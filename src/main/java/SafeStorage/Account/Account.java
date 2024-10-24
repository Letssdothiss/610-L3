package SafeStorage.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Account {

  private String username;
  private String password;
  private String levelOfEncryption;

  public Account(String username, String password) {
    setUsername(username);
    setPassword(password);
  }

  private void setUsername(String username) {
    this.username = username;
  }

  private void setPassword(String password) {
    this.password = password;
  }

  private String encryptUsername(String username) {}

  private String encryptPassword(String password) {}

  public void saveNewAccount() {}

  private String decryptUsername(String encryptedUsername) {}

  private String decryptPassword(String encryptedPassword) {}

  public Account loadAccount() {}

  private boolean verifyAccount() {}

  private boolean accountExists() {}

  private void userNameException(String username) {}

  private void passwordException(String password) {}

  private void accountDoesNotExistException() {}

  private void invalidPasswordException() {}

  private void invalidUsernameException() {}
}
