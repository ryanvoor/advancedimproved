package model;

// java standard library imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// javafx imports
import javafx.scene.canvas.Canvas;

// this project imports
import exception.MapFileReadException;

/**
 * Represents an individual Map
 * @author Ryan Voor
 * @version 1.0
 */
public class Map implements Iterable<Tile> {

    /*
    ---------------------------------------------------------------
    |                                                             |
    |   Information to help keep things straight in this class:   |
    |                                                             |
    ---------------------------------------------------------------

        X refers to horizontal
        Y refers to vertical

        Rows are horizontal
        X refers to position in a row
        Y refers to a specific row

        Columns are veritcal
        Y refers to position in a column
        X refers to a specific column

        We index both X and Y starting at 0

        Examples for additional clarity:
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

    // instance variables
    private ArrayList<ArrayList<Tile>> tiles;

    /**
     * constructs a new Map object
     * @param root the top-left Tile of this map
     */
    public Map(ArrayList<ArrayList<Tile>> tiles) {
        // TODO should I have something that enforces that the
        // arraylists inside are all the same length?
        // i should check the factory methods to see if that kind of
        // validation is already handled sort of
        // make decision as to whether i should put additional validation here
        this.tiles = tiles;
    }

    /**
     * draws this Map on the passed in Canvas object
     * @param canvas the Canvas object upon which we will draw this map
     */
    public void draw(Canvas canvas) {
        // grab the iterator object
        // this cast is necessary because the iterator
        // method returns an Iterator and I need to use
        // some MapIterator specific methods
        // this cast is safe because I know that this is
        // going to return a MapIterator
        MapIterator mapIterator = (MapIterator) this.iterator();

        // iterate through all the tiles
        while (mapIterator.hasNext()) {
            // get the tile I am currently on in the iteration
            Tile currentTile = mapIterator.next();

            // for the time being we just draw each Tile with
            // the assumption that its width will be the
            // same number of pixels always and we'll just
            // compress or stretch images to fit. This will
            // also ignore the size of the window and the size of
            // the canvas that we're drawing on

            // get the xPosition for the canvas
            int currentXIndex = mapIterator.getCurrentXIndex();
            int xPosition = currentXIndex * Tile.getWidthOfATileInPixels();

            // get the yPosition for the canvas
            int currentYIndex = mapIterator.getCurrentYIndex();
            int yPosition = currentYIndex * Tile.getHeightOfATileInPixels();

            // draw the current tile onto the canvas
            currentTile.draw(canvas, xPosition, yPosition);
        }
    }

    @Override
    public Iterator<Tile> iterator() {
        return new MapIterator();
    }

    /**
     * custom iterator class for this Map class
     */
    private class MapIterator implements Iterator<Tile> {

        /*
            iterates starting at the top left, then
            proceeds along each row from left to right,
            performs that pattern for each row from top
            to bottom
            For example: (x has been iterated over)

            Snapshot 1: Snapshot 2: Snapshot 3:
            o o o o o   x x x o o   x x x x x
            o o o o o   o o o o o   x x o o o
            o o o o o   o o o o o   o o o o o
            o o o o o   o o o o o   o o o o o
            o o o o o   o o o o o   o o o o o

            skipping ahead...

            Snapshot 4: Snapshot 5:
            x x x x x   x x x x x
            x x x x x   x x x x x
            x x x x x   x x x x x     Done!
            x x x x x   x x x x x
            x x x o o   x x x x x
        */

        // instance variables
        private int currentXIndex;
        private int currentYIndex;

        /**
         * Constructor for the Custom Iterator object for Maps
         * Sets the intial X and Y cursors
         */
        public MapIterator() {
            this.currentXIndex = 0;
            this.currentYIndex = 0;
        }

        /////////////
        // Getters //
        /////////////

        /**
         * Getter for the currentXIndex variable
         */
        public int getCurrentXIndex() {
            return this.currentXIndex;
        }

        /**
         * Getter for the currentYIndex variable
         */
        public int getCurrentYIndex() {
            return this.currentYIndex;
        }

        // no setters because the cursors should only
        // be manipulated internally

        //////////////////
        // Real Methods //
        //////////////////

        @Override
        public boolean hasNext() {
            return this.getCurrentXIndex() < Map.this.getNumberOfColumns()
                && this.getCurrentYIndex() < Map.this.getNumberOfRows();
        }

        @Override
        public Tile next() {
            if (this.hasNext()) {
                // grab the Tile that will be returned
                Tile toBeReturned =
                    Map.this.getTiles()
                        .get(currentYIndex).get(currentXIndex);

                // increment the X and Y cursors
                if (this.getCurrentXIndex() < Map.this.getMaxXIndex()) {
                    // if we are not at the end of a row then
                    // continue moving along that row
                    this.currentXIndex++;
                } else {
                    // if we are at the end of a row then
                    // move to beginning of the next row
                    this.currentYIndex++;
                    this.currentXIndex = 0;
                }

                // return the Tile we grabbed earlier
                return toBeReturned;
            }

            // this will only happen if whatever is using
            // this iterator is not checking hasNext correctly
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                "The remove operation is not supported by the MapIterator"
            );
        }
    }

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the number of columns in this map
     * so this gets the number of distinct X indexes
     * that can be accessed
     */
    public int getNumberOfColumns() {
        return tiles.get(0).size();
    }

    /**
     * getter for the number of rows in this map
     * so this gets the number of distinct Y indexes
     * that can be accessed
     */
    public int getNumberOfRows() {
        return tiles.size();
    }

    /**
     * getter for the maximum X index in this map
     * so this returns the number of columns minus 1
     * because we index at zero
     */
    public int getMaxXIndex() {
        return this.getNumberOfColumns() - 1;
    }

    /**
     * getter for the maximum Y index in this map
     * so this returns the number of rows minus 1
     * because we index at zero
     */
    public int getMaxYIndex() {
        return this.getNumberOfRows() - 1;
    }

    /**
     * Getter for the tiles variables
     * this method is private because it should
     * only ever be used in this class
     */
    private ArrayList<ArrayList<Tile>> getTiles() {
        return this.tiles;
    }



    /////////////////////////////////////
    ////////                     ////////
    //////// Map Factory methods ////////
    ////////                     ////////
    /////////////////////////////////////

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

        // fill in rows in the Map
        ArrayList<ArrayList<Tile>> tiles
            = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < y; i++) {
            // loop y times where y is the number of
            // rows on the map from above
            tiles.add(i, new ArrayList<Tile>());
        }

        // fill in the Tiles on the Map
        y = 0;
        x = 0;
        for (ArrayList<String> row: fileStrings) {
            for (String chunk: row) {
                // make sure each chunk has at least 2 characters
                if (chunk.length() < 2) {
                    throw new MapFileReadException(
                        "csv entry did not have enough letters for a tile");
                }

                // pull out the terrain and occupant characters
                String terrainString  = chunk.substring(0, 1);
                String occupantString = chunk.substring(1, 2);

                // get the actual java objects that relate to the strings
                Terrain terrain
                    = getTerrainFromFileString(terrainString);
                TileOccupant occupant
                    = getTileOccupantFromFileString(occupantString);

                // fill in variable tiles with Tile objects
                tiles.get(y).add(x, new Tile(terrain, occupant));

                x++;
            }
            x = 0;
            y++;
        }

        // make and return the actual Map object using the
        // grid of tiles that was just constructed
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

        // use this switch tree to go through all the
        // possible Terrains and convert those strings into
        // actual Terrain objects
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

        // use this switch tree to go through all the
        // possible Tile Occupants and convert those strings into
        // actual Tile Occupant objects
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
