package writ1co2system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage startPageStage;

    private static Scene scene;

    private static MyServer serverInstance;

    @Override
    public void start(Stage primaryStage) throws IOException {

        startPageStage = primaryStage;

        // Creating an instance of the server
        Thread serverThread = new Thread(() -> {
            serverInstance = new MyServer();
            serverInstance.startServer();
        });
        serverThread.setDaemon(true); // Set as daemon so it doesn't prevent JVM shutdown
        serverThread.start();

        // Load the startPage on the primary stage
        scene = new Scene(loadFXML("startPage"));
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create stages to simulate each client connection
        createClientStages();
    }

    private void createClientStages() throws IOException {
        for (int i = 1; i <= 4; i++) {
            Stage stage = new Stage();
            stage.setScene(new Scene(loadFXML("startPage")));
            stage.show();
        }
    }

    public static MyServer getServerInstance() {
        return serverInstance;
    }

    public static void setRoot(Stage stage, String fxml) throws IOException {
        stage.setScene(new Scene(loadFXML(fxml)));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openStartPageOnNewStage() throws IOException {
        Stage newStage = new Stage();
        newStage.setScene(new Scene(loadFXML("startPage")));
        newStage.show();
    }

    public static void openCsvPageOnNewStage() throws IOException {
        Stage newStage = new Stage();
        newStage.setScene(new Scene(loadFXML("csvPage")));
        newStage.show();
    }

    public static Stage getStartPageStage() {
        return startPageStage;
    }
}