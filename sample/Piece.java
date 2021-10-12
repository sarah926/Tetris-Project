package sample;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Piece {

    //create array of 4 squares that will be the piece
    public Rectangle[] squares = new Rectangle[4];

    //creates a random piece based on random number generator
    public static Piece createRandomPiece(int x, int y) {
        int result = (int) (Math.random() * 100);
        if (result < 15) {
            return new LPiece(x, y);
        } else if (result < 30) {
            return new BackLPiece(x, y);
        } else if (result < 45) {
            return new SquarePiece(x, y);
        } else if (result < 60) {
            return new SPiece(x, y);
        } else if (result < 75) {
            return new BackSPiece(x, y);
        } else if (result < 90) {
            return new TPiece(x, y);
        } else {
            return new LinePiece(x, y);
        }
    }

    //checks if the piece is at the bottom
    public boolean isAtBottom() {
        for (int i = 0; i < 4; i++) {
            if (this.squares[i].getY() == (GameBoard.BOARDHEIGHT - GameBoard.BLOCKSIZE)) {
                return true;
            }
        }

        return false;
    }

    //rotate function
    public void rotate() {

    }

    //moves piece left
    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            if (this.squares[i].getX() == 0) {
                return;
            }
        }
        for (int i = 0; i < 4; i++) {
            this.squares[i].setX(this.squares[i].getX() - GameBoard.BLOCKSIZE);
        }
    }

    //moves piece right
    public void moveRight() {
        for (int i = 0; i < 4; i++) {
            if (this.squares[i].getX() == (GameBoard.BOARDWIDTH - GameBoard.BLOCKSIZE)) {
                return;
            }
        }
        for (int i = 0; i < 4; i++) {
            this.squares[i].setX(this.squares[i].getX() + GameBoard.BLOCKSIZE);
        }
    }

    //moves down if not at bottom
    public void moveDown() {
        if (isAtBottom() == true) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            this.squares[i].setY(this.squares[i].getY() + GameBoard.BLOCKSIZE);
        }
    }

    //getter
    public Rectangle[] getSquares() {
        return this.squares;
    }

    //checks for collision based on which way trying to move
    public boolean collideIf(int xChange, int yChange, Rectangle[][] mesh) {
        for (Rectangle r : this.squares) {
            int x = (int) (r.getX() + xChange) / GameBoard.BLOCKSIZE;
            int y = (int) (r.getY() + yChange) / GameBoard.BLOCKSIZE;
            if (x < 0) {
                return true;
            }
            if (x >= (GameBoard.BOARDWIDTH / GameBoard.BLOCKSIZE)) {
                return true;
            }
            if (mesh[x][y].getFill() != GameBoard.EMPTYCOLOR) {
                return true;
            }
        }
        return false;
    }

}
