package game.Model.Pathfinding;

public class Node {
    public Node parent;
    public int x;
    public int y;
    public double g_cost;
    public double h_cost;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g_cost = this.h_cost = 0;
    }

    public double f_cost() {
        return h_cost + g_cost;
    }

    public boolean compareTo(Node n) {
        return this.x == n.x && this.y == n.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ") ";
    }
}
