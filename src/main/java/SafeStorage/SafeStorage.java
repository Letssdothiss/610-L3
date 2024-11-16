package SafeStorage;

import javafx.application.Application;
import javafx.stage.Stage;
import SafeStorage.view.MainView;
import SafeStorage.controller.EncryptionController;
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

    encryptionUtil = new EncryptionUtil();
    encryptionService = new EncryptionService(encryptionUtil);

    showMainView();
  }

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