package Hexgame;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

/**This is to draw the nook on the board
 * Created by aprile on 2015/9/29.
 */
public class Nook extends javafx.scene.shape.Polygon {
    public static ArrayList<javafx.scene.shape.Polygon> createJavaFXNook(String nook){
        Position position=new Position();
        String []nooks = new String[18];
        String nookOri[]=new String[18];
        for(int s =0;s<18;s++){
            String nook_s=nook.substring(4*s,4*s+3);
            nookOri[s]=nook.substring(4 * s + 3, 4 * s + 4);
            nooks[s]=nook_s;
        }
        ArrayList<javafx.scene.shape.Polygon> jfxnooks=new ArrayList<>();
        for(int i=0;i<18;i++){
            Point p=position.p[Integer.parseInt(nooks[i])];
            double side=24;
            javafx.scene.shape.Polygon polygon=new javafx.scene.shape.Polygon();
            double distance=Math.sqrt((side*side)-(side/2*side/2));
            //in these sets of if statement, d indicates the sides representing the direction
            if(nookOri[i].equals("A")){
                Double[] d={distance, side / 2-2, 0.0, -2.0, -distance, side / 2-2, -distance, 3 * side / 2-2};
                polygon.getPoints().addAll(d);
            }
            else if(nookOri[i].equals("B")){
                Double[] d={distance,3*side/2-2,distance,side/2-2,0.0,-2.0,-distance,side/2-2};
                polygon.getPoints().addAll(d);
            }
            else if(nookOri[i].equals("C")){
                Double[] d={0.0,2*side-2,distance,3*side/2-2,distance,side/2-2,0.0,-2.0};
                polygon.getPoints().addAll(d);
            }
            else if(nookOri[i].equals("D")){
                Double[] d={-distance,3*side/2-2,0.0,2*side-2,distance,3*side/2-2,distance,side/2-2};
                polygon.getPoints().addAll(d);
            }
            else if(nookOri[i].equals("E")){
                Double[] d={-distance,side/2-2,-distance,3*side/2-2,0.0,2*side-2,distance,3*side/2-2};
                polygon.getPoints().addAll(d);
            }
            else if(nookOri[i].equals("F")){
                Double[] d={0.0,-2.0,-distance,side/2-2,-distance,3*side/2-2,0.0,2*side-2};
                polygon.getPoints().addAll(d);
            }
            polygon.setLayoutX(p.x * 20 + 350);
            polygon.setLayoutY(-p.y * 20 + 350);
            polygon.setFill(Color.BLACK);
            polygon.setStrokeWidth(2);
            jfxnooks.add(polygon);
        }
        return jfxnooks;
    }


    /**
     * This method is to create a string of nook and pass this string to createJavaFXNook
     * @return
     */
    public static String createNooks(){
        Random r=new Random();
        String game,nooks="";
        //create 6 arrays to store 6 triangles' position for nooks put in
        String[] a={"008","020","021","038","039","040","062","063","064","065","092","093","094","095","096"};
        String[] b={"010","023","024","042","043","044","067","068","069","070","098","099","100","101","102"};
        String[] c={"012","026","027","046","047","048","072","073","074","075","104","105","106","107","108"};
        String[] d={"014","029","030","050","051","052","077","078","079","080","110","111","112","113","114"};
        String[] e={"016","032","033","054","055","056","082","083","084","085","116","117","118","119","120"};
        String[] f={"018","035","036","058","059","060","087","088","089","090","122","123","124","125","126"};
        //index array stores each relation between different position. this will be used for avoid adjacent nooks
        int index[][]={{2,3,0,0,0,0},{1,3,4,5,0,0},{1,2,5,6,0,0},{2,5,7,8,0,0},{2,3,4,6,8,9},{3,5,9,10,0,0},{4,8,11,12,0,0},{4,5,7,9,12,13},
                {5,6,8,10,13,14},{6,9,14,15,0,0},{7,12,0,0,0,0,0},{7,8,11,13,0,0},{8,9,12,14,0,0},{9,10,13,15,0,0},{10,14,0,0,0,0}};
        String directions[]={"A","B","C","D","E","F"};
        for(int i=0;i<3;i++){
            String nooksNum;
            do{
                nooksNum =a[r.nextInt(15)];
            }while (nooksNum=="000");
            String nooksDirection= directions[r.nextInt(6)];
            nooks+=(nooksNum+nooksDirection);
            for(int j=0;j<15;j++){
                if(nooksNum==a[j]){
                    a[j]="000";
                    for(int k=0;k<6;k++){
                        if(index[j][k]!=0)
                            a[index[j][k]-1]="000";
                    }
                }
            }
        }
        for(int i=0;i<3;i++){
            String nooksNum;
            do{
                nooksNum =b[r.nextInt(15)];
            }while (nooksNum=="000");
            String nooksDirection= directions[r.nextInt(6)];
            nooks+=(nooksNum+nooksDirection);
            for(int j=0;j<15;j++){
                if(nooksNum==b[j]){
                    b[j]="000";
                    for(int k=0;k<6;k++){
                        if(index[j][k]!=0)
                            b[index[j][k]-1]="000";
                    }
                }
            }
        }
        for(int i=0;i<3;i++){
            String nooksNum;
            do{
                nooksNum =c[r.nextInt(15)];
            }while (nooksNum=="000");
            String nooksDirection= directions[r.nextInt(6)];
            nooks+=(nooksNum+nooksDirection);
            for(int j=0;j<15;j++){
                if(nooksNum==c[j]){
                    c[j]="000";
                    for(int k=0;k<6;k++){
                        if(index[j][k]!=0)
                            c[index[j][k]-1]="000";
                    }
                }
            }
        }
        for(int i=0;i<3;i++){
            String nooksNum;
            do{
                nooksNum =d[r.nextInt(15)];
            }while (nooksNum=="000");
            String nooksDirection= directions[r.nextInt(6)];
            nooks+=(nooksNum+nooksDirection);
            for(int j=0;j<15;j++){
                if(nooksNum==d[j]){
                    d[j]="000";
                    for(int k=0;k<6;k++){
                        if(index[j][k]!=0)
                            d[index[j][k]-1]="000";
                    }
                }
            }
        }
        for(int i=0;i<3;i++){
            String nooksNum;
            do{
                nooksNum =e[r.nextInt(15)];
            }while (nooksNum=="000");
            String nooksDirection= directions[r.nextInt(6)];
            nooks+=(nooksNum+nooksDirection);
            for(int j=0;j<15;j++){
                if(nooksNum==e[j]){
                    e[j]="000";
                    for(int k=0;k<6;k++){
                        if(index[j][k]!=0)
                            e[index[j][k]-1]="000";
                    }
                }
            }
        }
        for(int i=0;i<3;i++){
            String nooksNum;
            do{
                nooksNum =f[r.nextInt(15)];
            }while (nooksNum=="000");
            String nooksDirection= directions[r.nextInt(6)];
            nooks+=(nooksNum+nooksDirection);
            for(int j=0;j<15;j++){
                if(nooksNum==f[j]){
                    f[j]="000";
                    for(int k=0;k<6;k++){
                        if(index[j][k]!=0)
                            f[index[j][k]-1]="000";
                    }
                }
            }
        }
        return nooks;
    }
}
