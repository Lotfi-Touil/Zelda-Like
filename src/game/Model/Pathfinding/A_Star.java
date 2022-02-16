package game.Model.Pathfinding;

import java.util.*;

public class A_Star {

    private final int[][] map;
    private final boolean diagonal;
    private final List<Node> path;

    public A_Star(int[][] map, boolean diagonal) {
        this.map = map;
        this.diagonal = diagonal;
        this.path = new ArrayList<>();
    }

    public List<Node> getPath() {
        return path;
    }

    public Iterator<Node> getPathIterator() {
        return path.iterator();
    }

    public void findPath(int startX, int startY, int targetX, int targetY, boolean handleCollision) {
        path.clear();
        Node startNode = new Node(startX, startY);
        Node targetNode = new Node(targetX, targetY);

        List<Node> openSet = new ArrayList<>();
        HashSet<Node> closedSet = new HashSet<>();
        openSet.add(startNode);

        while (! openSet.isEmpty()) {
            Node currentNode = openSet.get(0);
            for (int i = 1; i < openSet.size(); i++) {
                Node n = openSet.get(i);
                if (n.f_cost() < currentNode.f_cost() || n.f_cost() == currentNode.f_cost() && n.h_cost < currentNode.h_cost) {
                    currentNode = n;
                }
            }

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if (currentNode.compareTo(targetNode)) {
                while (! currentNode.compareTo(startNode)) {
                    path.add(currentNode);
                    currentNode = currentNode.parent;
                }
                Collections.reverse(path);

                return;
            }

            for (Node neighbour : getNeighbours(currentNode)) {
                // Si handleCollision, le jeu peut crash, si on enleve les boss ne peuvent plus éviter les obstacles
                if (handleCollision) {
                    if (map[neighbour.y][neighbour.x] != -1 && closedSet.contains(neighbour)) {
                        if (closedSet.contains(neighbour)) {
                            continue;
                        }
                    }
                }
                else {
                    if (closedSet.contains(neighbour)) {
                        continue;
                    }
                }

                double newMovementCostToNeighbour = currentNode.g_cost + getDistance(currentNode, neighbour);
                if (newMovementCostToNeighbour < neighbour.g_cost || !openSet.contains(neighbour)) {
                    neighbour.g_cost = newMovementCostToNeighbour;
                    neighbour.h_cost = getDistance(neighbour, targetNode);
                    neighbour.parent = currentNode;

                    if (!openSet.contains(neighbour)) {
                        openSet.add(neighbour);
                    }
                }

            }
        }
    }

    private List<Node> getNeighbours(Node n) {
        List<Node> neighbours = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                if (! this.diagonal) {
                    if ((x == -1 && y == -1) || (x == -1 && y == 1) || (x == 1 && y == -1) || (x == 1 &&  y == 1)) {
                        continue;
                    }
                }

                int checkX = n.x + x;
                int checkY = n.y + y;

                if (checkX >= 0 && checkX < map[0].length && checkY >= 0 && checkY < map.length) {
                    neighbours.add(new Node(checkX, checkY));
                }
            }
        }
        return neighbours;
    }

    private double getDistance(Node a, Node b) {
        double dstX = Math.abs(a.x - b.x);
        double dstY = Math.abs(a.y - b.y);

        if (this.diagonal) {
            // distance diagonale
            if (dstX > dstY) {
                return 14 * dstY + 10 * (dstX - dstY);
            }
            return 14 * dstX + 10 * (dstY - dstX);
        }
        else {
            // https://fr.wikipedia.org/wiki/Distance_de_Manhattan
            return 10 * (dstX + dstY);
        }
    }
}
