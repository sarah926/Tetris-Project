package sample;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GameBoard extends Parent {

    //setting static variables
    public static int BLOCKSIZE = 25;
    public static int BOARDWIDTH = 350;
    public static int BOARDHEIGHT = 475;
    public static int BOARDCOLUMNS = BOARDWIDTH/BLOCKSIZE;
    public static int BOARDROWS = BOARDHEIGHT/BLOCKSIZE;
    public static int RENDERSIZE = BLOCKSIZE-1;
    public Piece currentPiece = null;
    public static Color EMPTYCOLOR = Color.LIGHTCYAN;
    public int score = 0;

    //set up grid
    private static Rectangle[][] mesh = new Rectangle[BOARDCOLUMNS][BOARDROWS];

    //constructor
    public GameBoard(){
        newBoard();
    }
    //creates a new game board
    public void newBoard(){
        //initializes as empty
        for(int i = 0; i<BOARDCOLUMNS; i++){
            for(int j = 0; j<BOARDROWS; j++){
                mesh[i][j] = new Rectangle(i*BLOCKSIZE,j*BLOCKSIZE,BLOCKSIZE,BLOCKSIZE);
                mesh[i][j].setFill(EMPTYCOLOR);
                getChildren().add(mesh[i][j]);
            }
        }
        //initializes score at 0
        score = 0;
        //displays the next piece
        nextPiece();

    }
    //shows the next piece in the middle of the screen
    public void nextPiece(){
        currentPiece = Piece.createRandomPiece(((BOARDCOLUMNS/2)-1)*BLOCKSIZE,0);
        getChildren().addAll(currentPiece.squares);
    }
    //moves left if will not collide
    public void moveLeftIfCan(){
        if(currentPiece.collideIf((-1*BLOCKSIZE),0,mesh)==false){
            currentPiece.moveLeft();
        }

    }
    //moves right if will not collide
    public void moveRightIfCan() {
        if (currentPiece.collideIf(BLOCKSIZE, 0, mesh) == false){
            currentPiece.moveRight();
        }

    }
    //moves down if will not collide
    public void moveDownIfCan(){
        if(currentPiece.collideIf(0,BLOCKSIZE,mesh)==false){
            currentPiece.moveDown();
        }


    }
    //rotates if it can
    public void rotateIfCan() {
        try{
            currentPiece.rotate();
        } catch (Exception e){

        }

    }
    //if the piece is at bottom or is going to collide with bottom, make the next piece
    public void goToNextIfNeed(){
        if(currentPiece.isAtBottom() == true|| currentPiece.collideIf(0,BLOCKSIZE,mesh) == true){
            Rectangle [] squares = currentPiece.getSquares();

            for(Rectangle r : squares) {
                int x = (int)r.getX()/BLOCKSIZE;
                int y = (int)r.getY()/BLOCKSIZE;
                mesh[x][y] = r;
            }
            nextPiece();
        }

    }
    //if the row is full, delete it
    public void deleteRowIfNeed(){
        //checks each row to see if every column is full
        for(int y = BOARDROWS-1; y>0; y--){
            int numFull = 0;
            for(int x = 0; x < BOARDCOLUMNS; x++){
                if(mesh[x][y].getFill() != EMPTYCOLOR){
                    numFull++;
                } else{
                    break;
                }
            }
            //if all columns full, add score and move down everything
            if(numFull == BOARDCOLUMNS){
                score = score + 100;
                System.out.println(score);
                for(int x = 0; x < BOARDCOLUMNS; x++) {
                    mesh[x][y].setVisible(false);
                }
                for(int movey = y; movey > 0;movey--){
                    for(int movex = 0; movex<BOARDCOLUMNS; movex++){
                        mesh[movex][movey-1].setY(mesh[movex][movey-1].getY()+BLOCKSIZE);
                        mesh[movex][movey] = mesh[movex][movey-1];
                    }

                }
                for(int x = 0; x < BOARDCOLUMNS; x++) {
                    mesh[x][0]= new Rectangle(x*BLOCKSIZE, 0, BLOCKSIZE,BLOCKSIZE);
                    mesh[x][0].setFill(EMPTYCOLOR);
                    getChildren().add(mesh[x][0]);
                }

            }
        }

    }
    //resets the board
    public void clearMesh(){
        for(int i = 0; i<BOARDCOLUMNS; i++){
            for(int j = 0; j<BOARDROWS; j++){
                mesh[i][j].setFill(EMPTYCOLOR);
            }
        }
    }
    //checks if game is over if a piece is at the top row
    public boolean isEndGame(){
        for(int i = 0; i< BOARDCOLUMNS; i++){
            if(mesh[i][0].getFill()!= EMPTYCOLOR){
                System.out.println("at top");
                return true;
            }
        }
        return false;
    }


}
