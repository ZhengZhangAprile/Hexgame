package Hexgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is to display cranny on the board
 * created by aprile
 *
 */
public class Cranny {
    /**
     * this method is to create cranny on the hexgon game board.
     * @param cranny
     * @return
     */
    public static ArrayList<Line> createJavaFXCrannies(String cranny){
        //Create JavaFX crannies
        Position position=new Position();
        ArrayList<Integer> crannies = new ArrayList<Integer>();
        ArrayList<Line> lines = new ArrayList<>();
        //substring a given crannies and add each of them to the crannies Arraylist
        for(int i = 0;i<6;i++){
            String cranny_i = cranny.substring(3*i,3*i+3);
            crannies.add(Integer.parseInt(cranny_i));
        }
        for(int k =169 ;k<=216;k++){
            Point p_k = position.p[k];
            //down below is the crannies
            for(int i = 0;i<6;i++){
                if(crannies.get(i)==k){//this is the condition to compare the crannies number with k
                    //if k is in the range of (209-216), and the range of(185-192),then do the following
                    if((crannies.get(i)>=209&&crannies.get(i)<=216)){
                        Line line = new Line();
                        line.setStartX(20 * p_k.x + 350);
                        line.setStartY(-20 * p_k.y + 350);
                        line.setEndX(20 * p_k.x + 369);
                        line.setEndY(-20 * p_k.y + 360);
                        line.setStroke(Color.BLACK);
                        line.setStrokeWidth(4);
                        lines.add(line);
                    }else if((crannies.get(i)>=169&&crannies.get(i)<=176)){//this is in the range of (169-176,and the range of 193-200)
                        Line line = new Line();
                        line.setStartX(20 * p_k.x + 370);
                        line.setStartY(-20 * p_k.y + 360);
                        line.setEndX(20 * p_k.x + 370);
                        line.setEndY(-20*p_k.y+382);
                        line.setStroke(Color.BLACK);
                        line.setStrokeWidth(3);
                        lines.add(line);
                    }else if((crannies.get(i)>=193&&crannies.get(i)<=200)){//this is in the range of (169-176,and the range of 193-200)
                        Line line = new Line();
                        line.setStartX(20 * p_k.x + 330);
                        line.setStartY(-20 * p_k.y + 362);
                        line.setEndX(20 * p_k.x + 330);
                        line.setEndY(-20*p_k.y+384);
                        line.setStroke(Color.BLACK);
                        line.setStrokeWidth(3);
                        lines.add(line);
                    }
                    else if((crannies.get(i)>=177&&crannies.get(i)<=184)){//this is the range of (177-184 and the range of 201-208)
                        Line line = new Line();
                        line.setStartX(20 * p_k.x + 370);
                        line.setStartY(-20*p_k.y+384);
                        line.setEndX(20 * p_k.x + 350);
                        line.setEndY(-20*p_k.y+395);
                        line.setStroke(Color.BLACK);
                        line.setStrokeWidth(3);
                        lines.add(line);
                    }else if((crannies.get(i)>=185&&(crannies.get(i)<=192))){
                        Line line = new Line();
                        line.setStartX(20 * p_k.x + 330);
                        line.setStartY(-20 * p_k.y + 383);
                        line.setEndX(20 * p_k.x + 350);
                        line.setEndY(-20 * p_k.y + 396);
                        line.setStroke(Color.BLACK);
                        line.setStrokeWidth(3);
                        lines.add(line);
                    }else if((crannies.get(i)>=201&&crannies.get(i)<=208)){//this is the range of (177-184 and the range of 201-208)
                        Line line = new Line();
                        line.setStartX(20 * p_k.x + 350);
                        line.setStartY(-20*p_k.y+348);
                        line.setEndX(20 * p_k.x + 329);
                        line.setEndY(-20*p_k.y+360);
                        line.setStroke(Color.BLACK);
                        line.setStrokeWidth(3);
                        lines.add(line);
                    }
                }
            }
        }
        return lines;
    }

    /**this is to create a crannies in string if this cranny is legal and pass this to the createJavaFXCrannies
     *
     * @return
     */
    public static String createCrannies(){
        String crannies="";
        Random r=new Random();
        do{
            for(int i=0;i<6;i++){
                int[] p={169+i*8,170+i*8,171+i*8,172+i*8,173+i*8,174+i*8,175+i*8,176+i*8};
                crannies+=Integer.toString(p[r.nextInt(8)]);
            }
        }while(!HexGame.legitimateCrannies(crannies));
        return crannies;
    }

}
