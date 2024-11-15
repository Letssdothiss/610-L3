package SafeStorage.model;

import java.time.LocalDateTime;

public class EncryptedEntry {

  private final String title;
  private final String encryptedContent;
  private final LocalDateTime timestamp;

  public EncryptedEntry(String title, String encryptedContent) {
    this.title = title;
    this.encryptedContent = encryptedContent;
    this.timestamp = LocalDateTime.now();
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

  @Override
  public String toString() {
    return title + " (Created: " + timestamp + ")";
  }
}

