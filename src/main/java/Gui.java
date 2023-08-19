import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;


public class Gui extends Application {
    
    private TextField proxyTextField;
    private Label selectedProfileLabel;
    private ChoiceBox<String> platformChoiceBox;
    private Button startButton;
    private Button stopButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pump Dating apps ");
        initializeUI(primaryStage);
    }

    private void initializeUI(Stage primaryStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(10));
        
        createProfileSelectionSection(root);
        createPlatformSelectionSection(root);
        createProxyInputSection(root);
        createButtonsSection(root);
        
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createProxyInputSection(VBox root) {
        HBox proxyBox = new HBox(10);
        proxyBox.setAlignment(Pos.CENTER);

        Label proxyLabel = new Label("Proxy:");
        proxyTextField = new TextField();
        proxyTextField.setPrefWidth(200);

        proxyBox.getChildren().addAll(proxyLabel, proxyTextField);
        root.getChildren().add(proxyBox);
    }

    private void createProfileSelectionSection(VBox root) {
        HBox profileSelectionBox = new HBox(10);
        profileSelectionBox.setAlignment(Pos.CENTER);

        Label profileLabel = new Label("Select Profile:");
        Button browseButton = new Button("Browse");
        browseButton.setOnAction(event -> browseDirectory());

        selectedProfileLabel = new Label();

        profileSelectionBox.getChildren().addAll(profileLabel, browseButton);
        root.getChildren().addAll(profileSelectionBox, selectedProfileLabel);
    }

    private void createPlatformSelectionSection(VBox root) {
        HBox platformBox = new HBox(10);
        platformBox.setAlignment(Pos.CENTER);

        Label platformLabel = new Label("Select Platform:");
        platformChoiceBox = new ChoiceBox<>();
        platformChoiceBox.getItems().addAll("Tinder", "Bumble","Established Men", "Badoo" ,"Match.com", "Plenty of Fish", "ALT" , "Flirt","OneNightFriend",  "Zoosk", "elitesingles", "eHarmony", "Gleeden", "Parship"); // Add more platforms if needed

        platformBox.getChildren().addAll(platformLabel, platformChoiceBox);
        root.getChildren().add(platformBox);
    }

    private void createButtonsSection(VBox root) {
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);

        startButton = new Button("Start");
        // TODO: Add functionality

        stopButton = new Button("Stop");
        // TODO: Add functionality

        buttonsBox.getChildren().addAll(startButton, stopButton);
        root.getChildren().add(buttonsBox);
    }

    private void browseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            selectedProfileLabel.setText("Selected Profile: " + directoryPath);
        }
    }
}

