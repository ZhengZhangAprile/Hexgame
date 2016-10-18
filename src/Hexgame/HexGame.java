package Hexgame;

import java.util.ArrayList;
/**
 * Created by aprile on 30/07/2015.
 */
public class HexGame {
    private String crannies="",nooks="",pieces="";
    private static final int CRANNIES = 6;
    private static final int CRANNIE_CHARS = 3;
    private static final int NOOKS = 18;
    private static final int NOOK_CHARS = 4;
    private static final int PIECE_CHARS = 3;
    private static final float SQRT3 = 1.732f;
    /**
     * Construct HexGame from a string describing game state.
     * @param game The initial state of the game, described as a string according to the assignment specification.
     */
    public HexGame(String game) {
        int index=0;
        crannies=game.substring(index,index+=CRANNIE_CHARS*CRANNIES);
        nooks=game.substring(index,index+=NOOK_CHARS*NOOKS);
        pieces=game.substring(index);
    }
    public String getNooks() {
        return nooks;
    }
    public String getPieces() {
        return pieces;
    }
    public String getCrannies() {return crannies;}
    /**
     * Construct HexGame with a random game state that complies with the assignment specification.
     */
    public HexGame() {
        String game="",crannies="",nooks="",pieces="";
        while(!legitimateGame(game)){
            crannies=Cranny.createCrannies();
            nooks=Nook.createNooks();
            pieces=Piece.createPieces(nooks);
            game=crannies+nooks+pieces;
        }
        this.crannies=crannies;
        this.nooks=nooks;
        this.pieces=pieces;
    }
    /**
     * Determine whether a set of crannies are legal according to the assignment specification.
     * @param crannies A string describing the crannies, encoded according to the assignment specification.
     * @return True if the crannies are correctly encoded and in legal positions, according to the assignment specification.
     */
    public static boolean legitimateCrannies(String crannies) {
            String[] slist = new String[6];
            slist[0] = crannies.substring(0, 3);
            slist[1] = crannies.substring(3, 6);
            slist[2] = crannies.substring(6, 9);
            slist[3] = crannies.substring(9, 12);
            slist[4] = crannies.substring(12, 15);
            slist[5] = crannies.substring(15);
            int countA=0,countB=0,countC=0,countD=0,countE=0,countF=0; //to count how many crannies on each side
            if (crannies.length() != 18) { //to check whether the crannies have 18 digits, if not, then it is obviously false
                return false;
            } else if(crannies.length()==18) {// when length is 18, we have to check whether the cranny is actually on the boundary.
                for (int i = 0; i <= 5; i++) {
                    if (slist[i].compareTo("169") < 0 || slist[i].compareTo("216") > 0) {
                        return false;
                    }else{
                            if (slist[i].compareTo("169")>=0&&slist[i].compareTo("176")<=0){
                                countA++;
                            }else if(slist[i].compareTo("177")>=0&&slist[i].compareTo("184")<=0){
                                countB++;
                            }else if(slist[i].compareTo("185")>=0&&slist[i].compareTo("192")<=0){
                                countC++;
                            }else if(slist[i].compareTo("193")>=0&&slist[i].compareTo("200")<=0){
                                countD++;
                            }else if(slist[i].compareTo("201")>=0&&slist[i].compareTo("208")<=0){
                                countE++;
                            }else if (slist[i].compareTo("209")>=0&&slist[i].compareTo("216")<=0){
                                countF++;
                            }
                    }
                }
            }if(countA == 1&&countB ==1&&countC==1&&countD==1&&countE==1&&countF==1) {// the last step is to check whether there is only one cranny on each boundary
                return true;
            }else
                return false;
        }
    /**
     * This one is to test whether the crannies are on the right positions. In another word, it is to distinguish the legal crannies.There are several cases given in legistimateCrannyTest.

     The easiest one we can identify is the string length. If the length is not 18, the cranny is not legal and it will return false. But if it is 18, then we can keep going.

     The second one is to identify whether they have all six crannies on the right ring, the barriers. We split the long string that has included 6 crannies into six strings and each string contains one cranny. We compare it with the smallest number of the ring and the largest number of the ring, 169 and 216.  If all six crannies are in the range, then it will be on the right ring and return true. Otherwise it will return false.

     The third one is to test whether there are two crannies on the same side of the hexagon. We will count the original state as 0 and when there is one cranny hit one side, we will count 1, after all six crannies test, if there is no counting number bigger or smaller than 1, it will return true. Otherwise it will return false.

     */
    /**
     * Determine whether a set of nooks are legal according to the assignment specification.
     * @param nooks A string describing the nooks, encoded according to the assignment specification.
     * @return True if the nooks are correctly encoded and in legal positions, according to the assignment specification.
     */
    public static boolean legitimateNooks(String nooks) {
        //18 nooks, each nook has 4 chars, total length is 72
        if(nooks.length()!=72)
            return false;
        else{
            //nookList stores 18 nooks position.
            String[] nookList=new String[18];
                for(int i=0;i<18;i++) {
                nookList[i] = nooks.substring(4 * i, 4 * i + 3);
                //judge whether the nook position is beyond the range.
                if(Integer.parseInt(nookList[i])<8||Integer.parseInt(nookList[i])>126)
                    return false;
            }
            //oriList stores the orientations of the 18 nooks
            String[] oriList=new String[18];
            for(int i=0;i<18;i++) {
                oriList[i] = nooks.substring(4 * i + 3, 4 * i + 4);
                //judge whether the orientation is legal or not.
                if (oriList[i].compareTo("A")<0||oriList[i].compareTo("F")>0)
                    return false;
            }
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
            //count the nooks in one triangle.
            int countA=0,countB=0,countC=0,countD=0,countE=0,countF=0;
            int flag=0;// when we retrieval the nookList[] and 6 triangles, the flag is used to express there is one element of nookList is in one of the triangles.
            //travel the nookList and 15 positons in each triangles and legitimate the nook
            for(int i=0;i<18;i++){
                for(int j=0;j<15;j++){
                    //nookList[i] is in triangle a.
                    if(nookList[i].equals(a[j])) {
                        //if the nook position is in triangle a , countA plus 1. And change the position and its adjacent position's number to 000.
                        // In this case, when some nooks adjacent, the second one will be illegal.
                        countA++;
                        flag=1;
                        a[j]="000";
                        for(int k=0;k<6;k++){
                            if(index[j][k]!=0)
                                a[index[j][k]-1]="000";
                        }break;
                    }
                    else if(nookList[i].equals(b[j])) {
                        countB++;
                        flag=1;
                        b[j]="000";
                        for(int k=0;k<6;k++){
                            if(index[j][k]!=0)
                                b[index[j][k]-1]="000";
                        }break;
                    }
                    else if(nookList[i].equals(c[j])) {
                        countC++;
                        flag=1;
                        c[j]="000";
                        for(int k=0;k<6;k++){
                            if(index[j][k]!=0)
                                c[index[j][k]-1]="000";
                        }break;
                    }
                    else if(nookList[i].equals(d[j])) {
                            countD++;
                            flag=1;
                            d[j]="000";
                            for(int k=0;k<6;k++){
                                if(index[j][k]!=0)
                                    d[index[j][k]-1]="000";
                            }break;
                    }
                    else if(nookList[i].equals(e[j])) {
                        countE++;
                        flag=1;
                        e[j]="000";
                        for(int k=0;k<6;k++){
                            if(index[j][k]!=0)
                                e[index[j][k]-1]="000";
                        }break;
                    }
                    else if(nookList[i].equals(f[j])) {
                        countF++;
                        flag=1;
                        f[j]="000";
                        for(int k=0;k<6;k++){
                            if(index[j][k]!=0)
                                f[index[j][k]-1]="000";
                        }break;
                    }
                }
                // if nookList[i] is not equal to none of the postions in 6 triangles, the flag will be 0, the nook can be illegal.
                if(flag==0)
                    return false;
                // the nookList[i] can be found in the 6 triangles. The flag have changed to 1,  we should reset it to 0.
                else flag=0;
            }
            //if 6 counts are all equals 3,the nooks position is legal.
            return (countA==3&&countB==3&&countC==3&&countD==3&&countE==3&&countF==3);
        }
    }
    /**
     * Determine whether a game state is legal according to the assignment specification.
     * @param game A string describing the game state, encoded according to the assignment specification.
     * @return True if the game state is correctly encoded and represents a legal game state, according to the assignment specification.
     */
    public static boolean legitimateGame(String game) {
        if(game.length()!=93&&game.length()!=96&&game.length()!=99&&game.length()!=102)//game's length should be 93,96,99,102
            return false;
        else{
            String crannies=game.substring(0, CRANNIE_CHARS * CRANNIES);
            if(!legitimateCrannies(crannies))//determine whether the crannies are legal
                return false;
            else {
                String nooks=game.substring(CRANNIE_CHARS*CRANNIES,CRANNIE_CHARS*CRANNIES+NOOK_CHARS*NOOKS);
                if(!legitimateNooks(nooks))//determine whether the nooks are legal
                    return false;
                else{
                    ArrayList<String> pieces= new ArrayList<>();
                    int index1=CRANNIE_CHARS*CRANNIES+NOOK_CHARS*NOOKS;
                    int piecesLength = (game.length()-index1)/PIECE_CHARS;
                    for (int i = 0; i < piecesLength; i++) {//store pieces
                        pieces.add(game.substring(index1, index1+PIECE_CHARS));
                        index1 += PIECE_CHARS;
                    }
                    ArrayList<String> nook=new ArrayList<>();
                    int index2=0;
                    int flag=0;
                    for(int i=0;i<NOOKS;i++){
                        nook.add(nooks.substring(index2,index2+NOOK_CHARS-1));
                        index2+=NOOK_CHARS;
                    }
                    for(int i=0;i<pieces.size();i++){
                        for(int j=i+1;j<pieces.size();j++){
                            if(pieces.get(i).equals(pieces.get(j)))
                                return false;
                        }
                        for(int j=0;j<NOOKS;j++){
                            if(pieces.get(i).equals(nook.get(j))){
                                flag=1;
                                break;
                            }
                        }
                        if (flag == 0)
                            return false;
                    }
                    // nooks are all fully connected.
                    ArrayList<Integer> index=new ArrayList<>();
                    index.add(169);
                    for(int i=0;i<index.size();i++){
                        for(int j=0;j<217;j++){
                            if(nextMove.step(game, index.get(i), j)){
                                int signal=1;
                                for(int k=0;k<index.size();k++){
                                    if(index.get(k)==j){
                                        signal=0;
                                        break;
                                    }
                                }
                                if(signal==1)
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
            }
        }
    }
    /**
     * Determine whether a given step is legal according to a given game state and the assignment specification.
     * @param game A string describing the game state, encoded according to the assignment specification.
     * @param from The point from which the step starts
     * @param to The point to which step goes
     * @return True if the move is legal according to the assignment specification.
     */
    public static boolean legitimateStep(String game, int from, int to) {
        String nook = game.substring(18, 90);
        int[] nookNum = new int[18];//nook number
        for (int i = 0; i < nookNum.length; i++) {
            nookNum[i] = Integer.parseInt(nook.substring(4 * i, 4 * i + 3));
        }
        for(int i=0;i<18;i++){
            if(from==nookNum[i])
                return nextMove.step(game,from,to);
        }
        return false;
    }
    /**
     * Return a minimal path from start to goal.
     * @param game A string describing the game state, encoded according to the assignment specification.
     * @param start The point from which the path must start
     * @param goal The point at which the path must end
     * @return An array of integers reflecting a minimal path from start to goal, each integer reflecting a node on the board, starting with the start, and ending with the goal.
     */
    public static int[] minimalPath(String game, int start, int goal) {
        Position position=new Position();
        Point p1=position.p[start];
        PTree<Point> pTree = new PTree<Point>(p1);
        PNode<Point> parent = new PNode<Point>(p1,-1);
//        ArrayList<PNode<Point>> child=new ArrayList<>();
//        child.add(parent);
        for(int i=0;i<217;i++){
            if(nextMove.step(game,start,i)){
                if(i==goal) {
                    int[] min={start,i};
                    return min;
                }
                Point p=position.p[i];
                pTree.addNode(p, parent);
//                child.add(new PNode(p,start));
            }
        }
        int childSize=1;
        for(int j=childSize;j<pTree.getNodeNums();j++){
            childSize=pTree.getNodeNums();
            A:for(int i=0;i<217;i++){
                Point pFrom=(Point)(pTree.getPNode(j).getP());
                if(nextMove.step(game,pFrom.index,i)){
                    if(i==goal){
                        ArrayList<Integer> path=new ArrayList<>();
                        path.add(i);
//                        path.add(pFrom.index);
                        PNode node=pTree.getPNode(j);
                        Point q= (Point) node.getP();
                        int id=q.getIndex();
                        while (id != start)
                        {
                            path.add(id);
                            node=pTree.getParent(node);
                            id=((Point) node.getP()).getIndex();;
                        }
                        path.add(id);
                        int[] min=new int[path.size()];
                        for(int k=path.size()-1;k>=0;k--){
                            min[path.size()-1-k]=path.get(k);
                        }
                        return min;
                    }
                    for(int k=0;k<pTree.getNodeNums();k++){
                        if(pFrom.index==i)
                            continue A;
                    }
                    Point p=position.p[i];
                    pTree.addNode(p, pTree.getPNode(j));
//                    child.add(new PNode<Point>(p,child.get(j).getP().index));
                }
            }
        }
        return null;
    }
    /**
     * Output the state of the game as a string, encoded according to the assignment specification
     * @return A string that reflects the game state, encoded according to the assignment specification.
     */
    public String toString() {
        return crannies+nooks+pieces;
    }
}

