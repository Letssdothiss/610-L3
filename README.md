# SafeStorage Desktop Application
# Version 0.1.0

## Reflections & Module URL

[Reflections](./REFLECTIONS.md)

[Module Repository](https://github.com/Letssdothiss/610-L2/tree/feature/refactor)

## Beta

The application is fully functional, usable and tested, but the process to actually run this on your local machine is a bit more complex, therefore i will call this a Beta version and keep the major version at 0 for the time being.

Version 1.0 is still some time away so this could be considered "early access" or "Beta", the two major changes that need to happen before an official 1.0 release is the migration to maven that is ongoing and solving the issue of creating a functional cross-platform installer.

## Notice Board

The [notice board](./NoticeBoard.md) contains information about upcoming changes or updates that may be noteworthy.

## Change Log

The [change log](./ChangeLog.md) lists important changes that have been made.

## Introduction

The `SafeStorage` desktop application is a secure text encryption and storage solution designed for personal use. Built with JavaFX, it provides a user-friendly interface for encrypting sensitive information such as passwords, notes, and personal data using multiple levels of encryption complexity.

This application allows users to create encrypted entries with custom titles and content, choosing from five different encryption levels (1-5) for enhanced security. Each entry can only be decrypted using the correct encryption level, ensuring that sensitive information remains protected even if the application files are accessed by unauthorized parties.

For instance, you can store your online passwords, personal notes, or confidential information in encrypted form. The application persists your encrypted entries locally, and you can decrypt them anytime by selecting the correct encryption level, ensuring your data remains secure and accessible only to you.

The application follows Clean Code principles and MVC architecture, making it maintainable and extensible for future development.

## Unique Selling Points (USP)

- **Multiple Encryption Levels:** Choose from five different levels of encryption complexity (1-5), allowing users to balance security needs with performance requirements.
- **User-Friendly Interface:** Clean JavaFX-based GUI that makes encryption and decryption operations intuitive and accessible.
- **Persistent Storage:** Encrypted entries are automatically saved to disk and restored when the application restarts.
- **Secure by Design:** Each entry requires the correct encryption level for decryption, adding an extra layer of security.
- **Clean Architecture:** Built following MVC pattern and Clean Code principles for maintainability and extensibility.
- **Comprehensive Testing:** Extensive test coverage including unit tests, integration tests, and UI tests using JUnit 5 and TestFX.

## Prerequisites

- **Java 21 or higher** - Required for running the application.
- **JavaFX SDK 23.0.1 or higher** - Required for the GUI components.
- **StringCryption Library** - Included in the project dependencies.

## How to Use

### Option 1: Clone the Entire Project

1. **Navigate to Your Desired Directory:**
   - Open a terminal and navigate to the directory where you want to clone the repository. For example:
   ```sh
   cd path/to/your/desired/directory
   ```

2. **Clone the Repository:**
   - Press the `<> Code` button in the GitHub repository and copy the URL.
   - Or use the following command to clone the repository:
   ```sh
   git clone https://github.com/yourusername/SafeStorage.git
   ```

3. **Navigate to the Cloned Repository:**
   - Change the directory to the cloned repository:
   ```sh
   cd SafeStorage
   ```

4. **Setup JavaFX SDK:**
   - Download JavaFX SDK 23.0.1 from [OpenJFX](https://openjfx.io/).
   - Extract the SDK to the `lib` folder of the project.
   - Rename the extracted folder to `javafx-sdk-23.0.1`.

### Option 2: Build and Run from Source

1. **Build the Application:**
   - Use the provided build script to compile the application:
   ```sh
   ./run.sh
   ```

2. **Run the Application:**
   ```sh
   java --module-path "lib/javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib/*" SafeStorage.SafeStorage
   ```

**Note:** This is a course project. For production use, you would typically create a JAR file and distribute it via releases, but for this assignment, building from source is the recommended approach.

### Building from Source

1. **Compile the Application:**
   ```sh
   ./run.sh
   ```

2. **Run Tests:**
   ```sh
   java --module-path "lib/javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.fxml --add-opens javafx.graphics/com.sun.javafx.application=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.binding=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.event=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED -cp "bin;lib/*" org.junit.platform.console.ConsoleLauncher --scan-classpath
   ```

## Application Usage

### Main Interface

The SafeStorage application provides a clean, intuitive interface with the following components:

- **Title Field:** Enter a descriptive title for your encrypted entry.
- **Content Area:** Enter the sensitive information you want to encrypt.
- **Encryption Level Dropdown:** Choose encryption level (1-5) for your entry.
- **Encrypt Button:** Encrypt and save your entry.
- **Entries List:** View all your saved encrypted entries.
- **Decryption Level Dropdown:** Select the correct level to decrypt an entry.
- **Decrypt Button:** Decrypt the selected entry.
- **Decrypted Content Area:** View the decrypted content.

### Step-by-Step Usage

1. **Creating an Encrypted Entry:**
   - Enter a title in the "Title" field.
   - Enter your sensitive content in the "Content" area.
   - Select an encryption level (1-5) from the dropdown.
   - Click "Encrypt" to save the entry.

2. **Decrypting an Entry:**
   - Select an entry from the "Entries" list.
   - Choose the correct decryption level (must match the encryption level used when encrypted.).
   - Click "Decrypt" to view the content.
   - The decrypted content will appear in the "Decrypted Content" area.

3. **Managing Entries:**
   - All entries are automatically saved to disk.
   - Entries persist between application sessions.
   - Each entry can only be decrypted with its original encryption level.

### Example Usage

```java
// The application handles encryption/decryption internally
// Users interact through the GUI:

// 1. Enter title: "My Password"
// 2. Enter content: "mySecretPassword123"
// 3. Select encryption level: 3
// 4. Click Encrypt
// 5. Entry is saved and appears in the list

// To decrypt:
// 1. Select "My Password" from the list
// 2. Select decryption level: 3
// 3. Click Decrypt
// 4. Content "mySecretPassword123" appears
```

## Architecture

### MVC Pattern Implementation

The application follows the Model-View-Controller (MVC) architectural pattern:

- **Model:** `EncryptedEntry` - Represents the data structure for encrypted entries.
- **View:** `MainView` - Handles the JavaFX user interface.
- **Controller:** `EncryptionController` - Manages user interactions and coordinates between model and view.

### Key Components

- **SafeStorage:** Main application class and entry point.
- **EncryptionService:** Business logic for encryption, decryption, and data management.
- **EncryptionUtil:** Wrapper for the StringCryption library.
- **FileHandler:** Handles persistent storage of encrypted entries.
- **MainView:** JavaFX user interface implementation.

### Dependencies

- **StringCryption Library:** Core encryption/decryption functionality.
- **JavaFX:** GUI framework.
- **JUnit 5:** Unit testing framework.
- **TestFX:** UI testing framework.

## Testing

The application includes comprehensive test coverage:

### Test Categories

- **Unit Tests:** Individual component testing (EncryptedEntry, EncryptionUtil, EncryptionService, FileHandler).
- **Integration Tests:** Cross-component functionality testing.
- **UI Tests:** User interface testing using TestFX.

### Running Tests

```sh
# Run all tests
java --module-path "lib/javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.fxml --add-opens javafx.graphics/com.sun.javafx.application=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.binding=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.event=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED -cp "bin;lib/*" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

### Test Results

- **34/35 tests pass** (97% success rate).
- **1 TestFX UI test** has a known isolation issue (documented in TESTREPORT.md).
- **All core functionality** is thoroughly tested and working.

## Security Considerations

### Encryption Levels

- **Level 1:** Basic encryption (fastest).
- **Level 2-4:** Intermediate encryption levels.
- **Level 5:** Highest encryption complexity (most secure).

### Data Protection

- **Local Storage:** All data is stored locally on your machine.
- **Encryption Required:** No data is stored in plain text.
- **Level Verification:** Decryption requires the exact encryption level used.
- **No Network Access:** Application operates entirely offline.

### Best Practices

- **Choose Appropriate Levels:** Use higher levels for more sensitive data.
- **Remember Your Levels:** Keep track of which encryption level you used for each entry.
- **Regular Backups:** Consider backing up your encrypted entries file.
- **Secure Environment:** Run the application in a secure environment.

## Known Issues and Limitations

### TestFX Test Isolation

- **Issue:** One UI test (`handleEncryption_ValidInput`) fails due to TestFX test isolation problems.
- **Impact:** Does not affect application functionality.
- **Status:** Documented in TESTREPORT.md.

### Installation Challenges 

- **Issue:** Creating Windows installer (.msi) with jpackage has proven challenging.
- **Impact:** Application must be run from source or JAR file.
- **Workaround:** Use the provided run script or manual JAR execution.

### Platform Support

- **Current:** Windows (tested).
- **Potential:** macOS and Linux (not tested).
- **Limitation:** Requires JavaFX runtime for each platform.

## Future Enhancements

- **Cross-Platform Installers:** Resolve jpackage issues for easier distribution.
- **Additional Encryption Methods:** Integration with other encryption libraries.
- **Entry Categories:** Organize entries by type (passwords, notes, etc.).
- **Search Functionality:** Find entries by title or content.
- **Export/Import:** Backup and restore encrypted entries.
- **Password Protection:** Application-level password protection.

## Contributions

This is currently a school project so contributions are currently not needed.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

### MIT License
