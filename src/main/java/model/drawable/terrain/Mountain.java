package model.drawable.terrain;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.tileOccupant.TileOccupant;

/**
 * TODO
 */
public class Mountain extends Terrain {

    /**
     * TODO
     */
    public Mountain() {
        super("file:lib/images/resized/mountain_resized.jpg");
    }

    /**
     * TODO
     */
    public int getMovementCost(TileOccupant unit) {
        // TODO
        //if (unit instanceof ???) {
        //    return ???;
        //}
        return 0;
    }
}
