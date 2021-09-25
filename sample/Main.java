package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //makes database
    public static void makeDB(){
        Controller.dataBase();
    }

    //sets scene to start on fxml
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tetris Game Clone");

        Scene scene = new Scene(root,350,475);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //make database and launch game
    public static void main(String[] args) {
        makeDB();
        launch(args);
    }
}
