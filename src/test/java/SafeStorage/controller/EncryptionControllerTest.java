package test.java.SafeStorage.controller;

import SafeStorage.controller.EncryptionController;
import SafeStorage.service.EncryptionService;
import SafeStorage.view.MainView;
import SafeStorage.util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionControllerTest {
  
  private EncryptionController controller;
    private MainView view;
    private EncryptionService service;
    
  @BeforeEach
  void setUp() {
    service = new EncryptionService(new EncryptionUtil());
    view = new MainView();
    controller = new EncryptionController(view, service);
  }
    
  @Test
  void constructor_ValidParameters() {
    assertNotNull(controller);
  }

  @Test
  void handleEncryption_ValidInput() {
    // Setup input fields
    view.getTitleField().setText("Test Title");
    view.getContentArea().setText("Test Content");
    view.getEncryptionLevelBox().setValue(1);
        
    // Trigger encryption
    view.getEncryptButton().fire();
        
    // Verify
    assertEquals(1, view.getEntriesList().getItems().size());
    assertTrue(view.getTitleField().getText().isEmpty());
    assertTrue(view.getContentArea().getText().isEmpty());
  }
    
  @Test
  void handleEncryption_InvalidInput() {
    // Setup invalid input
    view.getTitleField().setText("");
    view.getContentArea().setText("Test Content");
    view.getEncryptionLevelBox().setValue(1);
        
    // Trigger encryption
    view.getEncryptButton().fire();
       
    // Verify
    assertEquals(0, view.getEntriesList().getItems().size());
  }

  @Test
  void handleDecryption_ValidSelection() {
    // First encrypt something
    view.getTitleField().setText("Test Title");
    view.getContentArea().setText("Test Content");
    view.getEncryptionLevelBox().setValue(1);
    view.getEncryptButton().fire();
      
    // Select the entry
    view.getEntriesList().getSelectionModel().select(0);
    view.getDecryptionLevelBox().setValue(1);
      
    // Trigger decryption
    view.getDecryptButton().fire();
      
    // Verify
    assertEquals("Test Content", view.getDecryptedContentArea().getText());
  }
  
  @Test
  void handleDecryption_WrongLevel() {
    // First encrypt something
    view.getTitleField().setText("Test Title");
    view.getContentArea().setText("Test Content");
    view.getEncryptionLevelBox().setValue(1);
    view.getEncryptButton().fire();
      
    // Select entry but use wrong level
    view.getEntriesList().getSelectionModel().select(0);
    view.getDecryptionLevelBox().setValue(2);
      
    // Trigger decryption
    view.getDecryptButton().fire();
      
    // Verify
    assertNotEquals("Test Content", view.getDecryptedContentArea().getText());
  }
  
  @Test
  void handleDecryption_NoSelection() {
    view.getDecryptButton().fire();
    assertTrue(view.getDecryptedContentArea().getText().isEmpty());
  }
  
  @Test
  void listSelection_ClearsDecryptedContent() {
    // First set some decrypted content
    view.getDecryptedContentArea().setText("Some content");
      
    // Trigger selection change
    view.getEntriesList().getSelectionModel().clearSelection();
      
    // Verify content was cleared
    assertTrue(view.getDecryptedContentArea().getText().isEmpty());
  }
}
