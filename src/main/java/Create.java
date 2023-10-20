import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Collections;

public class Create extends Application {

  private static final String CSS_FILE_PATH = "/style.css";
  private TextField proxyTextField;
  private ChoiceBox<String> directoryChoiceBox;
  private Button startButton;
  private Button stopButton;
  private WebDriverManager webDriverManager;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Open Chrome with Stored Cookies and Proxy");
    initializeUI(primaryStage);
    webDriverManager = new WebDriverManager();
  }

  private void initializeUI(Stage primaryStage) {
    VBox root = new VBox();
    root.setAlignment(Pos.TOP_CENTER);
    root.setSpacing(40);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    root.setPadding(new Insets(20));

    createProxyInputSection(root);
    createDirectorySelectionSection(root);
    createButtonsSection(root);

    Scene scene = new Scene(root, 800, 600);
    scene.getStylesheets().add(getClass().getResource(CSS_FILE_PATH).toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void createProxyInputSection(VBox root) {
    HBox proxyBox = new HBox();
    proxyBox.setId("proxy-box");
    proxyBox.setAlignment(Pos.CENTER);

    Label proxyLabel = new Label("Proxy:");
    proxyTextField = new TextField();
    proxyTextField.getStyleClass().add("text-field");

    proxyBox.getChildren().addAll(proxyLabel, proxyTextField);
    root.getChildren().add(0, proxyBox);
  }

  private void createDirectorySelectionSection(VBox root) {
    HBox directorySelectionBox = new HBox();
    directorySelectionBox.setId("directory-selection-box");
    directorySelectionBox.setAlignment(Pos.CENTER);
    // directorySelectionBox.setSpacing(10);

    Label directoryLabel = new Label("Select Profile:");
    directoryChoiceBox = new ChoiceBox<>();
    directoryChoiceBox.getStyleClass().add("choice-box");
    // directoryChoiceBox.setPrefWidth(200);

    Button browseButton = new Button("Browse");
    browseButton.getStyleClass().add("button");
    browseButton.setOnAction(event -> browseDirectory());

    directorySelectionBox.getChildren().addAll(directoryLabel, directoryChoiceBox, browseButton);
    root.getChildren().addAll(directorySelectionBox);
  }

  private void createButtonsSection(VBox root) {
    HBox buttonsBox = new HBox();
    buttonsBox.setId("buttons-box");
    buttonsBox.setAlignment(Pos.CENTER);
    // buttonsBox.setSpacing(10);

    startButton = new Button("Start");
    startButton.getStyleClass().add("button");
    startButton.setOnAction(event -> {
      if (webDriverManager.getDriver() == null) {
        start();
      }
    });

    stopButton = new Button("Stop");
    stopButton.getStyleClass().add("button");
    stopButton.setOnAction(event -> {
      if (webDriverManager.getDriver() != null) {
        la();
      }
    });
    stopButton.setDisable(true);

    buttonsBox.getChildren().addAll(startButton, stopButton);
    root.getChildren().add(buttonsBox);
  }

  private void browseDirectory() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    File selectedDirectory = directoryChooser.showDialog(null);
    if (selectedDirectory != null) {
      String directoryPath = selectedDirectory.getAbsolutePath();
      directoryChoiceBox.getItems().add(directoryPath);
      directoryChoiceBox.setValue(directoryPath);
    }
  }

  private void Create() {
    String proxyStr = proxyTextField.getText();
    String userDataDir = directoryChoiceBox.getValue();

    ChromeOptions options = new ChromeOptions();
    options.setAcceptInsecureCerts(true);

    Proxy proxy = new Proxy();
    proxy.setHttpProxy(proxyStr);
    proxy.setSslProxy(proxyStr);
    options.setProxy(proxy);

    options.addArguments("--disable-blink-features=AutomationControlled");
    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    options.setExperimentalOption("useAutomationExtension", null);
    options.addArguments("window-size=1920,1080");
    options.addArguments("--user-data-dir=" + userDataDir);

    webDriverManager.setDriver(new ChromeDriver(options));
  }

  private void start() {
    Create();

    startButton.setDisable(true);
    stopButton.setDisable(false);
  }

  private void la() {
    webDriverManager.closeWebDriver();
    startButton.setDisable(false);
    stopButton.setDisable(true);
  }

  private class WebDriverManager {
    private WebDriver driver;

    public synchronized void initializeWebDriver(String proxyStr, String userDataDir) {
      if (driver == null) {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyStr);
        proxy.setSslProxy(proxyStr);
        options.setProxy(proxy);

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", null);
        options.addArguments("window-size=1920,1080");
        options.addArguments("--user-data-dir=" + userDataDir);

        driver = new ChromeDriver(options);
      }
    }

    public synchronized void closeWebDriver() {
      if (driver != null) {
        driver.quit();
        driver = null;
      }
    }

    public synchronized WebDriver getDriver() {
      return driver;
    }

    public synchronized void setDriver(WebDriver driver) {
      this.driver = driver;
    }
  }
}
