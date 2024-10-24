package SafeStorage;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class SafeStorage extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Safe Storage");

    TextField passwordField = new TextField();
    passwordField.setPromptText("Enter password");

    Button encryptPasswordButton = new Button("Encrypt Password");
    TextArea encryptedPasswordArea = new TextArea();
    encryptedPasswordArea.setEditable(false);

    encryptPasswordButton.setOnAction(e -> {
      String password = passwordField.getText();
      // Placeholder for encryption logic
      String encryptedPassword = "Encrypted: " + password;
      encryptedPasswordArea.setText(encryptedPassword);
    });

    VBox vbox = new VBox(passwordField, encryptPasswordButton, encryptedPasswordArea);
    Scene scene = new Scene(vbox, 800, 600);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}