package model.drawable.terrain;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.tileOccupant.TileOccupant;

/**
 * represents a Forest Terrain
 * for more information about Terrain objects,
 * check the Terrain class
 * @author Ryan Voor
 */
public class Forest extends Terrain {

    /**
     * constructor for the Forest class
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
