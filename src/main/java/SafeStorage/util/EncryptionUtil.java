package SafeStorage.util;

//TODO: Fix package naming in the lib, is stringCryption, should be StringCryption.
import main.java.stringCryption.StringCryption;

public class EncryptionUtil {

  private final StringCryption stringCryption;

  public EncryptionUtil() {
    this.stringCryption = new StringCryption();
  }

  public String encrypt(String content, int encryptionLevel) {
    return stringCryption.encryptAtChosenLevel(content, encryptionLevel);
  }

  public String decrypt(String encryptedContent, int encryptionLevel) {
    return stringCryption.decryptAtChosenLevel(encryptedContent, encryptionLevel);
  }
}
