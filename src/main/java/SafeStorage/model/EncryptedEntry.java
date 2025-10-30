package SafeStorage.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an encrypted entry in the safe storage system.
 * Each entry contains a title, encrypted content, and timestamp.
 */
public class EncryptedEntry implements Serializable {

  private static final long serialVersionUID = 1L;

  private String title;
  private String encryptedContent;

  /**
    * Creates a new encrypted entry with the specified parameters.
    */
  public EncryptedEntry(String title, String encryptedContent) {
    
    // Validation before setting states to prevent invalid object creation.
    if (title.isEmpty()) {
      throw new IllegalArgumentException("Title must not be empty.");
    }
    encryptedContentValidation(encryptedContent);

    setTitle(title);
    setEncryptedContent(encryptedContent);
  }

  private void setTitle(String title) {
    if (!title.matches("^[a-zA-Z0-9\\s._-]{1,20}$")) {
      throw new IllegalArgumentException("Title must be 1-20 characters and can only contain letters, numbers, spaces, dots, hyphens and underscores.");
    }
    this.title = title;
  }

  private void setEncryptedContent(String encryptedContent) {
    encryptedContentValidation(encryptedContent);
    this.encryptedContent = encryptedContent;
  }

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

  @Override
  public String toString() {
    return title;
  }

  /**
   * Compares this entry with another object for equality.
   * Two entries are considered equal if they have the same title and encrypted content.
   * 
   * @param o - The object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EncryptedEntry that = (EncryptedEntry) o;
    return Objects.equals(title, that.title) &&
           Objects.equals(encryptedContent, that.encryptedContent);
  }

  /**
   * Returns a hash code for this entry.
   * The hash code is based on the title and encrypted content.
   */
  @Override
  public int hashCode() {
    return Objects.hash(title, encryptedContent);
  }
}

