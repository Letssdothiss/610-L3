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

  /**
   * Saves a list of encrypted entries to file.
   * 
   * @param entries The list of entries to save
   * @throws IOException if writing to file fails
   */
  public void saveEntries(List<EncryptedEntry> entries) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(this.filePath))) {
      oos.writeObject(new ArrayList<>(entries));
    }
  }

  /**
   * Loads encrypted entries from file.
   * 
   * @return List of loaded encrypted entries
   * @throws IOException if reading from file fails
   * @throws ClassNotFoundException if deserialization fails
   */
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
