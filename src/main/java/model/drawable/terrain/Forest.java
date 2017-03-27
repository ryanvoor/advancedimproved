package model.drawable.terrain;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.tileOccupant.TileOccupant;

/**
 * TODO
 */
public class Forest extends Terrain {

    /**
     * TODO
     */
    public Forest() {
        super("File:lib/images/resized/forest_resized.png");
    }

    @Override
    public int getMovementCost(TileOccupant unit) {
        // TODO
        //if (unit instanceof ???) {
        //    return ???;
        //}
        return 0;
    }
}
