package Hexgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/**This class is to create a complete hexgon board boundary
 * Created by aprile on 2015/10/5.
 */
public class Board {
    public static ArrayList<javafx.scene.shape.Polygon> createBlackBoundary(){
        Position position=new Position();
        ArrayList<Polygon> blackBoundary=new ArrayList<>();
        for(int i=169;i<217;i++){
            double side=24;
            double distance=Math.sqrt((side*side)-(side/2*side/2));
            if(i==169){
                Point p=position.p[i];
                Polygon polygon=new Polygon();
                Double[] d={distance, side / 2-4, 0.0, -4.0, -distance, side / 2-4, -distance, 3 * side / 2-4};
                polygon.getPoints().addAll(d);
                polygon.setLayoutX(p.x * 20 + 350);
                polygon.setLayoutY(-p.y * 20 + 351);
                polygon.setFill(Color.BLACK);
                blackBoundary.add(polygon);
            }
            for(i=170;i<=176;i++){//
                Point p=position.p[i];
                Polygon polygon1=new Polygon();
                Double[] d={distance,side/2-3,0.0,-3.0,-distance,side/2-3};
                polygon1.getPoints().addAll(d);
                polygon1.setLayoutX(p.x * 20 + 350);
                polygon1.setLayoutY(-p.y * 20 + 351);
                polygon1.setFill(Color.BLACK);
                blackBoundary.add(polygon1);
            }
            if(i==177){
                Point p=position.p[i];
                Polygon polygon=new Polygon();
                Double[] d={distance,3*side/2-3,distance,side/2-3,0.0,-3.0,-distance,side/2-3};
                polygon.getPoints().addAll(d);polygon.setLayoutX(p.x * 20 + 350);
                polygon.setLayoutY(-p.y * 20 + 351);
                polygon.setFill(Color.BLACK);
                blackBoundary.add(polygon);
            }
            for(i=178;i<=184;i++){//B
                Point p=position.p[i];
                Polygon polygon1=new Polygon();
                Double[] d={distance,3*side/2-3,distance,side/2-3,0.0,-3.0};
                polygon1.getPoints().addAll(d);
                polygon1.setLayoutX(p.x * 20 + 350);
                polygon1.setLayoutY(-p.y * 20 + 351);
                polygon1.setFill(Color.BLACK);
                blackBoundary.add(polygon1);
            }
            if(i==185){
                Point p=position.p[i];
                Polygon polygon=new Polygon();
                Double[] d={0.0,2*side-3,distance,3*side/2-3,distance,side/2-3,0.0,-3.0};
                polygon.getPoints().addAll(d);
                polygon.setLayoutX(p.x * 20 + 350);
                polygon.setLayoutY(-p.y * 20 + 351);
                polygon.setFill(Color.BLACK);
                blackBoundary.add(polygon);
            }
            for(i=186;i<=192;i++){//D
                Point p=position.p[i];
                Polygon polygon1=new Polygon();
                Double[] d={0.0,2*side-3,distance,3*side/2-3,distance,side/2-3};
                polygon1.getPoints().addAll(d);
                polygon1.setLayoutX(p.x * 20 + 350);
                polygon1.setLayoutY(-p.y * 20 + 351);
                polygon1.setFill(Color.BLACK);
                blackBoundary.add(polygon1);
            }
            if(i==193){
                Point p=position.p[i];
                Polygon polygon=new Polygon();
                Double[] d={-distance,3*side/2-3,0.0,2*side-3,distance,3*side/2-3,distance,side/2-3};
                polygon.getPoints().addAll(d);
                polygon.setLayoutX(p.x * 20 + 350);
                polygon.setLayoutY(-p.y * 20 + 351);
                polygon.setFill(Color.BLACK);
                blackBoundary.add(polygon);
            }
            for(i=194;i<=200;i++){//
                Point p=position.p[i];
                Polygon polygon1=new Polygon();
                Double[] d={distance,3*side/2-3,0.0,-3.0+2*side,-distance,3*side/2-3};
                polygon1.getPoints().addAll(d);
                polygon1.setLayoutX(p.x * 20 + 350);
                polygon1.setLayoutY(-p.y * 20 + 351);
                polygon1.setFill(Color.BLACK);
                blackBoundary.add(polygon1);
            }
            if(i==201){
                Point p=position.p[i];
                Polygon polygon=new Polygon();
                Double[] d={-distance,side/2-3,-distance,3*side/2-3,0.0,2*side-3,distance,3*side/2-3};
                polygon.getPoints().addAll(d);
                polygon.setLayoutX(p.x * 20 + 350);
                polygon.setLayoutY(-p.y * 20 + 351);
                polygon.setFill(Color.BLACK);
                blackBoundary.add(polygon);
            }
            for(i=202;i<=208;i++){//E
                Point p=position.p[i];
                Polygon polygon1=new Polygon();
                Double[] d={-distance,side/2-3,-distance,3*side/2-3,0.0,2*side-3};
                polygon1.getPoints().addAll(d);
                polygon1.setLayoutX(p.x * 20 + 350);
                polygon1.setLayoutY(-p.y * 20 + 351);
                polygon1.setFill(Color.BLACK);
                blackBoundary.add(polygon1);
            }
            if(i==209){
                Point p=position.p[i];
                Polygon polygon=new Polygon();
                Double[] d={0.0,-3.0,-distance,side/2-3,-distance,3*side/2-3,0.0,2*side-3};
                polygon.getPoints().addAll(d);
                polygon.setLayoutX(p.x * 20 + 350);
                polygon.setLayoutY(-p.y * 20 + 351);
                polygon.setFill(Color.BLACK);
                blackBoundary.add(polygon);
            }
            for(i=210;i<=216;i++){//A orientation
                Point p=position.p[i];
                Polygon polygon1=new Polygon();
                Double[] d = {-distance,3*side/2-3,-distance,side/2-3,0.0,-3.0};
                polygon1.getPoints().addAll(d);
                polygon1.setLayoutX(p.x * 20 + 350);
                polygon1.setLayoutY(-p.y * 20 + 351);
                polygon1.setFill(Color.BLACK);
                blackBoundary.add(polygon1);
            }
        }
        return blackBoundary;
    }
}
