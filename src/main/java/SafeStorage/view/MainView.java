package SafeStorage.view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainView {

  private final Scene scene;
    private final TextField titleField;
    private final TextArea contentArea;
    private final Button encryptButton;
    private final ListView<String> entriesList;
  
  public MainView() {
    VBox layout = new VBox(20);
    layout.setPadding(new Insets(40));
    layout.setAlignment(Pos.CENTER);

    // Title.
    Label titleLabel = new Label("Safe Storage");
    titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

    // Entry title input
    titleField = new TextField();
    titleField.setPromptText("Enter title for encrypted entry");
    titleField.setMaxWidth(300);

    // Content input
    contentArea = new TextArea();
    contentArea.setPromptText("Enter content to encrypt");
    contentArea.setPrefRowCount(5);
    contentArea.setMaxWidth(300);

    // Encrypt button
    encryptButton = new Button("Encrypt");
    encryptButton.setMaxWidth(300);

    // List of encrypted entries
    entriesList = new ListView<>();
    entriesList.setPrefHeight(200);
    entriesList.setMaxWidth(300);

    layout.getChildren().addAll(
      titleLabel,
      new Label("Title:"),
      titleField,
      new Label("Content:"),
      contentArea,
      encryptButton,
      new Label("Encrypted Entries:"),
      entriesList
    );

    scene = new Scene(layout, 400, 600);
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

  public Button getEncryptButton() { 
    return encryptButton; 
  }

  public ListView<String> getEntriesList() { 
    return entriesList; 
  }
}
