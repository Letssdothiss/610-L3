package SafeStorage;

import javafx.application.Application;
import javafx.stage.Stage;
import SafeStorage.view.LoginView;
import SafeStorage.controller.LoginController;
import SafeStorage.service.AuthenticationService;
import SafeStorage.util.EncryptionUtil;

public class SafeStorage extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      primaryStage.setTitle("Safe Storage");

      // Initialize dependencies.
      EncryptionUtil encryptionUtil = new EncryptionUtil();
      AuthenticationService authService = new AuthenticationService(encryptionUtil);

      // Set up MVC components.
      LoginView loginView = new LoginView();
      LoginController loginController = new LoginController(loginView, authService);
      loginController.setOnLoginSuccess(() -> showMainMenu());

      primaryStage.setScene(loginView.getScene());
      primaryStage.show();
    }

    private void showMainMenu() {
      // TODO: Initialize main menu components.
      System.out.println("Login successful! Main menu coming soon...");
    }

    public static void main(String[] args) {
      launch(args);
    }
}