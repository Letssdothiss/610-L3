package SafeStorage.view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class LoginView {

  private final Scene scene;
  private final Button loginButton;
  private final TextField usernameField;
  private final PasswordField passwordField;

  public LoginView() {
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20));
    layout.setAlignment(Pos.CENTER);

    Label titleLabel = new Label("Safe Storage");
    titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

    usernameField = new TextField();
    usernameField.setPromptText("Username");
    usernameField.setMaxWidth(200);

    passwordField = new PasswordField();
    passwordField.setPromptText("Password");
    passwordField.setMaxWidth(200);

    loginButton = new Button("Login");

    layout.getChildren().addAll(
      titleLabel,
      usernameField,
      passwordField,
      loginButton
    );

    scene = new Scene(layout, 400, 300);
  }

  // Getters for controller to access UI elements.
  public Scene getScene() {
    return scene;
  }

  public TextField getUsernameField() { 
    return usernameField;
  }

  public PasswordField getPasswordField() { 
    return passwordField; 
  }

  public Button getLoginButton() { 
    return loginButton; 
  }
}
