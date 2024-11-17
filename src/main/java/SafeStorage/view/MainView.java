package SafeStorage.view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;

/**
 * Main view class for the SafeStorage application.
 * Provides the user interface for encrypting and decrypting text entries.
 */
public class MainView {

  private final Scene scene;
  private final TextField titleField;
  private final TextArea contentArea;
  private final TextArea decryptedContentArea;
  private final ComboBox<Integer> encryptionLevelBox;
  private final ComboBox<Integer> decryptionLevelBox;
  private final Button encryptButton;
  private final Button decryptButton;
  private final ListView<String> entriesList;
  
  /**
   * Constructs the main view with all UI components.
   * Initializes and layouts all controls for encryption and decryption.
   */
  public MainView() {
    VBox layout = new VBox(20);
    layout.setPadding(new Insets(40));
    layout.setAlignment(Pos.CENTER);

    // Title.
    Label titleLabel = new Label("Safe Storage");
    titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

    // Entry title input.
    titleField = new TextField();
    titleField.setId("titleField");
    titleField.setPromptText("Enter title for encrypted entry");
    titleField.setMaxWidth(300);

    // Content input.
    contentArea = new TextArea();
    contentArea.setId("contentArea");
    contentArea.setPromptText("Enter content to encrypt");
    contentArea.setPrefRowCount(5);
    contentArea.setMaxWidth(300);

    // Encryption level selector.
    encryptionLevelBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5));
    encryptionLevelBox.setId("encryptionLevelBox");
    encryptionLevelBox.setPromptText("Select encryption level");
    encryptionLevelBox.setValue(1); // Default value.

    // Encrypt button with level selection.
    HBox encryptionBox = new HBox(10);
    encryptionBox.setAlignment(Pos.CENTER);
    encryptButton = new Button("Encrypt");
    encryptButton.setId("encryptButton");
    encryptionBox.getChildren().addAll(encryptionLevelBox, encryptButton);

    // List of encrypted entries.
    entriesList = new ListView<>();
    entriesList.setId("entriesList");
    entriesList.setPrefHeight(200);
    entriesList.setMaxWidth(300);

    // Decryption section.
    decryptionLevelBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5));
    decryptionLevelBox.setId("decryptionLevelBox");
    decryptionLevelBox.setPromptText("Select decryption level");
    decryptionLevelBox.setValue(1); // Default value.

    // Decrypt button with level selection.
    HBox decryptionBox = new HBox(10);
    decryptionBox.setAlignment(Pos.CENTER);
    decryptButton = new Button("Decrypt Selected");
    decryptButton.setId("decryptButton");
    decryptionBox.getChildren().addAll(decryptionLevelBox, decryptButton);

    // Decrypted content display.
    decryptedContentArea = new TextArea();
    decryptedContentArea.setId("decryptedContentArea");
    decryptedContentArea.setPromptText("Decrypted content will appear here");
    decryptedContentArea.setPrefRowCount(5);
    decryptedContentArea.setMaxWidth(300);
    decryptedContentArea.setEditable(false);

    layout.getChildren().addAll(
      titleLabel,
      new Label("Title:"),
      titleField,
      new Label("Content:"),
      contentArea,
      encryptionBox,
      new Label("Encrypted Entries:"),
      entriesList,
      decryptionBox,
      new Label("Decrypted Content:"),
      decryptedContentArea
    );

    scene = new Scene(layout, 400, 800);
  }

  public Scene getScene() { 
    return scene; 
  }

  public TextField getTitleField() { 
    return titleField; 
  }

  public TextArea getContentArea() { 
    return contentArea; 
  }

  public TextArea getDecryptedContentArea() { 
    return decryptedContentArea; 
  }

  public ComboBox<Integer> getEncryptionLevelBox() { 
    return encryptionLevelBox; 
  }

  public ComboBox<Integer> getDecryptionLevelBox() { 
    return decryptionLevelBox; 
  }

  public Button getEncryptButton() { 
    return encryptButton; 
  }

  public Button getDecryptButton() { 
    return decryptButton; 
  }

  public ListView<String> getEntriesList() { 
    return entriesList; 
  }
}
