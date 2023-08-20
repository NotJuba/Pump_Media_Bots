import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;

public class Gui extends Application {
    
    private TextField proxyTextField;
    private Label selectedProfileLabel;
    private ChoiceBox<String> platformChoiceBox;
    private Button startButton;
    private Button stopButton;
    
    private Label timespanLabel;
    private HBox swipeDataBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pump Dating apps");
        initializeUI(primaryStage);
        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        primaryStage.getIcons().add(icon);
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
        createSwipeInfoSection(root); 
        createButtonsSection(root);
        
        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createProxyInputSection(VBox root) {
        HBox proxyBox = new HBox(10);
        proxyBox.setAlignment(Pos.CENTER);

        Label proxyLabel = new Label("Proxy:");
        proxyTextField = new TextField();
        proxyTextField.setPrefWidth(200);
        proxyTextField.getStyleClass().add("input-field");

        proxyBox.getChildren().addAll(proxyLabel, proxyTextField);
        root.getChildren().add(proxyBox);
    }

    private void createProfileSelectionSection(VBox root) {
        HBox profileSelectionBox = new HBox(10);
        profileSelectionBox.setAlignment(Pos.CENTER);

        Label profileLabel = new Label("Select Profile:");
        Button browseButton = new Button("Browse");
        browseButton.getStyleClass().add("browse-button");
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
        platformChoiceBox.getItems().addAll("Tinder", "Bumble","Established Men", "Badoo" ,"Match.com", "Plenty of Fish", "ALT" , "Flirt","OneNightFriend",  "Zoosk", "elitesingles", "eHarmony", "Gleeden", "Parship");

        platformBox.getChildren().addAll(platformLabel, platformChoiceBox);
        root.getChildren().add(platformBox);
    }

    private void createButtonsSection(VBox root) {
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);

        startButton = new Button("Start");
        //  I've gotta get every start to link up with the platform and head straight to a script

        stopButton = new Button("Stop");
        // YOO Guven sorry for the bad GUI 

        buttonsBox.getChildren().addAll(startButton, stopButton);
        root.getChildren().add(buttonsBox);
    }

    private void createSwipeInfoSection(VBox root) {
        VBox swipeInfoBox = new VBox(10);
        swipeInfoBox.setAlignment(Pos.CENTER);
    
        timespanLabel = new Label("Timespan: 0 hours"); 
    
        swipeDataBox = new HBox(10);
        swipeDataBox.setAlignment(Pos.CENTER);
    
        Label matchesLabel = new Label("Matches: 0"); 
        Label totalSwipesLabel = new Label("Total Swipes: 0"); 
        Label rightSwipesLabel = new Label("Right Swipes: 0"); 
        Label leftSwipesLabel = new Label("Left Swipes: 0"); 
    
        swipeDataBox.getChildren().addAll(matchesLabel, totalSwipesLabel, rightSwipesLabel, leftSwipesLabel);
    
        swipeInfoBox.getChildren().addAll(timespanLabel, swipeDataBox);
        root.getChildren().add(swipeInfoBox);
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

