package test.java.SafeStorage.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import SafeStorage.util.EncryptionUtil;

public class EncryptionUtilTest {
  
  private EncryptionUtil encryptionUtil;
  private static final String TEST_CONTENT = "Test content to encrypt";
  private static final int VALID_LEVEL = 3;

  @BeforeEach
  void setUp() {
    encryptionUtil = new EncryptionUtil();
  }

  @Test
  @DisplayName("Should encrypt and decrypt content correctly")
  void shouldEncryptAndDecryptContentCorrectly() {
    String encrypted = encryptionUtil.encrypt(TEST_CONTENT, VALID_LEVEL);
    String decrypted = encryptionUtil.decrypt(encrypted, VALID_LEVEL);
        
    assertAll(
      // Encrypted content should be different from original
      () -> assertNotEquals(TEST_CONTENT, encrypted),
      // Decrypted content should match original
      () -> assertEquals(TEST_CONTENT, decrypted)
    );
  }

  @Test
  @DisplayName("Should decrypt content only with correct level")
  void shouldDecryptOnlyWithCorrectLevel() {
    String encrypted = encryptionUtil.encrypt(TEST_CONTENT, VALID_LEVEL);
        
    assertAll(
      // Correct level should work
      () -> assertEquals(TEST_CONTENT, 
        encryptionUtil.decrypt(encrypted, VALID_LEVEL)),
            
      // Wrong level should not work
      () -> assertNotEquals(TEST_CONTENT, 
        encryptionUtil.decrypt(encrypted, VALID_LEVEL + 1))
    );
  }

  @Test
  @DisplayName("Should handle special characters")
  void shouldHandleSpecialCharacters() {
    String specialContent = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    String encrypted = encryptionUtil.encrypt(specialContent, VALID_LEVEL);
    String decrypted = encryptionUtil.decrypt(encrypted, VALID_LEVEL);
        
    assertEquals(specialContent, decrypted);
  }
}
