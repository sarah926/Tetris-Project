package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BackSPiece extends Piece {
    //initialize piece and state
    boolean horizontal = true;

    public BackSPiece(int x, int y) {
        this.squares[0] = new Rectangle(x, y, GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        this.squares[1] = new Rectangle(x + GameBoard.BLOCKSIZE, y, GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        this.squares[2] = new Rectangle(x + GameBoard.BLOCKSIZE, y + GameBoard.BLOCKSIZE, GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        this.squares[3] = new Rectangle(x + (2 * GameBoard.BLOCKSIZE), y + GameBoard.BLOCKSIZE, GameBoard.RENDERSIZE, GameBoard.RENDERSIZE);
        for (int i = 0; i < 4; i++) {
            this.squares[i].setFill(Color.DARKBLUE);
        }
    }

    @Override
    public void rotate() {
        super.rotate();
        horizontal = !horizontal;
        //changes x and y coordinates based on which position rotating to
        if (horizontal) {
            this.squares[0].setX(this.squares[0].getX() - GameBoard.BLOCKSIZE);

            this.squares[1].setY(this.squares[1].getY() - GameBoard.BLOCKSIZE);

            this.squares[2].setX(this.squares[2].getX() + GameBoard.BLOCKSIZE);

            this.squares[3].setX(this.squares[3].getX() + (2 * GameBoard.BLOCKSIZE));
            this.squares[3].setY(this.squares[3].getY() - GameBoard.BLOCKSIZE);
        } else {
            this.squares[0].setX(this.squares[0].getX() + GameBoard.BLOCKSIZE);

            this.squares[1].setY(this.squares[1].getY() + GameBoard.BLOCKSIZE);

            this.squares[2].setX(this.squares[2].getX() - GameBoard.BLOCKSIZE);

            this.squares[3].setX(this.squares[3].getX() - (2 * GameBoard.BLOCKSIZE));
            this.squares[3].setY(this.squares[3].getY() + GameBoard.BLOCKSIZE);

        }


    }
}

