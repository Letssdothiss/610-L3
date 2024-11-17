package test.java.SafeStorage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;
import SafeStorage.SafeStorage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class SafeStorageTest extends ApplicationTest {

  @Override
  public void start(Stage stage) {
    new SafeStorage().start(stage);
  }

  @Test
  void shouldShowMainViewWithTitle() {
    verifyThat(".label", hasText("Safe Storage"));
  }

  @Test
  void shouldShowAllMainComponents() {
    verifyThat("#titleField", isVisible());
    verifyThat("#contentArea", isVisible());
    verifyThat("#encryptionLevelBox", isVisible());
    verifyThat("#encryptButton", isVisible());
    verifyThat("#entriesList", isVisible());
    verifyThat("#decryptionLevelBox", isVisible());
    verifyThat("#decryptButton", isVisible());
    verifyThat("#decryptedContentArea", isVisible());
  }
}
