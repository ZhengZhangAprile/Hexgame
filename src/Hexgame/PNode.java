package Hexgame;

/**
 * Created by aprile on 2015/9/28.
 */
public class PNode<Point>  {
    private Point p;// node data
    private int parent;// position of parent
    public PNode(Point p) {
        this.p = p;
    }
    public PNode(Point p, int parent) {
        this.p = p;
        this.parent = parent;
    }

    /**
     * return Point p
     * @return
     */
    public Point getP() {
        return p;
    }

    /**
     * Set Point p
     * @param p
     */
    public void setP(Point p) {
        this.p = p;
    }

    /**
     * Get the parent of the node
     * @return
     */
    public int getParent() {
        return parent;
    }

    /**
     * Set the parent of the node
     * @param parent
     */
    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PNode<Point> other = (PNode<Point>) obj;
        if (this.p == null) {
            if (other.p != null)
                return false;
        } else if (!(p==other.p))
            return false;
        if (parent != other.parent)
            return false;
        return true;
    }
}
