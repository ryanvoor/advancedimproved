package model.drawable.terrain;

// this project imports
import model.drawable.tileOccupant.TileOccupant;

/**
 * represents a Plains Terrain
 * for more information about Terrain objects,
 * check the Terrain class
 * @author Ryan Voor
 */
public class Plains extends Terrain {

    /**
     * constructor for the Plains class
     */
    public Plains() {
        super("file:lib/images/resized/plains_resized.jpg");
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
