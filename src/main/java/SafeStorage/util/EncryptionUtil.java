package SafeStorage.util;

//TODO: Fix package naming in the lib, is stringCryption, should be StringCryption.
import main.java.stringCryption.StringCryption;

/**
 * Utility class for encrypting and decrypting content using the StringCryption library.
 * Provides a simplified interface for encryption operations at different security levels.
 */
public class EncryptionUtil {

  private final StringCryption stringCryption;

  /**
   * Creates a new EncryptionUtil instance.
   * Initializes the underlying StringCryption library.
   */
  public EncryptionUtil() {
    this.stringCryption = new StringCryption();
  }

  /**
   * Encrypts the provided content using the specified encryption level.
   * 
   * @param content - The text content to encrypt.
   * @param encryptionLevel - The level of encryption to apply (1-5).
   * @return The encrypted content as a String.
   */
  public String encrypt(String content, int encryptionLevel) {
    return stringCryption.encryptAtChosenLevel(content, encryptionLevel);
  }

  /**
   * Decrypts the provided content using the specified encryption level.
   * The level must match the one used for encryption.
   * 
   * @param encryptedContent - The encrypted content to decrypt.
   * @param encryptionLevel - The level of encryption used (1-5).
   * @return The decrypted content as a String, or a string of random characters if the level is incorrect.
   */
  public String decrypt(String encryptedContent, int encryptionLevel) {
    return stringCryption.decryptAtChosenLevel(encryptedContent, encryptionLevel);
  }
}
