package game.Controller.Listeners;

import game.Constants;
import game.Controller.Utils;
import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;

public class DestructiblesListener implements ListChangeListener<Integer> {

    private Canvas canvas;

    public DestructiblesListener(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void onChanged(Change<? extends Integer> change) {
        while (change.next()) {
            for (Integer i : change.getRemoved()) {
                int x = i % Constants.MAP_BLOCK_WIDTH;
                int y = i / Constants.MAP_BLOCK_WIDTH;

                Utils.removeFromCanvas(canvas, x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
            }
        }
    }
}
