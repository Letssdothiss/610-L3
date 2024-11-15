package SafeStorage.service;

import SafeStorage.model.EncryptedEntry;
import SafeStorage.util.EncryptionUtil;
import java.util.ArrayList;
import java.util.List;

public class EncryptionService {
  private final EncryptionUtil encryptionUtil;
  private final List<EncryptedEntry> entries;

  public EncryptionService(EncryptionUtil encryptionUtil) {
    this.encryptionUtil = encryptionUtil;
    this.entries = new ArrayList<>();  // Temporary in-memory storage
  }

  public EncryptedEntry encryptAndSave(String title, String content) {
    if (title == null || content == null || title.isEmpty() || content.isEmpty()) {
      throw new IllegalArgumentException("Title and content cannot be empty");
    }

    // For now, just store the content as-is
    // TODO: Implement actual encryption when EncryptionUtil is ready
    String encryptedContent = content;
        
    EncryptedEntry entry = new EncryptedEntry(title, encryptedContent);
    entries.add(entry);
    return entry;
  }

  public List<EncryptedEntry> getAllEntries() {
    return new ArrayList<>(entries);  // Return a copy to prevent external modification
  }
}
