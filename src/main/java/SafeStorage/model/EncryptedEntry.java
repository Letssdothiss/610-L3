package SafeStorage.model;

import java.time.LocalDateTime;

/**
 * Represents an encrypted entry in the safe storage system.
 * Each entry contains a title, encrypted content, encryption level, and timestamp.
 */
public class EncryptedEntry {

  private String title;
  private String encryptedContent;
  private int encryptionLevel;
  private LocalDateTime timestamp;

  // Used in validation, to avoid magic numbers.
  private static final int MIN_ENCRYPTION_LEVEL = 1;
  private static final int MAX_ENCRYPTION_LEVEL = 5;

  /**
    * Creates a new encrypted entry with the specified parameters.
    * Validates all inputs before creating the entry to ensure valid state.
    *
    * @param title - The title of the entry (1-20 characters, alphanumeric and basic punctuation)
    * @param encryptedContent - The encrypted content to be stored
    * @param encryptionLevel - The level of encryption used (1-5)
    * @throws IllegalArgumentException if any validation fails
    */
  public EncryptedEntry(String title, String encryptedContent, int encryptionLevel) {
    
    // Validation before setting states to prevent invalid object creation.
    if (title.isEmpty()) {
      throw new IllegalArgumentException("Title must not be empty.");
    }
    encryptedContentValidation(encryptedContent);
    encryptionLevelValidation(encryptionLevel);

    setTitle(title);
    setEncryptedContent(encryptedContent);
    setEncryptionLevel(encryptionLevel);
    this.timestamp = LocalDateTime.now();
  }

  /**
   * Setter for the title.
   * Validates the title parameter, used in the constructor.
   *
   * @param title - The title of the encrypted content entry.
   * @throws IllegalArgumentException
   */
  private void setTitle(String title) {
    if (!title.matches("^[a-zA-Z0-9\\s._-]{1,20}$")) {
      throw new IllegalArgumentException("Title must be 1-20 characters and can only contain letters, numbers, spaces, dots, hyphens and underscores.");
    }
    this.title = title;
  }

  /**
   * Setter for the encrypted content.
   * Validates the encrypted content, used in the constructor.
   * 
   * @param encryptedContent - The encrypted content to be stored.
   */
  private void setEncryptedContent(String encryptedContent) {
    encryptedContentValidation(encryptedContent);
    this.encryptedContent = encryptedContent;
  }

  /**
   * Setter for the used encryption level.
   * Validates the encryption level, used in the constructor.
   * 
   * @param encryptionLevel - The level of encryption used to encrypt the encrypted content.
   */
  private void setEncryptionLevel(int encryptionLevel) {
    encryptionLevelValidation(encryptionLevel);
    this.encryptionLevel = encryptionLevel;
  }

  /**
   * Validation method for the encryption level.
   * Makes sure the encryption level is between 1-5
   * 
   * @param encryptionLevel - The entered encryption level.
   * @throws IllegalArgumentException
   */
  private void encryptionLevelValidation(int encryptionLevel) {
    if (encryptionLevel < MIN_ENCRYPTION_LEVEL || encryptionLevel > MAX_ENCRYPTION_LEVEL) {
      throw new IllegalArgumentException("Encryption level between 1-5 must be chosen.");
    }
  }

  /**
   * Validation method for the encrypted content.
   * Makes sure the encrypted content is not empty.
   * 
   * @param encryptedContent
   * @throws IllegalArgumentException
   */
  private void encryptedContentValidation(String encryptedContent) {
    if (encryptedContent.isEmpty()) {
      throw new IllegalArgumentException("Content cannot be empty.");
    }
  }

  public String getTitle() { 
    return title; 
  }

  public String getEncryptedContent() { 
    return encryptedContent; 
  }

  public int getEncryptionLevel() { 
    return encryptionLevel; 
  }

  public LocalDateTime getTimestamp() { 
    return timestamp; 
  }

  /**
     * Returns a string representation of this entry.
     * Only includes non-sensitive information (title and timestamp).
     * 
     * @return String containing the entry's title and creation timestamp
     */
  @Override
  public String toString() {
    return title + " (Created: " + timestamp + ")";
  }
}

