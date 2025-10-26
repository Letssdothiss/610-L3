package test.java.SafeStorage.service;

import SafeStorage.model.EncryptedEntry;
import SafeStorage.service.EncryptionService;
import SafeStorage.util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

public class EncryptionServiceTest {

  private EncryptionService service;
    
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
    service = new EncryptionService(new EncryptionUtil());
  }

  @Test
  void encryptAndSave_ValidInput() {
    EncryptedEntry entry = service.encryptAndSave("Valid Title", "Test Content", 1);
        
    assertNotNull(entry);
    assertEquals("Valid Title", entry.getTitle());
    assertNotEquals("Test Content", entry.getEncryptedContent());
  }

  @Test
  void encryptAndDecrypt() {
    EncryptedEntry entry = service.encryptAndSave("Title", "Test Content", 3);
    String decrypted = service.decrypt(entry, 3);
        
    assertEquals("Test Content", decrypted);
  }

  @Test
  void encryptAndSave_EmptyTitle() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("", "Content", 1));
  }

  @Test
  void encryptAndSave_InvalidTitleCharacters() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("Invalid@Title#", "Content", 1));
  }

  @Test
  void encryptAndSave_TitleTooLong() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("ThisTitleIsWayTooLongToBeValid", "Content", 1));
  }

  @Test
  void encryptAndSave_EmptyContent() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("Title", "", 1));
  }

  @Test
  void encryptAndSave_InvalidEncryptionLevel() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("Title", "Content", 0));
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("Title", "Content", 6));
  }

  @Test
  void getAllEntries_ReturnsCopy() {
    service.encryptAndSave("Title1", "Content1", 1);
    service.encryptAndSave("Title2", "Content2", 1);
       
    var entries = service.getAllEntries();
    assertEquals(2, entries.size());
        
    entries.clear();
    assertEquals(2, service.getAllEntries().size());
  }

  @Test
  void getEntry_ValidIndex() {
    service.encryptAndSave("Title", "Content", 1);
       
    EncryptedEntry entry = service.getEntry(0);
    assertNotNull(entry);
    assertEquals("Title", entry.getTitle());
  }

  @Test
  void shouldLoadExistingEntriesOnInitialization() {
    // Arrange
    service.encryptAndSave("Title1", "Content1", 1);
    
    // Act
    EncryptionService newService = new EncryptionService(new EncryptionUtil());
    
    // Assert
    List<EncryptedEntry> loadedEntries = newService.getAllEntries();
    assertFalse(loadedEntries.isEmpty());
    assertEquals("Title1", loadedEntries.get(0).getTitle());
  }

  @Test
  void shouldMaintainEntryOrderAfterReload() {
    // Arrange
    service.encryptAndSave("Title1", "Content1", 1);
    service.encryptAndSave("Title2", "Content2", 1);
    
    // Act
    EncryptionService newService = new EncryptionService(new EncryptionUtil());
    
    // Assert
    List<EncryptedEntry> entries = newService.getAllEntries();
    assertEquals(2, entries.size());
    assertEquals("Title1", entries.get(0).getTitle());
    assertEquals("Title2", entries.get(1).getTitle());
  }

  @Test
  void shouldDecryptAfterAppRestart() {
    // Arrange - Create and save an entry
    service.encryptAndSave("Test Title", "Test Content", 3);
    
    // Act - Simulate app restart (new service instance)
    EncryptionService newService = new EncryptionService(new EncryptionUtil());
    
    // Assert - Verify we can decrypt the loaded entry
    List<EncryptedEntry> loadedEntries = newService.getAllEntries();
    assertEquals(1, loadedEntries.size());
    
    EncryptedEntry loadedEntry = loadedEntries.get(0);
    String decryptedContent = newService.decrypt(loadedEntry, 3);
    
    assertEquals("Test Content", decryptedContent);
  }
}
