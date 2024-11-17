package SafeStorage.model;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * Represents an encrypted entry in the safe storage system.
 * Each entry contains a title, encrypted content, and timestamp.
 */
public class EncryptedEntry implements Serializable {

  private static final long serialVersionUID = 1L;

  private String title;
  private String encryptedContent;
  private LocalDateTime timestamp;

  /**
    * Creates a new encrypted entry with the specified parameters.
    * Validates all inputs before creating the entry to ensure valid state.
    *
    * @param title - The title of the entry (1-20 characters, alphanumeric and basic punctuation)
    * @param encryptedContent - The encrypted content to be stored
    * @throws IllegalArgumentException if any validation fails
    */
  public EncryptedEntry(String title, String encryptedContent) {
    
    // Validation before setting states to prevent invalid object creation.
    if (title.isEmpty()) {
      throw new IllegalArgumentException("Title must not be empty.");
    }
    encryptedContentValidation(encryptedContent);

    setTitle(title);
    setEncryptedContent(encryptedContent);
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

