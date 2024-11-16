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
    this.entries = new ArrayList<>();
  }

  public EncryptedEntry encryptAndSave(String title, String content, int encryptionLevel) {
    String encryptedContent = encryptionUtil.encrypt(content, encryptionLevel);
    EncryptedEntry entry = new EncryptedEntry(title, encryptedContent, encryptionLevel);
    entries.add(entry);
    return entry;
  }

  public String decrypt(EncryptedEntry entry, int encryptionLevel) {
    return encryptionUtil.decrypt(entry.getEncryptedContent(), encryptionLevel);
  }

  public List<EncryptedEntry> getAllEntries() {
    return new ArrayList<>(entries);
  }

  public EncryptedEntry getEntry(int index) {
    return entries.get(index);
  }
}
