package SafeStorage.controller;

import SafeStorage.view.MainView;
import SafeStorage.service.EncryptionService;
import SafeStorage.model.EncryptedEntry;
import javafx.scene.control.Alert;
import java.util.List;

/**
 * Controller class that handles user interactions and coordinates between the view and service layer.
 * Manages encryption operations, UI updates, and error handling.
 */
public class EncryptionController {
  
  private final MainView view;
  private final EncryptionService encryptionService;

  /**
   * Creates a new EncryptionController and initializes the UI event handlers.
   *
   * @param view - The main view of the application.
   * @param encryptionService - The service handling encryption operations.
   * @throws IllegalArgumentException if initialization fails.
   */
  public EncryptionController(MainView view, EncryptionService encryptionService) {
    try {
      this.view = view;
      this.encryptionService = encryptionService;
      initializeEventHandlers();
      loadExistingEntries();
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to initialize controller: " + e.getMessage());
    }
  }

  /**
   * Sets up event handlers for UI components.
   * Links user actions to their handler methods.
   */
  private void initializeEventHandlers() {
    // Set up encryption button click handler.
    view.getEncryptButton().setOnAction(e -> handleEncryption());

    // Set up decryption button click handler.
    view.getDecryptButton().setOnAction(e -> handleDecryption());

    // Clear decrypted content when selection changes.
    view.getEntriesList().getSelectionModel().selectedItemProperty()
      .addListener((obs, oldVal, newVal) -> clearDecryptedContent());
  }

  /**
   * Handles the encryption process when the encrypt button is clicked.
   * Creates and saves a new encrypted entry, then updates the UI.
   */
  private void handleEncryption() {
    try {
      EncryptedEntry entry = createEncryptedEntry();
      saveAndUpdateView(entry);
    } catch (Exception e) {
      showError("Failed to encrypt: " + e.getMessage());
    }
  }

  /**
   * Creates a new encrypted entry from user input.
   * Retrieves title, content, and encryption level from the UI.
   *
   * @return The newly created and encrypted entry
   */
  private EncryptedEntry createEncryptedEntry() {
    String title = view.getTitleField().getText();
    String content = view.getContentArea().getText();
    Integer encryptionLevel = view.getEncryptionLevelBox().getValue();
    
    return encryptionService.encryptAndSave(title, content, encryptionLevel);
  }


  private void saveAndUpdateView(EncryptedEntry entry) {
    updateEntriesList();
    clearInputs();
  }

  private void handleDecryption() {
    try {
      validateSelection();
      decryptAndDisplayContent();
    } catch (Exception e) {
      handleDecryptionError(e);
    }
  }

  private void validateSelection() {
    if (view.getEntriesList().getSelectionModel().getSelectedIndex() < 0) {
      throw new IllegalStateException("Please select an entry to decrypt");
    }
  }

  private void decryptAndDisplayContent() {
    EncryptedEntry entry = getSelectedEntry();
    Integer level = view.getDecryptionLevelBox().getValue();
    String decryptedContent = encryptionService.decrypt(entry, level);
    view.getDecryptedContentArea().setText(decryptedContent);
  }

  private EncryptedEntry getSelectedEntry() {
    int selectedIndex = view.getEntriesList().getSelectionModel().getSelectedIndex();
    return encryptionService.getEntry(selectedIndex);
  }

  private void handleDecryptionError(Exception e) {
    showError("Failed to decrypt: " + e.getMessage());
    clearDecryptedContent();
  }

  private void updateEntriesList() {
    try {
        clearEntriesList();
        displayEntries();
    } catch (Exception e) {
        showError("Failed to update entries list: " + e.getMessage());
    }
  }

  private void clearEntriesList() {
    view.getEntriesList().getItems().clear();
  }

  private void displayEntries() {
    List<EncryptedEntry> entries = encryptionService.getAllEntries();
    entries.forEach(entry -> 
        view.getEntriesList().getItems().add(entry.toString())
    );
  }

  private void clearDecryptedContent() {
    view.getDecryptedContentArea().clear();
  }

  private void loadExistingEntries() {
    updateEntriesList();
  }

  private void clearInputs() {
    view.getTitleField().clear();
    view.getContentArea().clear();
  }

  private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
