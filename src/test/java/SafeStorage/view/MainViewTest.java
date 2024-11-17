package test.java.SafeStorage.view;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.Scene;
import javafx.stage.Stage;
import SafeStorage.view.MainView;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import static org.testfx.matcher.control.ComboBoxMatchers.hasSelectedItem;

public class MainViewTest extends ApplicationTest {
    
  private MainView mainView;

  @Override
  public void start(Stage stage) {
    mainView = new MainView();
    stage.setScene(mainView.getScene());
    stage.show();
  }

  @Test
  void shouldInitializeWithDefaultValues() {
    // Verify default encryption level
    verifyThat("#encryptionLevelBox", hasSelectedItem(1));
        
    // Verify default decryption level
    verifyThat("#decryptionLevelBox", hasSelectedItem(1));
        
    // Verify empty input fields
    verifyThat("#titleField", hasText(""));
    verifyThat("#contentArea", hasText(""));
    verifyThat("#decryptedContentArea", hasText(""));
  }

  @Test
  void shouldHaveCorrectDimensions() {
    Scene scene = mainView.getScene();
    assertEquals(400, scene.getWidth());
    assertEquals(800, scene.getHeight());
  }

  @Test
  void decryptedContentAreaShouldBeReadOnly() {
    assertFalse(mainView.getDecryptedContentArea().isEditable());
  }
}
