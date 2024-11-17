package SafeStorage.controller;

import SafeStorage.view.MainView;
import SafeStorage.service.EncryptionService;
import SafeStorage.model.EncryptedEntry;
import javafx.scene.control.Alert;
import java.util.List;

public class EncryptionController {
  
  private final MainView view;
  private final EncryptionService encryptionService;

  /**
   * Creates a new EncryptionController and initializes the UI event handlers.
   *
   * @param view - The main view of the application
   * @param encryptionService - The service handling encryption operations
   * @throws IllegalArgumentException if initialization fails
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

  private void initializeEventHandlers() {
    view.getEncryptButton().setOnAction(e -> handleEncryption());
    view.getDecryptButton().setOnAction(e -> handleDecryption());
    view.getEntriesList().getSelectionModel().selectedItemProperty()
      .addListener((obs, oldVal, newVal) -> clearDecryptedContent());
  }

  private void handleEncryption() {
    try {
      EncryptedEntry entry = createEncryptedEntry();
      saveAndUpdateView(entry);
    } catch (Exception e) {
      showError("Failed to encrypt: " + e.getMessage());
    }
  }

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
      if (view.getEntriesList().getSelectionModel().getSelectedIndex() < 0) {
        showError("Please select an entry to decrypt");
        return;
      }

      int selectedIndex = view.getEntriesList().getSelectionModel().getSelectedIndex();
      Integer level = view.getDecryptionLevelBox().getValue();

      EncryptedEntry entry = encryptionService.getEntry(selectedIndex);
      String decryptedContent = encryptionService.decrypt(entry, level);
      view.getDecryptedContentArea().setText(decryptedContent);
    } catch (Exception e) {
      showError("Failed to decrypt: " + e.getMessage());
      clearDecryptedContent();
    }
  }

  private void clearDecryptedContent() {
    view.getDecryptedContentArea().clear();
  }

  private void loadExistingEntries() {
    updateEntriesList();
  }

  private void updateEntriesList() {
    try {
      view.getEntriesList().getItems().clear();
      List<EncryptedEntry> entries = encryptionService.getAllEntries();
      entries.forEach(entry -> 
        view.getEntriesList().getItems().add(entry.toString())
      );
    } catch (Exception e) {
      showError("Failed to update entries list: " + e.getMessage());
    }
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
