package model.drawable.terrain;

// this project imports
import model.drawable.tileOccupant.TileOccupant;
import model.drawable.tileOccupant.FootMovementUnit;

/**
 * represents a Mountain Terrain
 * for more information about Terrain objects,
 * check the Terrain class
 * @author Ryan Voor
 */
public class Mountain extends Terrain {

    // class variables
    private static final int footMovementUnitCost = 2;

    /**
     * constructor for the Mountain class
     */
    public Mountain() {
        super("file:lib/images/resized/mountain_resized.jpg");
    }

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the movement cost of a foot movement unit
     * to move onto a Tile with this Terrain
     * @return int the movement cost of a foot movement unit
     * to move onto a Tile with this Terrain
     */
    private int getFootMovementUnitCost() {
        return Mountain.footMovementUnitCost;
    }


    //////////////////
    // Real Methods //
    //////////////////

    @Override
    public int getMovementCost(TileOccupant unit) {
        // one if-statement for each type of unit
        // that can move onto a Tile with this Terrain
        if (unit instanceof FootMovementUnit) {
            return this.getFootMovementUnitCost();
        }

        // catch-all for any unit-types that cannot
        // move onto a Tile with this Terrain
        return Integer.MAX_VALUE;
    }
}
