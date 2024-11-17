package test.java.SafeStorage.service;

import SafeStorage.model.EncryptedEntry;
import SafeStorage.service.EncryptionService;
import SafeStorage.util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionServiceTest {

  private EncryptionService service;
    
  @BeforeEach
  void setUp() {
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
  void encryptAndSave_EmptyTitle_ThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("", "Content", 1));
  }

  @Test
  void encryptAndSave_InvalidTitleCharacters_ThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("Invalid@Title#", "Content", 1));
  }

  @Test
  void encryptAndSave_TitleTooLong_ThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("ThisTitleIsWayTooLongToBeValid", "Content", 1));
  }

  @Test
  void encryptAndSave_EmptyContent_ThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> 
      service.encryptAndSave("Title", "", 1));
  }

  @Test
  void encryptAndSave_InvalidEncryptionLevel_ThrowsException() {
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
}
