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
}
