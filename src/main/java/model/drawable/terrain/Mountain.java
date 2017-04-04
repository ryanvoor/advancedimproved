package model.drawable.terrain;

// this project imports
import model.drawable.tileOccupant.TileOccupant;

/**
 * represents a Mountain Terrain
 * for more information about Terrain objects,
 * check the Terrain class
 * @author Ryan Voor
 */
public class Mountain extends Terrain {

    /**
     * constructor for the Mountain class
     */
    public Mountain() {
        super("file:lib/images/resized/mountain_resized.jpg");
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
