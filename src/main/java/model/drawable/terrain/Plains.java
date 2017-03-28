package model.drawable.terrain;

// this project imports
import model.drawable.tileOccupant.TileOccupant;

/**
 * TODO
 */
public class Plains extends Terrain {

    /**
     * TODO
     */
    public Plains() {
        super("file:lib/images/resized/plains_resized.jpg");
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
