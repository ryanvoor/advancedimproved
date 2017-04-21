package model.drawable.building;

// java standard library imports
import java.util.ArrayList;
import java.util.Arrays;

/**
 * represents a MedicalCenter Building
 * for more information about Buildings
 * see the Building class
 * @author Ryan Voor
 */
public class MedicalCenter extends Building {

    /**
     * constructor for the MedicalCenter class
     */
    public MedicalCenter() {
        super(
            new ArrayList<String>(
                Arrays.asList(
                    "file:lib/images/resized/"
                        + "medical_brick.png"
                )
            )
        );
    }
}
