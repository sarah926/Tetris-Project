package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LinePiece extends Piece{
    //initialize piece and state
    boolean vertical = true;

    public LinePiece(int x, int y){
        this.squares[0] = new Rectangle(x,y,GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        this.squares[1] = new Rectangle(x,y+GameBoard.BLOCKSIZE,GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        this.squares[2] = new Rectangle(x,y+(GameBoard.BLOCKSIZE*2),GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        this.squares[3] = new Rectangle(x,y+(GameBoard.BLOCKSIZE*3),GameBoard.RENDERSIZE,GameBoard.RENDERSIZE);
        for(int i = 0; i<4;i++) {
            this.squares[i].setFill(Color.HOTPINK);
        }
    }

    @Override
    public void rotate(){
        super.rotate();
        //checks if possible to rotate or too close to edge
        if(vertical){
            if(this.squares[0].getX() <= GameBoard.BLOCKSIZE|| this.squares[0].getX() >=(GameBoard.BOARDWIDTH-(2*GameBoard.BLOCKSIZE))){
                return;
            }
        }
        //switches state
        if(vertical){
            this.squares[0].setX(this.squares[0].getX()-(2*GameBoard.BLOCKSIZE));
            this.squares[0].setY(this.squares[0].getY()+(2*GameBoard.BLOCKSIZE));

            this.squares[1].setX(this.squares[1].getX()-GameBoard.BLOCKSIZE);
            this.squares[1].setY(this.squares[1].getY()+(GameBoard.BLOCKSIZE));

            this.squares[3].setX(this.squares[3].getX()+GameBoard.BLOCKSIZE);
            this.squares[3].setY(this.squares[3].getY()-(GameBoard.BLOCKSIZE));
            vertical = false;
        } else {
            this.squares[0].setX(this.squares[0].getX()+(2*GameBoard.BLOCKSIZE));
            this.squares[0].setY(this.squares[0].getY()-(2*GameBoard.BLOCKSIZE));

            this.squares[1].setX(this.squares[1].getX()+GameBoard.BLOCKSIZE);
            this.squares[1].setY(this.squares[1].getY()-(GameBoard.BLOCKSIZE));

            this.squares[3].setX(this.squares[3].getX()-GameBoard.BLOCKSIZE);
            this.squares[3].setY(this.squares[3].getY()+(GameBoard.BLOCKSIZE));
            vertical = true;
        }


    }
}
