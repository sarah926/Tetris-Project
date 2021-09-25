package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquarePiece extends Piece{
    //doesnt need to rotate because a square
    @Override
    public void rotate() {
        super.rotate();
    }
    //initialize shape
    public SquarePiece(int x, int y){
        this.squares[0] = new Rectangle(x,y,GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        this.squares[1] = new Rectangle(x+GameBoard.BLOCKSIZE,y,GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        this.squares[2] = new Rectangle(x,y+GameBoard.BLOCKSIZE,GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        this.squares[3] = new Rectangle(x+GameBoard.BLOCKSIZE,y+GameBoard.BLOCKSIZE,GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        for(int i = 0; i<4;i++) {
            this.squares[i].setFill(Color.FORESTGREEN);
        }
    }


}