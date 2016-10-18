package Hexgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

/**
 * Created by aprile on 2015/9/27.
 */

/**
 * This class is to set up a piece which we need to play it in the game.
 */
public class Piece extends Circle{
    public Piece(String piece,String nook){
        Position position=new Position();
        String []nooks = new String[18];
        String nookOri[]=new String[18];
        for(int s =0;s<18;s++){
            String nook_s=nook.substring(4*s,4*s+3);
            nookOri[s]=nook.substring(4*s+3,4*s+4);
            nooks[s]=nook_s;
        }
        // to draw the hexagon with the pattern, form small one in the middle to the bigger one
        int pieceNum=Integer.parseInt(piece);
        for(int i=0;i<18;i++){
            if(pieceNum==Integer.parseInt(nooks[i])){
                Point p=position.p[Integer.parseInt(nooks[i])];
                this.setCenterX(20 * p.x + 350);
                this.setCenterY(-20 * p.y+ 370);
                this.setRadius(12.0);
                this.setFill(Color.BLUE);
            }
        }
    }

    public static String createPieces(String nooks){
        String pieces="";
        Random r=new Random();
        String[] nook=new String[18];
        for(int i=0;i<18;i++){
            nook[i]=nooks.substring(4*i,4*i+3);
        }
        pieces=nook[r.nextInt(18)];
        return pieces;
    }
}
