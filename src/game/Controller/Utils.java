package game.Controller;

import game.Constants;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Utils {

    public static void mapToCanvas(BufferedImage tileset, Canvas c, int[][] map) {
        if (map != null) {
            GraphicsContext gc = c.getGraphicsContext2D();
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[0].length; col++) {
                    // if the tile isn't empty
                    if (map[row][col] != -1) {
                        Image img = SwingFXUtils.toFXImage(getTileFromTileset(tileset, map[row][col]), null);
                        int x = col * Constants.BLOCK_SIZE;
                        int y = row * Constants.BLOCK_SIZE;

                        gc.drawImage(img, x, y);
                    }
                }
            }
        }
    }

    public static BufferedImage getTileFromTileset(BufferedImage tileset, int block) {

        int x = Constants.BLOCK_SIZE * (block % (tileset.getWidth() / Constants.BLOCK_SIZE));
        int y = Constants.BLOCK_SIZE * (block / (tileset.getWidth() / Constants.BLOCK_SIZE));

        return tileset.getSubimage(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
    }

    public static void drawOnCanvas(Canvas c, BufferedImage tileset, double x, double y, int block) {
        GraphicsContext gc = c.getGraphicsContext2D();
        Image img = SwingFXUtils.toFXImage(Utils.getTileFromTileset(tileset, block), null);
        gc.drawImage(img, x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE);
    }

    public static void removeFromCanvas(Canvas c, int x, int y, int w, int h) {
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.clearRect(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE, w, h);
    }

    public static void clearCanvas(Canvas c) {
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.clearRect(0, 0, c.getWidth(), c.getHeight());

    }

}
