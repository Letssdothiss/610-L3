package SafeStorage.service;

import SafeStorage.model.EncryptedEntry;
import SafeStorage.util.EncryptionUtil;
import SafeStorage.util.FileHandler;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Service class that manages encryption operations and storage of encrypted entries.
 */
public class EncryptionService {

  private final EncryptionUtil encryptionUtil;
  private final FileHandler fileHandler;

  private List<EncryptedEntry> entries;

  private static final int MIN_ENCRYPTION_LEVEL = 1;
  private static final int MAX_ENCRYPTION_LEVEL = 5;

  /**
   * Creates a new EncryptionService with the specified encryption utility.
   *
   * @param encryptionUtil - The utility class for encryption operations.
   */
  public EncryptionService(EncryptionUtil encryptionUtil) {
    try {
      this.encryptionUtil = encryptionUtil;
      this.entries = new ArrayList<>();
      this.fileHandler = new FileHandler();
      loadEntriesFromFile();
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to initialize encryption service: " + e.getMessage());
    }
  }
  
  /**
   * Creates a new EncryptionService with a new EncryptionUtil instance.
   * This constructor should be used when simulating app restarts.
   */
  public EncryptionService() {
    this(new EncryptionUtil());
  }

  private void loadEntriesFromFile() {
    try {
      this.entries = fileHandler.loadEntries();
    } catch (IOException | ClassNotFoundException e) {
      this.entries = new ArrayList<>();
    }
  }

  /**
   * Encrypts content and saves it as a new entry.
   * Coordinates the encryption and storage process.
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

  //Title must be 1-20 characters and contain only letters, numbers, spaces, dots, hyphens and underscores.
  private void validateTitle(String title) {
    if (!title.matches("^[a-zA-Z0-9\\s._-]{1,20}$")) {
      throw new IllegalArgumentException("Title must be 1-20 characters and can only contain letters, numbers, spaces, dots, hyphens and underscores.");
    }
  }

  private void validateContent(String content) {
    if (content.isEmpty()) {
      throw new IllegalArgumentException("Content cannot be empty.");
    }
  }

  private void validateEncryptionLevel(int encryptionLevel) {
    if (encryptionLevel < MIN_ENCRYPTION_LEVEL || encryptionLevel > MAX_ENCRYPTION_LEVEL) {
      throw new IllegalArgumentException("Encryption level between 1-5 must be chosen.");
    }
  }

  private String encryptContent(String content, int encryptionLevel) {
    return encryptionUtil.encrypt(content, encryptionLevel);
  }

  private EncryptedEntry createEntry(String title, String encryptedContent) {
    return new EncryptedEntry(title, encryptedContent);
  }

  private EncryptedEntry saveEntry(EncryptedEntry entry) {
    entries.add(entry);
    try {
      fileHandler.saveEntries(entries);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to save entries to file: " + e.getMessage());
    }
    return entry;
  }

  public String decrypt(EncryptedEntry entry, int encryptionLevel) {
    validateEntryExists(entry);
    validateEncryptionLevel(encryptionLevel);
  
    // Returns "garbled" text if wrong level.
    return encryptionUtil.decrypt(entry.getEncryptedContent(), encryptionLevel);
  }

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

  // Throws IndexOutOfBoundsException if the index is out of range.
  public EncryptedEntry getEntry(int index) {
    return entries.get(index);
  }
}
