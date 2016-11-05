package model;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import exception.MapFileReadException;

/**
 * Represents an individual Map
 * @author Ryan Voor
 * @version 1.0
 */
public class Map {
    private ArrayList<ArrayList<Tile>> tiles;

    /**
     * constructs a new Map object
     * @param root the top-left Tile of this map
     */
    public Map(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
    }

    // Map Factory methods

    /**
     * builds a Map object given a path to a map file
     * @param mapFileName the path to the map file
     * @return Map the map object
     */
    public static Map buildMap(String mapFileName)
        throws FileNotFoundException, MapFileReadException {
        File    mapFileReader = new File(mapFileName);
        Scanner scan          = new Scanner(mapFileReader);

        ArrayList<ArrayList<String>> fileStrings
            = new ArrayList<ArrayList<String>>();
        /*
            (0,0), (1,0), (2,0)
            (0,1), (1,1), (2,1)
            (0,2), (1,2), (2,2)

            .get(0).get(0), .get(0).get(1), .get(0).get(2)
            .get(1).get(0), .get(1).get(1), .get(1).get(2)
            .get(2).get(0), .get(2).get(1), .get(2).get(2)

            ArrayList(
                ArrayList("a","b","c"),
                ArrayList("d","e","f"),
                ArrayList("g","h","i")
            )
        */

        // fill in fileStrings with strings from csv file
        int y = 0;
        int x = 0;
        while (scan.hasNextLine()) {
            fileStrings.add(y, new ArrayList<String>());
            String line = scan.nextLine();
            String[] lineParts = line.split(",");
            x = 0;
            for (String linePart: lineParts) {
                fileStrings.get(y).add(x, linePart);
                x++;
            }
            y++;
        }

        // fill in tiles to be put into the Map
        ArrayList<ArrayList<Tile>> tiles
            = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < y; i++) {
            /* loop y times where y is the number of
            rows on the map from above */
            tiles.add(i, new ArrayList<Tile>());
        }

        y = 0;
        x = 0;
        for (ArrayList<String> row: fileStrings) {
            for (String chunk: row) {
                if (chunk.length() < 2) {
                    throw new MapFileReadException(
                        "csv entry did not have enough letters for a tile");
                }
                String terrainString  = chunk.substring(0, 1);
                String occupantString = chunk.substring(1, 2);
                Terrain terrain
                    = getTerrainFromFileString(terrainString);
                TileOccupant occupant
                    = getTileOccupantFromFileString(occupantString);

                // fill in variable tiles with Tiles
                tiles.get(y).add(x, new Tile(terrain, occupant));

                x++;
            }
            y++;
        }

        Map map = new Map(tiles);
        return map;
    }

    /*
        The files are csv's that look like this:
        pn,pn,pn,
        mn,mn,mn,
        fn,fn,fn,

        The first letter in each entry refers to the terrain
        of that tile.
        The second letter in each entry refers to the occupant
        of that tile.

        Map Letters Map:
            Terrains:
            p => plains,
            m => mountain,
            f => forest,

            Occupants:
            n => no occupant

    */

    /**
     * helper method that converts a terrain-string from a map file into
     * an actual Terrain object
     * @param fileString the string from the map file
     * @return Terrain the corresponding Terrain
     */
    private static Terrain getTerrainFromFileString(String fileString)
        throws MapFileReadException {
        Terrain toBeReturned = null;

        switch (fileString) {
        case "p":
            toBeReturned = new Plains();
            break;
        case "m":
            toBeReturned = new Mountain();
            break;
        case "f":
            toBeReturned = new Forest();
            break;
        default:
            throw new MapFileReadException(
                "incorrect terrain letter in map file");
        }

        return toBeReturned;
    }

    /**
     * helper method that converts an occupant-string from a map file into
     * an actual TileOccupant object
     * @param fileString the string from the map file
     * @return TileOccupant the corresponding TileOccupant
     */
    private static TileOccupant getTileOccupantFromFileString(String fileString)
        throws MapFileReadException {
        TileOccupant toBeReturned = null;
        switch (fileString) {
        case "n":
            toBeReturned = null;
            break;
        default:
            throw new MapFileReadException(
                "incorrect occupant letter in map file");
        }

        return toBeReturned;
    }
}
