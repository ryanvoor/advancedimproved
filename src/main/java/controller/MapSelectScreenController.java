package controller;

import javafx.fxml.FXML;
import model.Map;
import fxapp.MainFXApplication;

/**
 * Controller for welcome screen
 */
public class MapSelectScreenController {

    /**
     * executes when example map select button is pressed
     */
    @FXML
    private void exampleMapSelectButtonPressed() {
        mapSelectButtonPressed("lib/maps/example.map");
    }

    /**
     * builds Map with given File and if successful
     * takes the user to the game play screen
     * @param mapFilePath the path to map file to use
     * to build the Map
     */
    private void mapSelectButtonPressed(String mapFilePath) {
        Map map = null;
        try {
            map = Map.buildMap(mapFilePath);
            // TODO there should be different catches for each type of error
        } catch (Exception e) {
            // TODO make this a user dialogue
            System.out.println(e.getMessage());
            System.exit(0);
        }
        MainFXApplication.showGameplayScreen(map);
    }
}
