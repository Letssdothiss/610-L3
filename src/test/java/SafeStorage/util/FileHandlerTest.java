package test.java.SafeStorage.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import SafeStorage.model.EncryptedEntry;
import SafeStorage.util.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerTest {
  private FileHandler fileHandler;
  private static final String TEST_FILE = "test_encrypted_entries.dat";

  @BeforeEach
  void setUp() {
    // Clean up any existing test files
    File file1 = new File("encrypted_entries.dat");
    File file2 = new File("test_encrypted_entries.dat");
    if (file1.exists()) {
      file1.delete();
    }
    if (file2.exists()) {
      file2.delete();
    }
    // Set system property for test file path to ensure isolation
    System.setProperty("storage.file.path", "test_encrypted_entries.dat");
    fileHandler = new FileHandler();
  }

  @AfterEach
  void cleanup() {
    // Delete test file after each test
    File file = new File(TEST_FILE);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  void shouldSaveAndLoadEntries() throws IOException, ClassNotFoundException {

    List<EncryptedEntry> entries = new ArrayList<>();
    entries.add(new EncryptedEntry("Test Title", "Encrypted Content"));
        
    fileHandler.saveEntries(entries);
    List<EncryptedEntry> loadedEntries = fileHandler.loadEntries();
       
    assertEquals(1, loadedEntries.size());
    assertEquals("Test Title", loadedEntries.get(0).getTitle());
    assertEquals("Encrypted Content", loadedEntries.get(0).getEncryptedContent());
  }

  @Test
  void shouldReturnEmptyListWhenFileDoesNotExist() throws IOException, ClassNotFoundException {
  
    List<EncryptedEntry> loadedEntries = fileHandler.loadEntries();
        
    assertNotNull(loadedEntries);
    assertTrue(loadedEntries.isEmpty());
  }

  @Test
  void shouldSaveMultipleEntries() throws IOException, ClassNotFoundException {

    List<EncryptedEntry> entries = new ArrayList<>();
    entries.add(new EncryptedEntry("Title 1", "Content 1"));
    entries.add(new EncryptedEntry("Title 2", "Content 2"));
        
    fileHandler.saveEntries(entries);
    List<EncryptedEntry> loadedEntries = fileHandler.loadEntries();
        
    assertEquals(2, loadedEntries.size());
  }

  @Test
  void shouldOverwriteExistingFile() throws IOException, ClassNotFoundException {

    List<EncryptedEntry> entries1 = new ArrayList<>();
    entries1.add(new EncryptedEntry("Title 1", "Content 1"));
        
    List<EncryptedEntry> entries2 = new ArrayList<>();
    entries2.add(new EncryptedEntry("Title 2", "Content 2"));
        
    fileHandler.saveEntries(entries1);
    fileHandler.saveEntries(entries2);
    List<EncryptedEntry> loadedEntries = fileHandler.loadEntries();
        
    assertEquals(1, loadedEntries.size());
    assertEquals("Title 2", loadedEntries.get(0).getTitle());
  }
}
