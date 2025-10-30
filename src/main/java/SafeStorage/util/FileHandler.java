package SafeStorage.util;

import SafeStorage.model.EncryptedEntry;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file operations for storing and retrieving encrypted entries.
 * Provides methods for saving entries to file and loading them back.
 */
public class FileHandler {
  private final String filePath;

  public FileHandler() {
    this.filePath = System.getProperty("storage.file.path", "encrypted_entries.dat");
  }

  public void saveEntries(List<EncryptedEntry> entries) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(this.filePath))) {
      oos.writeObject(new ArrayList<>(entries));
    }
  }

  @SuppressWarnings("unchecked")
  public List<EncryptedEntry> loadEntries() throws IOException, ClassNotFoundException {
    if (!new File(this.filePath).exists()) {
      return new ArrayList<>();
    }

    try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(this.filePath))) {
      return (List<EncryptedEntry>) ois.readObject();
    }
  }
}
