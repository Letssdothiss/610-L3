package SafeStorage.util;

//TODO: Fix package naming in the lib, is stringCryption, should be StringCryption.
import main.java.stringCryption.StringCryption;

/**
 * Utility class for encrypting and decrypting content using the StringCryption library.
 * Provides a simplified interface for encryption operations at different security levels.
 */
public class EncryptionUtil {

  private final StringCryption stringCryption;

  public EncryptionUtil() {
    this.stringCryption = new StringCryption();
  }

  public String encrypt(String content, int encryptionLevel) {
    return stringCryption.encryptAtChosenLevel(content, encryptionLevel);
  }

  // Return The decrypted content as a String, or a string of random characters if the level is incorrect.
  public String decrypt(String encryptedContent, int encryptionLevel) {
    return stringCryption.decryptAtChosenLevel(encryptedContent, encryptionLevel);
  }
}
