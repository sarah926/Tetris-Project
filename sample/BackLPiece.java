package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BackLPiece extends Piece {
    //initialize position at 0 and creates piece
    int position = 0;

    public BackLPiece(int x, int y) {
        this.squares[0] = new Rectangle(x + GameBoard.BLOCKSIZE, y, GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        this.squares[1] = new Rectangle(x + GameBoard.BLOCKSIZE, y + (GameBoard.BLOCKSIZE), GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        this.squares[2] = new Rectangle(x + GameBoard.BLOCKSIZE, y + (2 * GameBoard.BLOCKSIZE), GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        this.squares[3] = new Rectangle(x, y + (2 * GameBoard.BLOCKSIZE), GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        for (int i = 0; i < 4; i++) {
            this.squares[i].setFill(Color.RED);
        }
    }

    //rotates piece
    @Override
    public void rotate() {
        super.rotate();
        //checks if will rotate off screen before rotates
        if (position == 2) {
            if ((this.squares[0].getX()) <= 0) {
                return;
            }
        } else if (position == 0) {
            if ((this.squares[0].getX()) >= (GameBoard.BOARDWIDTH - GameBoard.BLOCKSIZE)) {
                return;
            }
        }
        //changes position by 1
        position = position + 1;

        if (position > 3) {
            position = 0;
        }
        //changes x and y coordinates based on which position rotating to
        if (position == 0) {

            this.squares[0].setX(this.squares[0].getX() - GameBoard.BLOCKSIZE);
            this.squares[0].setY(this.squares[0].getY() - GameBoard.BLOCKSIZE);

            this.squares[2].setX(this.squares[2].getX() + GameBoard.BLOCKSIZE);
            this.squares[2].setY(this.squares[2].getY() + GameBoard.BLOCKSIZE);

            this.squares[3].setY(this.squares[3].getY() + (2 * GameBoard.BLOCKSIZE));

        } else if (position == 1) {
            this.squares[0].setX(this.squares[0].getX() - GameBoard.BLOCKSIZE);
            this.squares[0].setY(this.squares[0].getY() + GameBoard.BLOCKSIZE);

            this.squares[2].setX(this.squares[2].getX() + GameBoard.BLOCKSIZE);
            this.squares[2].setY(this.squares[2].getY() - GameBoard.BLOCKSIZE);

            this.squares[3].setX(this.squares[3].getX() + (2 * GameBoard.BLOCKSIZE));


        } else if (position == 2) {
            this.squares[0].setX(this.squares[0].getX() + GameBoard.BLOCKSIZE);
            this.squares[0].setY(this.squares[0].getY() + GameBoard.BLOCKSIZE);

            this.squares[2].setX(this.squares[2].getX() - GameBoard.BLOCKSIZE);
            this.squares[2].setY(this.squares[2].getY() - GameBoard.BLOCKSIZE);

            this.squares[3].setY(this.squares[3].getY() - (2 * GameBoard.BLOCKSIZE));


        } else {

            this.squares[0].setX(this.squares[0].getX() + GameBoard.BLOCKSIZE);
            this.squares[0].setY(this.squares[0].getY() - GameBoard.BLOCKSIZE);

            this.squares[2].setX(this.squares[2].getX() - GameBoard.BLOCKSIZE);
            this.squares[2].setY(this.squares[2].getY() + GameBoard.BLOCKSIZE);

            this.squares[3].setX(this.squares[3].getX() - (2 * GameBoard.BLOCKSIZE));

        }


    }

}


