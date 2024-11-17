package test.java.SafeStorage.controller;

import SafeStorage.SafeStorage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import org.testfx.matcher.control.ListViewMatchers;
import javafx.scene.control.ListView;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

public class EncryptionControllerTest extends ApplicationTest {
  
  @Override
  public void start(Stage stage) {
    new SafeStorage().start(stage);
  }
    
  @Test
  void handleEncryption_ValidInput() {
    // Input test data
    clickOn("#titleField").write("Test Title");
    clickOn("#contentArea").write("Test Content");
    clickOn("#encryptionLevelBox").clickOn("1");
        
    // Trigger encryption
    clickOn("#encryptButton");
        
    // Verify results
    verifyThat("#entriesList", ListViewMatchers.hasItems(1));
    verifyThat("#titleField", hasText(""));
    verifyThat("#contentArea", hasText(""));
  }
    
  @Test
  void handleEncryption_InvalidInput() {
    // Try to encrypt with empty title
    clickOn("#contentArea").write("Test Content");
    clickOn("#encryptionLevelBox").clickOn("1");
    clickOn("#encryptButton");
        
    // Verify no entry was added
    verifyThat("#entriesList", ListViewMatchers.hasItems(0));
  }
    
  @Test
  void handleDecryption_ValidSelection() {
    // First encrypt something
    clickOn("#titleField").write("Test Title");
    clickOn("#contentArea").write("Test Content");
    clickOn("#encryptionLevelBox").clickOn("1");
    clickOn("#encryptButton");
        
    // Then decrypt it
    // First click the ListView itself
    clickOn("#entriesList");
        
    // Then click the first item in the list
    ListView<?> list = lookup("#entriesList").queryListView();
    String firstItem = list.getItems().get(0).toString();
    
    clickOn(firstItem);
    clickOn("#decryptionLevelBox").clickOn("1");
    clickOn("#decryptButton");
        
    // Verify decrypted content
    verifyThat("#decryptedContentArea", hasText("Test Content"));
      
  }
  
  @Test
  void handleDecryption_WrongLevel() {
    // First encrypt something
    clickOn("#titleField").write("Test Title");
    clickOn("#contentArea").write("Test Content");
    clickOn("#encryptionLevelBox").clickOn("1");
    clickOn("#encryptButton");
        
    // Then try to decrypt with wrong level
    // First click the ListView itself
    clickOn("#entriesList");
        
    // Then click the first item in the list
    ListView<?> list = lookup("#entriesList").queryListView();
    String firstItem = list.getItems().get(0).toString();
     
    clickOn(firstItem);
    clickOn("#decryptionLevelBox").clickOn("2");
    clickOn("#decryptButton");
        
    // Verify content is not decrypted correctly.
    verifyThat("#decryptedContentArea", hasText(""));
  }
  
  @Test
  void handleDecryption_NoSelection() {
    // Try to decrypt without selection
    clickOn("#decryptButton");
        
    // Verify decrypted area is empty
    verifyThat("#decryptedContentArea", hasText(""));
  }
  
  @Test
  void listSelection_ClearsDecryptedContent() {
    // First encrypt and decrypt something
    clickOn("#titleField").write("Test Title");
    clickOn("#contentArea").write("Test Content");
    clickOn("#encryptionLevelBox").clickOn("1");
    clickOn("#encryptButton");
    clickOn("#entriesList");
    clickOn("#decryptionLevelBox").clickOn("1");
    clickOn("#decryptButton");
        
    // Change selection
    press(KeyCode.ESCAPE);
       
    // Verify content was cleared
    verifyThat("#decryptedContentArea", hasText(""));
  }
}