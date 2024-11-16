package SafeStorage.controller;

import SafeStorage.view.MainView;
import SafeStorage.service.EncryptionService;
import SafeStorage.model.EncryptedEntry;
import javafx.scene.control.Alert;
import java.util.List;

public class EncryptionController {
  
  private final MainView view;
  private final EncryptionService encryptionService;

  public EncryptionController(MainView view, EncryptionService encryptionService) {
    this.view = view;
    this.encryptionService = encryptionService;
    initializeEventHandlers();
    loadExistingEntries();
  }

  private void initializeEventHandlers() {
    view.getEncryptButton().setOnAction(e -> handleEncryption());
    view.getDecryptButton().setOnAction(e -> handleDecryption());
    view.getEntriesList().getSelectionModel().selectedItemProperty()
      .addListener((obs, oldVal, newVal) -> clearDecryptedContent());
  }

  private void handleEncryption() {
    String title = view.getTitleField().getText();
    String content = view.getContentArea().getText();
    Integer encryptionLevel = view.getEncryptionLevelBox().getValue();

    try {
      EncryptedEntry entry = encryptionService.encryptAndSave(title, content, encryptionLevel);
      updateEntriesList();
      clearInputs();
    } catch (Exception e) {
      showError("Failed to encrypt: " + e.getMessage());
    }
  }

  private void handleDecryption() {
    int selectedIndex = view.getEntriesList().getSelectionModel().getSelectedIndex();
    Integer level = view.getDecryptionLevelBox().getValue();

    try {
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
    view.getEntriesList().getItems().clear();
    List<EncryptedEntry> entries = encryptionService.getAllEntries();
    entries.forEach(entry -> 
      view.getEntriesList().getItems().add(entry.toString())
    );
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
