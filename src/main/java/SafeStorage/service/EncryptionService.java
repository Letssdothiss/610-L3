package SafeStorage.service;

import SafeStorage.model.EncryptedEntry;
import SafeStorage.util.EncryptionUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class that manages encryption operations and storage of encrypted entries.
 * Provides methods for encrypting, decrypting, and managing encrypted content entries.
 */
public class EncryptionService {

  private final EncryptionUtil encryptionUtil;
  private final List<EncryptedEntry> entries;

  /**
   * Creates a new EncryptionService with the specified encryption utility.
   * Initializes an empty list for storing encrypted entries.
   *
   * @param encryptionUtil - The utility class for encryption operations.
   */
  public EncryptionService(EncryptionUtil encryptionUtil) {
    this.encryptionUtil = encryptionUtil;
    this.entries = new ArrayList<>();
  }

  /**
   * Encrypts content and saves it as a new entry.
   * Coordinates the encryption and storage process.
   *
   * @param title - The title for the entry (1-20 characters)
   * @param content - The content to encrypt
   * @param encryptionLevel - The level of encryption to use (1-5)
   * @return The created and saved EncryptedEntry
   * @throws IllegalArgumentException if any validation fails
   */
  public EncryptedEntry encryptAndSave(String title, String content, int encryptionLevel) {
    String encryptedContent = encryptContent(content, encryptionLevel);
    EncryptedEntry entry = createEntry(title, encryptedContent);
    
    return saveEntry(entry);
  }

  /**
   * Encrypts the provided content using the specified encryption level.
   *
   * @param content - The content to encrypt.
   * @param encryptionLevel - The level of encryption to use.
   * @return The encrypted content.
   */
  private String encryptContent(String content, int encryptionLevel) {
    return encryptionUtil.encrypt(content, encryptionLevel);
  }

  /**
   * Creates a new EncryptedEntry with the provided parameters.
   *
   * @param title - The title for the entry
   * @param encryptedContent - The pre-encrypted content
   * @return A new EncryptedEntry instance
   * @throws IllegalArgumentException if any validation fails
   */
  private EncryptedEntry createEntry(String title, String encryptedContent) {
    return new EncryptedEntry(title, encryptedContent);
  }

  /**
   * Saves the provided entry to storage.
   *
   * @param entry - The entry to save.
   * @return The saved entry.
   */
  private EncryptedEntry saveEntry(EncryptedEntry entry) {
    entries.add(entry);
    return entry;
  }

  /**
   * Decrypts the content of the specified entry using the provided encryption level.
   * The level must match the one used for encryption.
   *
   * @param entry - The encrypted entry to decrypt.
   * @param encryptionLevel - The encryption level to use for decryption.
   * @return The decrypted content, or garbled text if wrong level.
   */
  public String decrypt(EncryptedEntry entry, int encryptionLevel) {
    return encryptionUtil.decrypt(entry.getEncryptedContent(), encryptionLevel);
  }

  /**
   * Returns a copy of all stored encrypted entries.
   * Returns a new ArrayList to prevent modification of internal list.
   *
   * @return List of all encrypted entries.
   */
  public List<EncryptedEntry> getAllEntries() {
    return new ArrayList<>(entries);
  }

  /**
   * Retrieves a specific entry by its index in the list.
   *
   * @param index - The index of the entry to retrieve.
   * @return The EncryptedEntry at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  public EncryptedEntry getEntry(int index) {
    return entries.get(index);
  }
}
