package Hexgame;

import java.util.ArrayList;
/**
 * Created by aprile on 2015/9/28.
 */

/**
 * This class is to build a tree for finding minimal path
 * The root node is the starting point, using nextMove to find the legal position of each steps. Process will finish after it reaches the finishing point.
 */
public class PTree<Point> {
    ArrayList<PNode<Point>> pNodes2=new ArrayList<>();
     int nodeNums;// number of nodes
    /**
     * build a tree using "data".
     *
     * @param data
     */
    public PTree(Point data) {
        this.pNodes2.add(new PNode<Point>(data, -1));
        nodeNums++;

    }
    /**
     * add child node under the node "parent".
     * @param data
     * @param parent
     */
    public void addNode(Point data, PNode<Point> parent) {
                pNodes2.add(new PNode<Point>(data, getPos(parent))) ;
                nodeNums++;
    }
    /**
     *return the parent node of the "pNode".(if the pNode is the root node, return "null".
     * @param pNode
     * @return
     */
    public PNode<Point> getParent(PNode<Point> pNode) {
        if (pNode != null) {
            return pNodes2.get(pNode.getParent());
        }
        return null;
    }
    /**
     * return the position of the "node"
     *
     * @param node
     * @return
     */
    public int getPos(PNode<Point> node) {
        for (int i = 0; i < getNodeNums(); i++) {
            if (pNodes2.get(i).equals(node)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * return the child nodes of the specified node "parent".
     *
     * @return
     */
    public int getNodeNums(){
        return nodeNums;
    }
    public PNode getPNode(int i){
        return pNodes2.get(i);
    }
}
