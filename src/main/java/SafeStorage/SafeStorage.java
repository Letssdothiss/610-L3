package SafeStorage;

import javafx.application.Application;
import javafx.stage.Stage;
import SafeStorage.view.LoginView;

public class SafeStorage extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Safe Storage");

        LoginView loginScreen = new LoginView();
        loginScreen.setOnLoginSuccess(() -> showMainMenu());

        primaryStage.setScene(loginScreen.getScene());
        primaryStage.show();
    }

    private void showMainMenu() {
        // TODO: Implement main menu transition
        System.out.println("Login successful! Main menu coming soon...");
    }

    public static void main(String[] args) {
        launch(args);
    }
}