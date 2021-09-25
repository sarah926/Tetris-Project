package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Controller {
    //creates connection
    String hs;

    public static Connection conn;

    //creates new database
    public static void dataBase() {
        try {
            conn = DriverManager.getConnection("JDBC:sqlite:highscore.db");
            Statement query = conn.createStatement();
            query.execute("CREATE TABLE IF NOT EXISTS scores(hs INTEGER)");
        } catch (SQLException e) {
            System.out.println("exception " + e);
        }

    }

    //button to start game
    @FXML
    Button beginGame;


    //changes screen if begin game button clicked
    @FXML
    public void changeScreen(ActionEvent event) throws Exception {

        Stage stage;
        Parent root;
        if (event.getSource() == beginGame) {
            stage = (Stage) beginGame.getScene().getWindow();
            Game g = new Game();

            root = g;
            Scene scene = new Scene(root);
            g.setUp();
            stage.setScene(scene);
            stage.show();
        } else {
            stage = (Stage) beginGame.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        }

    }



}
