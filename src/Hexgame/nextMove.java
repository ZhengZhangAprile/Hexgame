package Hexgame;

import java.util.ArrayList;

/**
 * Created by aprile on 26/09/15.
 */

/**
 * This class is used to find the next move. Basically is to find the next legal position that the piece can go.
 */
public class nextMove extends HexGame{
    public int from;
    public int to;
    private static final float SQRT3 = 1.732f;
    public nextMove(int from,int to){
        this.from =from;
        this.to=to;
    }

    /**
     * Judge whether the a piece can come out from the nook 'next', orientation is k.
     * @param nookNum
     * @param nookOri
     * @param k
     * @param from
     * @param to
     * @return
     */
    public static boolean nookOut(int[] nookNum,String[] nookOri,float k,int from,int to){
        //kA represent slope factor of orientation A, and its x>x0
        //kB represent slope factor of orientation B, and its x<x0
        //kC represent slope factor of orientation C, and its x<x0
        //kD represent slope factor of orientation D, and its x<x0
        //kE represent slope factor of orientation E, and its x>x0
        //kF represent slope factor of orientation F, and its x>x0
        float kA=-SQRT3,kB=SQRT3,kC=0,kD=-SQRT3,kE=SQRT3,kF=0;
        Position position=new Position();
        Point p1=position.p[from];
        Point p2=position.p[to];
        if(Math.abs(k-kA)<=0.001&&p1.x<p2.x){
            if(onNook(nookNum,from)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==from){
                        if(nookOri[i].equals("A")||nookOri[i].equals("B")||nookOri[i].equals("F"))
                            return true;
                    }
                }
            }
        }
        if(Math.abs(k-kB)<=0.001&&p1.x>p2.x){
            if(onNook(nookNum,from)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==from)
                        if(nookOri[i].equals("A")||nookOri[i].equals("B")||nookOri[i].equals("C"))
                            return true;
                }
            }
        }
        if(Math.abs(k-kC)<=0.001&&p1.x>p2.x){
            if(onNook(nookNum,from)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==from)
                        if(nookOri[i].equals("D")||nookOri[i].equals("B")||nookOri[i].equals("C"))
                            return true;
                }
            }
        }
        if(Math.abs(k-kD)<=0.001&&p1.x>p2.x){
            if(onNook(nookNum,from)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==from)
                        if(nookOri[i].equals("C")||nookOri[i].equals("D")||nookOri[i].equals("E"))
                            return true;
                }
            }
        }
        if(Math.abs(k-kE)<=0.001&&p1.x<p2.x){
            if(onNook(nookNum,from)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==from)
                        if(nookOri[i].equals("D")||nookOri[i].equals("E")||nookOri[i].equals("F"))
                            return true;
                }
            }
        }
        if(Math.abs(k-kF)<=0.001&&p1.x<p2.x){
            if(onNook(nookNum,from)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==from)
                        if(nookOri[i].equals("A")||nookOri[i].equals("E")||nookOri[i].equals("F"))
                            return true;
                }
            }
        }
        return false;
    }

    /**
     * Judge a piece can come into the nook 'to'.
     * @param nookNum
     * @param nookOri
     * @param k
     * @param from
     * @param to
     * @return
     */
    public static boolean nookIn(int[] nookNum,String[] nookOri,float k,int from,int to){
        return nookOut(nookNum,nookOri,k,to,from);
    }

    /**
     * To judge whether or not the two nooks can be connected.
     * @param nookNum
     * @param nookOri
     * @param from
     * @param to
     * @return
     */
    public static boolean nookToNook(int[] nookNum,String[] nookOri,int from,int to){
        //kA represent slope factor of orientation A, and its x>x0
        //kB represent slope factor of orientation B, and its x<x0
        //kC represent slope factor of orientation C, and its x<x0
        //kD represent slope factor of orientation D, and its x<x0
        //kE represent slope factor of orientation E, and its x>x0
        //kF represent slope factor of orientation F, and its x>x0
        float kA=-SQRT3,kB=SQRT3,kC=0,kD=-SQRT3,kE=SQRT3,kF=0;
        return nookIn(nookNum, nookOri, getK(from, to), from,to)&&nookOut(nookNum, nookOri, getK(from, to), from,to);
    }

    /**
     * From and to are all not on nook.
     * @param nookNum
     * @param nookOri
     * @param crannies
     * @param from
     * @param to
     * @return
     */
    public static boolean blockToBlock(int[] nookNum,String[] nookOri,int[] crannies,int from, int to){
        Position position=new Position();
        Point p1=position.p[from];
        Point p2=position.p[to];
           if(!hasNext(nookNum,nookOri,crannies,from,to,getK(from,to))){//if to point has been blocked, the step is legal.
                return true;
            }
        return false;
    }

    /**
     * One of the from and to is nook.
     * @param nookNum
     * @param nookOri
     * @param crannies
     * @param from
     * @param to
     * @return
     */
    public static boolean nookToBlock(int[] nookNum,String[] nookOri,int[] crannies,int from, int to) {
        if(onNook(nookNum, from)){//   from is on nook
            if(nookOut(nookNum, nookOri, getK(from, to), from,to)){//if from to to can come out from the nook "from".
                if(pointOnBoundary(to))// to is on boundary.
                    return true;
                else{// judge whether the "to" point is blocked by a nook, if so the step is legal.
                    return !hasNext(nookNum,nookOri,crannies,from,to,getK(from,to));
                }
            }
        }
        else //to is on nook.
        {
            return nookIn(nookNum, nookOri, getK(from, to), from,to);
        }
     return false;
    }

    /**
     * HasNext judge whether the to is the point blocked by nooks. If the "to" can continue going to the next point, return true.
     * @param nookNum
     * @param nookOri
     * @param crannies
     * @param from
     * @param to
     * @param k
     * @return
     */
    public static boolean hasNext(int[] nookNum,String[] nookOri,int[] crannies,int from,int to,float k){
        double nextX,nextY;
        Position position=new Position();
        Point p1=position.p[from];
        Point p2=position.p[to];
        float kA=-SQRT3,kB=SQRT3,kC=0,kD=-SQRT3,kE=SQRT3,kF=0;
        if(Math.abs(k-kA)<=0.001&&p1.x<p2.x){
            nextX= (float) (p2.x+1);
            nextY= (float) (p2.y-SQRT3);
            int next=-1;
            for(int j=0;j<217;j++){
                if(Math.abs(position.p[j].x-nextX)<=0.001&&Math.abs(position.p[j].y-nextY)<=0.001) {
                    next = j;
                    break;
                }
            }
            if(onNook(nookNum,next)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==next)
                        return !(nookOri[i].equals("A")||nookOri[i].equals("B")||nookOri[i].equals("F"));
                }
            }
            else{
                for(int i=0;i<6;i++){
                    if(crannies[i]==to||to==crannies[i]+1)
                        return false;
                }
                if(next==-1)
                    return false;
                else return true;
            }
        }
        if(Math.abs(k-kB)<=0.001&&p1.x>p2.x) {
            nextX = (float) (p2.x - 1);
            nextY = (float) (p2.y - SQRT3);
            int next = -1;
            for (int j = 0; j < 217; j++) {
                if (Math.abs(position.p[j].x-nextX)<=0.001 && Math.abs(position.p[j].y-nextY)<=0.001) {
                    next = j;
                    break;
                }
            }
            if (onNook(nookNum, next)) {//if next is on nook, and the orientation of the next should be A,B,F
                for (int i = 0; i < 18; i++) {
                    if (nookNum[i] == next)
                        return !(nookOri[i].equals("A")||nookOri[i].equals("B")||nookOri[i].equals("C"));
                }
            }
            else{
                for(int i=0;i<6;i++){
                    if(crannies[i]==to||to==crannies[i]+1)
                        return false;
                }
                if(next==-1)
                    return false;
                else return true;
            }
        }
        if(Math.abs(k-kC)<=0.001&&p1.x>p2.x){
            nextX= (float) (p2.x-2);
            nextY= (float) p2.y;
            int next=-1;
            for(int j=0;j<217;j++){
                if(Math.abs(position.p[j].x-nextX)<=0.001&&Math.abs(position.p[j].y-nextY)<=0.001) {
                    next = j;
                    break;
                }
            }
            if(onNook(nookNum,next)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==next)
                        return !(nookOri[i].equals("B")||nookOri[i].equals("C")||nookOri[i].equals("D"));
                }
            }
            else{
                for(int i=0;i<6;i++){
                    if(crannies[i]==to||to==crannies[i]+1)
                        return false;
                }
                if(next==-1)
                    return false;
                else return true;
            }
        }
        if(Math.abs(k-kD)<=0.001&&p1.x>p2.x) {
            nextX = (float) (p2.x - 1);
            nextY = (float) (p2.y + SQRT3);
            int next = -1;
            for (int j = 0; j < 217; j++) {
                if (Math.abs(position.p[j].x-nextX)<=0.001 && Math.abs(position.p[j].y-nextY)<=0.001) {
                    next = j;
                    break;
                }
            }
            if (onNook(nookNum, next)) {//if next is on nook, and the orientation of the next should be A,B,F
                for (int i = 0; i < 18; i++) {
                    if (nookNum[i] == next)
                        return !(nookOri[i].equals("C")||nookOri[i].equals("E")||nookOri[i].equals("D"));
                }
            }
            else{
                for(int i=0;i<6;i++){
                    if(crannies[i]==to||to==crannies[i]+1)
                        return false;
                }
                if(next==-1)
                    return false;
                else return true;
            }
        }
        if(Math.abs(k-kE)<=0.001&&p1.x<p2.x){
            nextX= (float) (p2.x+1);
            nextY= (float) (p2.y+SQRT3);
            int next=-1;
            for(int j=0;j<217;j++){
                if(Math.abs(position.p[j].x-nextX)<=0.001&&Math.abs(position.p[j].y-nextY)<=0.001) {
                    next = j;
                    break;
                }
            }
            if(onNook(nookNum,next)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==next)
                        return !(nookOri[i].equals("D")||nookOri[i].equals("E")||nookOri[i].equals("F"));
                }
            }
            else{
                for(int i=0;i<6;i++){
                    if(crannies[i]==to||to==crannies[i]+1)
                        return false;
                }
                if(next==-1)
                    return false;
                else return true;
            }
        }
        if(Math.abs(k-kF)<=0.001&&p1.x<p2.x){
            nextX= (float) (p2.x+2);
            nextY= (float) p2.y;
            int next=-1;
            for(int j=0;j<217;j++){
                if(Math.abs(position.p[j].x-nextX)<=0.001&&Math.abs(position.p[j].y-nextY)<=0.001) {
                    next = j;
                    break;
                }
            }
            if(onNook(nookNum,next)){//if next is on nook, and the orientation of the next should be A,B,F
                for(int i=0;i<18;i++){
                    if(nookNum[i]==next)
                        return !(nookOri[i].equals("A")||nookOri[i].equals("E")||nookOri[i].equals("F"));
                }
            }
            else{
                for(int i=0;i<6;i++){
                    if(crannies[i]==to||to==crannies[i]+1)
                        return false;
                }
                if(next==-1)
                    return false;
                else return true;
            }
        }
        return false;
    }

    /**
     * Judge whether the point is on crannies.
     * @param crannies
     * @param to
     * @return
     */
    public static boolean pointOnCrannies(int[] crannies,int to){
        for(int i=0;i<6;i++){
            if(crannies[i]==to)
                return true;
        }
        return false;
    }

    /**
     * Judge whether the point is on boundary.
     * @param to
     * @return
     */
    public static boolean pointOnBoundary(int to){
        if(to>168&&to<217){
            return true;
        }
        return false;
    }

    /**
     * Judge whether the point is on the same boundary.
     * @param from
     * @param to
     * @return
     */
    public static boolean pointOnSameBoundary(int from,int to){
        for(int i=0;i<40;i+=8){
            if((from>168+i)&&from<(178+i)&&(to>168+i)&&(to<178+i))
                return true;
        }
        if((from>208&&from<217||from==169)&&(to>208&&to<217||to==169))
            return true;
        else return false;
    }

    /**
     * Judge the point is on the game board.
     * @param x
     * @param y
     * @return
     */
    public static int pointOnboard(float x, float y){
        Position position=new Position();
        for(int i=0;i<217;i++){
            if(position.p[i].x==x&&position.p[i].y==y)
                return i;
        }
        return -1;
    }

    /**
     * To find the slope factor of the two points"from" to "to" and return the value.
     * @param from
     * @param to
     * @return
     */
    public static float getK(int from, int to){
        Position position=new Position();
        Point p1=position.p[from];
        Point p2=position.p[to];
        float k= (float) ((p1.y-p2.y)/(p1.x-p2.x));
        return k;
    }

    /**
     * To find whether there are blocks between from and to. If so return true.
     * @param nookNum
     * @param crannies
     * @param from
     * @param to
     * @return
     */
    public static boolean hasBlock( int[] nookNum,int[] crannies,int from, int to){
        Position position=new Position();
        Point p1=position.p[from];
        Point p2=position.p[to];
        //if two points are on the same boundary. When the crannies is on the position between from to to, has block is true.
        if(pointOnSameBoundary(from,to)){
            for(int i=0;i<40;i+=8){
                if(from>168+i&&from<178+i&&to>168+i&&to<178+i){
                    for(int j=0;j<6;j++) {
                        if(crannies[j]>168+i&&crannies[j]<177+i){
                            if ((crannies[j] < from && crannies[j] < to) || (crannies[j] >= from && crannies[j] >= to))
                                return false;
                        }
                    }
                }
            }
            if((from>208&&from<217)&&(to>208&&to<217)){
                for(int j=0;j<6;j++) {
                    if(crannies[j]>208&&crannies[j]<217){
                        if (crannies[j] < from && crannies[j] < to || crannies[j] >= from && crannies[j] >= to)
                            return false;
                    }
                }
            }
            else if(from>208&&from<217||to==169){
                for(int j=0;j<6;j++) {
                    if(crannies[j]>208&&crannies[j]<217){
                        if (crannies[j] < from)
                            return false;
                    }
                }
            }
            else if(to>208&&to<217||from==169){
                for(int j=0;j<6;j++) {
                    if(crannies[j]>208&&crannies[j]<217){
                        if (crannies[j] < to)
                            return false;
                    }
                }
            }
        }
        //if two points are not all on the same boundary. When a nook is between the two points, they can't be connected.
        else{
            float k=getK(from,to);
            for(int i=0;i<nookNum.length;i++){
                int nook=nookNum[i];
                Point p3=position.p[nook];
                float k1= (float) ((p3.y-p1.y)/(p3.x-p1.x));
                if(Math.abs(k1-k)<0.001&&(p1.x<p3.x&&p3.x<p2.x||p2.x<p3.x&&p3.x<p1.x))
                    return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Show whether the position is on nooks and return the index of the nook.
     * @param nookNum
     * @param position
     * @return
     */
    public static boolean onNook(int[] nookNum,int position){// position is  from or to
        for(int i=0;i<18;i++){
            if(position==nookNum[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * Judge whether the 18 nooks are fully connected.
     * @param game
     * @param nook
     * @return
     */
    public static boolean fullyConnected(String game,ArrayList<String> nook){
        // nooks are all fully connected.
        ArrayList<Integer> index=new ArrayList<>();
        index.add(169);
        for(int i=0;i<index.size();i++){
            for(int j=0;j<217;j++){
                if(legitimateStep(game,index.get(i),j)){
                    int sigal=0;
                    for(int k=0;k<index.size();k++){
                        if(index.get(k)==j){
                            sigal=0;
                            break;
                        }
                        sigal=1;
                    }
                    if(sigal==1)
                        index.add(j);
                }
            }
        }
        int num=0;
        for(int j=0;j<nook.size();j++){
            for(int i=0;i<index.size();i++){
                if(Integer.parseInt(nook.get(j))==index.get(i))
                    num++;
            }
        }
        if(num==18)
            return true;
         else return false;
    }

    /**
     * Judge whether from can get the point "to".
     * @param game
     * @param from
     * @param to
     * @return
     */
    public static boolean step(String game,int from, int to){
        //kA represent slope factor of orientation A, and its x>x0
        //kB represent slope factor of orientation B, and its x<x0
        //kC represent slope factor of orientation C, and its x<x0
        //kD represent slope factor of orientation D, and its x<x0
        //kE represent slope factor of orientation E, and its x>x0
        //kF represent slope factor of orientation F, and its x>x0
        float kA=-SQRT3,kB=SQRT3,kC=0,kD=-SQRT3,kE=SQRT3,kF=0;
        float k=nextMove.getK(from, to);
        //if the slope between from and to is not in A,B,C,D,E,F orientations, return false.
        if(!(Math.abs(k-kA)<=0.001)&&!(Math.abs(k-kB)<=0.001)&&!(Math.abs(k-kC)<=0.001)&&!(Math.abs(k-kD)<=0.001)&&!(Math.abs(k-kE)<=0.001)&&!(Math.abs(k-kF)<=0.001))
            return false;
        else {
            int[] crannies=new int[6];
            for(int i=0;i<6;i++){
                crannies[i]=Integer.parseInt(game.substring(3*i,3*i+3));
            }
            String nook = game.substring(18, 90);
            int[] nookNum = new int[18];//nook number
            String[] nookOri = new String[18];//nook orientation
            for (int i = 0; i < nookNum.length; i++) {
                nookNum[i] = Integer.parseInt(nook.substring(4 * i, 4 * i + 3));
                nookOri[i] = game.substring( 18+4 * (i + 1)-1,18+4*(i+1));
            }
            //if there are blocks between from and to, return false.
            if(nextMove.hasBlock(nookNum, crannies,from, to))
                return false;
            else {
                if (nextMove.onNook(nookNum, from)) {//From is on nook.
                    if (nextMove.onNook(nookNum, to)) {//TO is on nook.
                        return nextMove.nookToNook(nookNum,nookOri,from, to);
                    } else  //To is not on nook.
                        return nextMove.nookToBlock(nookNum, nookOri, crannies, from, to);
                }
                else {// From is not on nook.
                    if (nextMove.onNook(nookNum, to)) {//TO is on nook.
                        return nextMove.nookToBlock(nookNum, nookOri, crannies, from, to);
                    } else // To is not on nook.
                        return nextMove.blockToBlock(nookNum, nookOri, crannies, from, to);
                }
            }
        }
    }
}
