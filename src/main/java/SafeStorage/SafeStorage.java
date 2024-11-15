package SafeStorage;

import javafx.application.Application;
import javafx.stage.Stage;
import SafeStorage.view.LoginView;
import SafeStorage.view.MainView;
import SafeStorage.controller.LoginController;
import SafeStorage.controller.EncryptionController;
import SafeStorage.service.AuthenticationService;
import SafeStorage.service.EncryptionService;
import SafeStorage.util.EncryptionUtil;

public class SafeStorage extends Application {
    private Stage primaryStage;
    private EncryptionUtil encryptionUtil;
    private EncryptionService encryptionService;

    @Override
    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      primaryStage.setTitle("Safe Storage");

      // Initialize dependencies.
      encryptionUtil = new EncryptionUtil();
      encryptionService = new EncryptionService(encryptionUtil);
      AuthenticationService authService = new AuthenticationService(encryptionUtil);

      // Set up MVC components.
      LoginView loginView = new LoginView();

      //TODO: Implement AuthenticationService
      LoginController loginController = new LoginController(loginView, authService);
      loginController.setOnLoginSuccess(() -> showMainView());

      primaryStage.setScene(loginView.getScene());
      primaryStage.show();
    }

    private void showMainMenu() {
      MainView mainView = new MainView();

      //TODO: Implement encryptionController
      EncryptionController encryptionController = new EncryptionController(mainView, encryptionService);

      primaryStage.setScene(mainView.getScene());
      primaryStage.show();
    }

    public static void main(String[] args) {
      launch(args);
    }
}