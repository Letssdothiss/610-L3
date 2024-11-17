package SafeStorage;

import javafx.application.Application;
import javafx.stage.Stage;
import SafeStorage.view.MainView;
import SafeStorage.controller.EncryptionController;
import SafeStorage.service.EncryptionService;
import SafeStorage.util.EncryptionUtil;

/**
 * Main application class for SafeStorage.
 * Initializes and launches the JavaFX application for secure text storage and encryption.
 * 
 * @version 1.0.0
 */
public class SafeStorage extends Application {

  private Stage primaryStage;
  private EncryptionUtil encryptionUtil;
  private EncryptionService encryptionService;

  /**
   * Starts the JavaFX application.
   * Initializes the encryption utilities and displays the main view.
   * 
   * @param primaryStage The primary stage for this application
   */
  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setTitle("Safe Storage");

    encryptionUtil = new EncryptionUtil();
    encryptionService = new EncryptionService(encryptionUtil);

    showMainView();
  }

  /**
   * Creates and displays the main view of the application.
   * Initializes the controller and connects it to the view.
   */
  private void showMainView() {
    MainView mainView = new MainView();

    EncryptionController encryptionController = new EncryptionController(mainView, encryptionService);

    primaryStage.setScene(mainView.getScene());
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}