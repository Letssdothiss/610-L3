package test.java.SafeStorage.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import SafeStorage.model.EncryptedEntry;

class EncryptedEntryTest {
    
  private static final String VALID_TITLE = "Test Title";
  private static final String VALID_CONTENT = "Test content";

  @Test
  @DisplayName("Should create valid instance with valid inputs")
  void shouldCreateValidInstance() {
    EncryptedEntry entry = new EncryptedEntry(VALID_TITLE, VALID_CONTENT);
          
    // Verify all fields are set correctly
    assertEquals(VALID_TITLE, entry.getTitle());
    assertEquals(VALID_CONTENT, entry.getEncryptedContent());
    assertNotNull(entry.getTimestamp());
  }

  @Test
  @DisplayName("Should validate title correctly")
  void shouldValidateTitleCorrectly() {
    assertAll(
      // Test empty title
      () -> {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
          () -> new EncryptedEntry("", VALID_CONTENT));
        assertEquals("Title must not be empty.", e.getMessage());
      },
            
      // Test invalid characters
      () -> {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
          () -> new EncryptedEntry("Invalid@Title#", VALID_CONTENT));
        assertTrue(e.getMessage().contains("Title must be 1-20 characters"));
      },
            
      // Test too long title
      () -> {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
          () -> new EncryptedEntry("This title is way too long to be valid", VALID_CONTENT));
        assertTrue(e.getMessage().contains("Title must be 1-20 characters"));
      }
    );
  }

  @Test
  @DisplayName("Should validate content correctly")
  void shouldValidateContentCorrectly() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
      () -> new EncryptedEntry(VALID_TITLE, ""));
    assertEquals("Content cannot be empty.", e.getMessage());
  }

  @Test
  @DisplayName("Should create proper string representation")
  void shouldCreateProperStringRepresentation() {
    EncryptedEntry entry = new EncryptedEntry(VALID_TITLE, VALID_CONTENT);
    String toString = entry.toString();
        
    assertTrue(toString.contains(VALID_TITLE));
    assertTrue(toString.contains("Created:"));
    assertFalse(toString.contains(VALID_CONTENT));
  }
}
