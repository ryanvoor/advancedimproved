package model.drawable.tileOccupant;

// java standard library imports
import java.util.ArrayList;
import java.util.Arrays;

/**
 * represents a Sniper TileOccupant
 * for more information about TileOccupants
 * see the TileOccupant class
 * @author Ryan Voor
 */
public class Sniper extends FootMovementUnit {

    /**
     * constructor for the Sniper class
     */
    public Sniper() {
        super(
            new ArrayList<String>(
                Arrays.asList(
                    "file:lib/images/resized/"
                        + "megaman_pointing_transparent_resized.gif",
                    "file:lib/images/resized/"
                        + "megaman_jumping_transparent_resized.gif"
                )
            ),
            2
        );
    }
}
