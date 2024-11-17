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

  // Used in validation, to avoid magic numbers.
  private static final int MIN_ENCRYPTION_LEVEL = 1;
  private static final int MAX_ENCRYPTION_LEVEL = 5;

  /**
   * Creates a new EncryptionService with the specified encryption utility.
   * Initializes an empty list for storing encrypted entries.
   *
   * @param encryptionUtil - The utility class for encryption operations.
   */
  public EncryptionService(EncryptionUtil encryptionUtil) {
    try {
      this.encryptionUtil = encryptionUtil;
      this.entries = new ArrayList<>();
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to initialize encryption service: " + e.getMessage());
  }
  }

  /**
   * Encrypts content and saves it as a new entry.
   * Coordinates the encryption and storage process.
   *
   * @param title - The title for the entry (1-20 characters).
   * @param content - The content to encrypt.
   * @param encryptionLevel - The level of encryption to use (1-5).
   * @return The created and saved EncryptedEntry.
   * @throws IllegalArgumentException if any validation fails.
   */
  public EncryptedEntry encryptAndSave(String title, String content, int encryptionLevel) {
    validateTitle(title);
    validateContent(content);
    validateEncryptionLevel(encryptionLevel); 

    try {
      String encryptedContent = encryptContent(content, encryptionLevel);
      EncryptedEntry entry = createEntry(title, encryptedContent);
      return saveEntry(entry);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to encrypt and save entry.");
    }
  }

  /**
   * Validates the title format and length.
   * Title must be 1-20 characters and contain only letters, numbers, spaces, dots, hyphens and underscores.
   *
   * @param title - The title to validate.
   * @throws IllegalArgumentException if title format is invalid.
   */
  private void validateTitle(String title) {
    if (!title.matches("^[a-zA-Z0-9\\s._-]{1,20}$")) {
      throw new IllegalArgumentException("Title must be 1-20 characters and can only contain letters, numbers, spaces, dots, hyphens and underscores.");
    }
  }

  /**
   * Validates that the content is not empty.
   * Content is required for encryption.
   *
   * @param content - The content to validate.
   * @throws IllegalArgumentException if content is empty.
   */
  private void validateContent(String content) {
    if (content.isEmpty()) {
      throw new IllegalArgumentException("Content cannot be empty.");
    }
  }

  /**
   * Validates that the encryption level is within allowed range.
   * Level must be between MIN_ENCRYPTION_LEVEL and MAX_ENCRYPTION_LEVEL.
   *
   * @param encryptionLevel - The encryption level to validate.
   * @throws IllegalArgumentException if encryption level is out of range.
   */
  private void validateEncryptionLevel(int encryptionLevel) {
    if (encryptionLevel < MIN_ENCRYPTION_LEVEL || encryptionLevel > MAX_ENCRYPTION_LEVEL) {
      throw new IllegalArgumentException("Encryption level between 1-5 must be chosen.");
    }
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
   * @param title - The title for the entry.
   * @param encryptedContent - The pre-encrypted content.
   * @return A new EncryptedEntry instance.
   * @throws IllegalArgumentException if any validation fails.
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
    validateEntryExists(entry);
    validateEncryptionLevel(encryptionLevel);
  
    return encryptionUtil.decrypt(entry.getEncryptedContent(), encryptionLevel);
  }

  /**
   * Validates that the entry exists in storage.
   *
   * @param entry - The entry to validate.
   * @throws IllegalArgumentException if entry is invalid or not found.
   */
  private void validateEntryExists(EncryptedEntry entry) {
    if (!entries.contains(entry)) {
      throw new IllegalArgumentException("Entry not found in storage");
    }
  }

  /**
   * Returns a copy of all stored encrypted entries.
   * Returns a new ArrayList to prevent modification of internal list.
   *
   * @return List of all encrypted entries.
   * @throws IllegalArgumentException if failed to get all entries.
   */
  public List<EncryptedEntry> getAllEntries() {
    try {
      return new ArrayList<>(entries);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to get all entries: " + e.getMessage());
    }
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
