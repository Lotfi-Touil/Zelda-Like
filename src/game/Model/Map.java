package game.Model;

import game.Constants;
import game.Model.Enemies.Enemy;
import game.Model.Objects.Chest;
import game.Model.Objects.Heart;
import game.Model.Objects.Object;
import game.Model.Objects.Potions.PotionCollision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Map {

    private final String name;
    private final double link_start_x;
    private final double link_start_y;
    private final java.util.Map<String, int[][]> maps;
    private final ObservableList<Integer> destructibles;
    private ObservableList<Object> objects;
    private ObservableList<Enemy> enemies;
    private final ObservableList<PNJ> pnjs;

    public Map(String name, double link_start_x, double link_start_y, String... map_paths) {
        this.name = name;
        this.link_start_x = link_start_x;
        this.link_start_y = link_start_y;
        this.destructibles = FXCollections.observableArrayList();
        this.objects = FXCollections.observableArrayList();
        this.enemies = FXCollections.observableArrayList();
        this.pnjs = FXCollections.observableArrayList();

        this.maps = new HashMap<>();
        for (String s : map_paths) {
            this.maps.put(s.substring(s.lastIndexOf("_") + 1, s.lastIndexOf(".")), csvToMap(s));
        }

        for (int row = 0; row < this.maps.get("destructible").length; row++) {
            for (int col = 0; col < this.maps.get("destructible")[0].length; col++) {
                if (this.maps.get("destructible")[row][col] != -1) {
                    this.destructibles.add(Constants.MAP_BLOCK_WIDTH * row + col);
                }
                if (this.maps.get("objects")[row][col] != -1) {
                    if (this.maps.get("objects")[row][col] == Constants.CHEST_CLOSED) {
                        this.objects.add(new Chest(col, row));
                    }
                    else if (this.maps.get("objects")[row][col] == Constants.LIFE_HEART) {
                        this.objects.add(new Heart(col, row));
                    }
                    else {
                        this.objects.add(new PotionCollision(col, row));
                    }
                }
                if (this.maps.get("pnjs")[row][col] == Constants.PNJ_DOWN) {
                    this.pnjs.add(new PNJ(col, row));
                }
            }
        }

    }

    public String getName() {
        return name;
    }

    public java.util.Map<String, int[][]> getMaps() {
        return maps;
    }

    public ObservableList<Integer> getDestructibles() {
        return destructibles;
    }

    public ObservableList<Object> getObjects() {
        return objects;
    }

    public ObservableList<Enemy> getEnemies() {
        return enemies;
    }

    public ObservableList<PNJ> getPnjs() {
        return pnjs;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = FXCollections.observableArrayList(objects);
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = FXCollections.observableArrayList(enemies);
    }

    public double getLink_start_x() {
        return link_start_x;
    }

    public double getLink_start_y() {
        return link_start_y;
    }

    public static int[][] csvToMap(String path) {
        List<List<String>> csv_elements = new ArrayList<>();
        int map_width = 0;
        int map_height = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                csv_elements.add(Arrays.asList(values));

                map_width = values.length;
                map_height++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] map = new int[map_height][map_width];

        for (int i = 0; i < map_height; i++) {
            for (int j = 0; j < map_width; j++) {
                map[i][j] = Integer.parseInt(csv_elements.get(i).get(j));
            }
        }

        return map;
    }

    @Override
    public String toString() {
        return "Map: " + name;
    }
}
