package model.drawable.tileOccupant;

// java standard library imports
import java.util.ArrayList;
import java.util.Arrays;

/**
 * represents an Infantry TileOccupant
 * for more information about TileOccupants
 * see the TileOccupant class
 * @author Ryan Voor
 */
public class Infantry extends TileOccupant {

    /**
     * constructor for the Infantry class
     */
    public Infantry() {
        super(
            new ArrayList<String>(
                Arrays.asList(
                    "file:lib/images/resized/"
                        + "megaman_running_transparent_resized.gif"
                )
            )
        );
    }
}
