package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.sql.*;
import java.util.TimerTask;

public class Game extends Parent {
    //creating static variables
    public boolean pause = false;
    public boolean end = false;
    int highest = 0;
    public String input = "";

    //creating a new gameboard
    GameBoard board = new GameBoard();

    //creating text labels
    Text scoreLabel = new Text("Score:");

    Text scoreValue = new Text("0");
    java.util.Timer timer = new java.util.Timer();
    Font f = new Font("arial", 20);

    Rectangle r = new Rectangle();


    Text high = new Text(null);
    Text highs = new Text(null);

    Text over = new Text("GAME OVER");
    Text n = new Text("click n for a new game");

    //empty constructor
    public Game() {

    }

    //set up game
    public void setUp() {
        //get user input
        getScene().setOnKeyReleased(

                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        input = keyEvent.getCode().toString();
                    }
                }
        );
        //set placement of score
        scoreLabel.setX(10);
        scoreLabel.setY(10);
        scoreValue.setX(75);
        scoreValue.setFill(Color.BLACK);
        scoreValue.setY(10);

        //add elements to screen
        getChildren().addAll(board, scoreLabel, scoreValue);

        //timer to continuously move down block
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (pause == false) {
                    board.moveDownIfCan();
                }
            }

        }, 500, 400);

        //animation loop that runs the game
        new AnimationTimer() {

            public void handle(long currentNanoTime) {
                //only runs if the game isn't over
                if (end == false) {

                    //moves based on keyboard input
                    if (input == "LEFT") {
                        board.moveLeftIfCan();
                    } else if (input == "RIGHT") {
                        board.moveRightIfCan();
                    } else if (input == "DOWN") {
                        board.moveDownIfCan();
                    } else if (input == "UP") {
                        board.rotateIfCan();
                    } else if (input == "P") {
                        System.out.println("pause");
                        pauseGame();
                    } else if (input == "R") {
                        System.out.println("resume");
                        resumeGame();
                    } else if(input == "SPACE"){
                        System.out.println("space");
                        while(board.canMoveDown()){
                            board.moveDownIfCan();
                        }

                    }
                }
                //if click n, make new game
                if (input == "N") {
                    board.newBoard();
                    end = false;
                    getChildren().removeAll(high, over, highs, n, r);
                }
                //reset input
                input = "";
                //if not done,
                if (end == false) {
                    board.goToNextIfNeed();
                    board.deleteRowIfNeed();
                }
                //redo score
                scoreValue.setText(String.valueOf(board.score));

                //if game is over
                if (end == false && board.isEndGame() == true) {

                    //creating database
                    try (Statement query = Controller.conn.createStatement()) {
                        try {
                            //add current score to database
                            query.execute("INSERT INTO scores VALUES('" + board.score + "')");
                            ResultSet rs = query.executeQuery("SELECT MAX(hs) FROM scores");
                            highest = rs.getInt("MAX(hs)");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    //set properties of ending screen
                    r.setHeight(GameBoard.BOARDHEIGHT);
                    r.setWidth(GameBoard.BOARDWIDTH);
                    r.setFill(Color.ALICEBLUE);
                    r.setOpacity(.6);
                    getChildren().add(r);
                    //display score and highest score
                    high.setText("your score was " + board.score);
                    highs.setText("your highest score ever is " + highest);

                    over.setX(100);
                    over.setY(150);
                    over.setFont(f);
                    over.toFront();

                    high.setFont(f);
                    high.setX(90);
                    high.setY(200);
                    high.toFront();

                    highs.setX(50);
                    highs.setY(300);
                    highs.setFont(f);
                    highs.toFront();

                    n.setX(80);
                    n.setY(330);
                    n.setFont(f);
                    n.toFront();
                    //add ending elements to screen
                    getChildren().addAll(high, over, highs, n);
                    //reset end to true so it only runs once
                    end = true;
                }

            }
        }.start();
    }

    //set pause to true when game paused
    public void pauseGame() {
        pause = true;

    }

    //set pause to false when resume game
    public void resumeGame() {
        pause = false;
    }

}
