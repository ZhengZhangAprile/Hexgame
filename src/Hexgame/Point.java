package Hexgame;

/**
 * Created by aprile on 2015/9/25.
 */

import java.util.ArrayList;

/**
 * Point class converts position into actual coordinates with x and y.
 * Using coordinates are useful for finding minimal path.
 */
public class Point {
    float x,y;
    int index;
    public Point(int index,float x,float y){
        this.x=x;
        this.y=y;
        this.index=index;
    }
    public int getIndex(){
        return index;
    }
}
