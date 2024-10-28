package SafeStorage.Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import main.java.stringCryption.StringCryption;

public class Account {

  private String username;
  private String password;
  private int levelOfEncryption = 5;

  //private StringCryption encryptionModule = new StringCryption();

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

  private String encryptUsername(String username, int levelOfEncryption) {
    return null;
  }

  private String encryptPassword(String password) {
    return null;
  }

  public void saveNewAccount() {}

  private String decryptUsername(String encryptedUsername) {
    return null;
  }

  private String decryptPassword(String encryptedPassword) {
    return null;
  }

  public Account loadAccount() {
    return null;
  }

  private boolean verifyAccount() {
    return false;
  }

  private boolean accountExists() {
    return false;
  }

  private void userNameException(String username) {}

  private void passwordException(String password) {}

  private void accountDoesNotExistException() {}

  private void invalidPasswordException() {}

  private void invalidUsernameException() {}
}
