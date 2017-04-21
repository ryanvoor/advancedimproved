package model.drawable.building;

// java standard library imports
import java.util.ArrayList;
import java.util.Arrays;

/**
 * represents a CommandCenter Building
 * for more information about Buildings
 * see the Building class
 * @author Ryan Voor
 */
public class CommandCenter extends Building {

    /**
     * constructor for the CommandCenter class
     */
    public CommandCenter() {
        super(
            new ArrayList<String>(
                Arrays.asList(
                    "file:lib/images/resized/"
                        + "sc2_red_command_center.gif"
                )
            )
        );
    }
}
