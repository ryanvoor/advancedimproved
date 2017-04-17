package model.map;

// java standard library imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// javafx imports
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

// this project imports
import exception.MapFileReadException;
import model.drawable.terrain.Terrain;
import model.drawable.terrain.Forest;
import model.drawable.terrain.Mountain;
import model.drawable.terrain.Plains;
import model.drawable.tileOccupant.TileOccupant;
import model.drawable.tileOccupant.Infantry;
import model.drawable.tileOccupant.Sniper;

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

    /**
     * returns the Tile in this Map that resides at the parameter
     * X and Y indexes
     * @param xIndex the column upon which the return Tile resides
     * @param yIndex the row upon which the return Tile resides
     * @return Tile the Tile that is located at those indexes
     */
    public Tile getTileFromIndices(int xIndex, int yIndex) {
        // grab all the tiles so we can index in
        ArrayList<ArrayList<Tile>> tiles = this.getTiles();

        // see giant indexing comment at top of this class
        return tiles.get(yIndex).get(xIndex);
    }


    //////////////////
    // Real Methods //
    //////////////////

    /**
     * returns whether the Tile located at the parameter
     * indices is occupied
     * @param xIndex the index of the Column that contains
     * the Tile that we are interested in
     * @param yIndex the index of the Row that contains
     * the Tile that we are interested in
     * @return boolean whether the Tile located at the parameter
     * indices is occupied
     */
    public boolean tileIsOccupied(int xIndex, int yIndex) {
        // grab the Tile that we are interested in
        Tile tile = this.getTileFromIndices(xIndex, yIndex);

        // return whether that Tile is occupied
        return tile.hasOccupant();
    }

    /**
     * highlights all the Tiles that the occupant located on the
     * Tile specified by the parameter indices can move to
     * @param mapCanvas the Canvas upon which this Map is drawn
     * @param xIndex the column that the Tile we are checking is
     * located in
     * @param yIndex the row that the Tile we are checking is
     * located in
     * @param color the Color to tint the Tiles
     * @param alpha the Alpha value used to determine transparency,
     * between 0.0 and 1.0 and lower is more transparent
     */
    public void tintTilesToWhichOccupantCanMove(Canvas mapCanvas,
        int xIndex, int yIndex, Color color, double alpha) {
        // TODO finish implementing movement range highlighting

        // TODO try to abstract parts of this method into helper methods
        // just so that way it will be a litte clearer what is going on

        // TODO so I'm having a problem with an infinite loop i think
        // ill need to take a look at this and fix please

        //// build the DistanceGrid using Dijkstra's Algorithm         ////
        //// see: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm ////

        // grab the Tile that the occupant we care about is sitting on
        // so we can grab the TileOCcupant from that Tile
        // so we can grab the movement range from that TileOccupant
        Tile tileInQuestion = this.getTileFromIndices(xIndex, yIndex);
        TileOccupant occupantInQuestion = tileInQuestion.getOccupant();
        int movementRange = occupantInQuestion.getMovementRange();

        // set up the DistanceGrid
        DistanceGrid distanceGrid = new DistanceGrid(
            this.getNumberOfColumns(),
            this.getNumberOfRows()
        );

        // assign tentative distance values
        // initial Tile: 0
        // everything else: infinity (Integer.MAX_VALUE)
        // DistanceGrid sets values to "infinity" by default so
        // we only need to set the value for our initial Tile
        distanceGrid.setDistance(xIndex, yIndex, 0);

        // set initial Tile as current
        int currentXIndex = xIndex;
        int currentYIndex = yIndex;

        // check the unvisited Tiles, if the smallest tentative distance
        // of all the unvisited Tiles is "infinity" then stop looping
        while (!distanceGrid.allUnvisitedDistancesAreInfinity()) {
            // for current Tile calculate tentative distances of its
            // unvisited neighbors and replace their current tentative
            // distance if the new one is smaller than their current one,
            // otherwise just leave their current tentative distance alone
            int northXIndex = currentXIndex;
            int northYIndex = currentYIndex + 1;
            // check that we aren't at the edge of the Map and
            // that this Tile is unvisited
            if (northYIndex >= 0
                && !distanceGrid.isVisited(northXIndex, northYIndex)) {
                // TODO abstract the code in this if statement into a helper
                // method so i dont have it copied and pasted 4 times

                // grab the Tile to the north of the current Tile so we
                // can grab the Terrain of that Tile so we can grab the
                // movement cost of that Tile
                Tile northTile
                    = this.getTileFromIndices(northXIndex, northYIndex);
                Terrain northTerrain  = northTile.getTerrain();
                int northMovementCost = northTerrain.getMovementCost(occupantInQuestion);

                // grab current north tile distance and current tile distance
                int currentNorthTileDistance
                    = distanceGrid.getDistance(northXIndex, northYIndex);
                int currentTileDistance
                    = distanceGrid.getDistance(currentXIndex, currentYIndex);

                // use those to calculate the distance that might replace the
                // north tile distance
                int potentialNorthTileDistance
                    = currentTileDistance + northMovementCost;

                // if this new distance to the north tile is shorter than
                // the existing distance to the north tile
                if (potentialNorthTileDistance < currentNorthTileDistance) {
                    // replace the distance
                    distanceGrid.setDistance(
                        northXIndex,
                        northYIndex,
                        potentialNorthTileDistance
                    );
                }
            }

            int eastXIndex = currentXIndex + 1;
            int eastYIndex = currentYIndex;
            // check that we aren't at the edge of the Map and
            // that this Tile is unvisited
            if (eastXIndex <= this.getMaxXIndex()
                && !distanceGrid.isVisited(eastXIndex, eastYIndex)) {
                // grab the Tile to the east of the current Tile so we
                // can grab the Terrain of that Tile so we can grab the
                // movement cost of that Tile
                Tile eastTile
                    = this.getTileFromIndices(eastXIndex, eastYIndex);
                Terrain eastTerrain  = eastTile.getTerrain();
                int eastMovementCost = eastTerrain.getMovementCost(occupantInQuestion);

                // grab current east tile distance and current tile distance
                int currentEastTileDistance
                    = distanceGrid.getDistance(eastXIndex, eastYIndex);
                int currentTileDistance
                    = distanceGrid.getDistance(currentXIndex, currentYIndex);

                // use those to calculate the distance that might replace the
                // east tile distance
                int potentialEastTileDistance
                    = currentTileDistance + eastMovementCost;

                // if this new distance to the east tile is shorter than
                // the existing distance to the east tile
                if (potentialEastTileDistance < currentEastTileDistance) {
                    // replace the distance
                    distanceGrid.setDistance(
                        eastXIndex,
                        eastYIndex,
                        potentialEastTileDistance
                    );
                }
            }

            int southXIndex = currentXIndex;
            int southYIndex = currentYIndex - 1;
            // check that we aren't at the edge of the Map and
            // that this Tile is unvisited
            if (southYIndex <= this.getMaxYIndex()
                && !distanceGrid.isVisited(southXIndex, southYIndex)) {
                // grab the Tile to the south of the current Tile so we
                // can grab the Terrain of that Tile so we can grab the
                // movement cost of that Tile
                Tile southTile
                    = this.getTileFromIndices(southXIndex, southYIndex);
                Terrain southTerrain  = southTile.getTerrain();
                int southMovementCost = southTerrain.getMovementCost(occupantInQuestion);

                // grab current south tile distance and current tile distance
                int currentSouthTileDistance
                    = distanceGrid.getDistance(southXIndex, southYIndex);
                int currentTileDistance
                    = distanceGrid.getDistance(currentXIndex, currentYIndex);

                // use those to calculate the distance that might replace the
                // south tile distance
                int potentialSouthTileDistance
                    = currentTileDistance + southMovementCost;

                // if this new distance to the south tile is shorter than
                // the existing distance to the south tile
                if (potentialSouthTileDistance < currentSouthTileDistance) {
                    // replace the distance
                    distanceGrid.setDistance(
                        southXIndex,
                        southYIndex,
                        potentialSouthTileDistance
                    );
                }
            }

            int westXIndex = currentXIndex - 1;
            int westYIndex = currentYIndex;
            // check that we aren't at the edge of the Map and
            // that this Tile is unvisited
            if (westXIndex >= 0
                && !distanceGrid.isVisited(westXIndex, westYIndex)) {
                // grab the Tile to the west of the current Tile so we
                // can grab the Terrain of that Tile so we can grab the
                // movement cost of that Tile
                Tile westTile
                    = this.getTileFromIndices(westXIndex, westYIndex);
                Terrain westTerrain  = westTile.getTerrain();
                int westMovementCost = westTerrain.getMovementCost(occupantInQuestion);

                // grab current west tile distance and current tile distance
                int currentWestTileDistance
                    = distanceGrid.getDistance(westXIndex, westYIndex);
                int currentTileDistance
                    = distanceGrid.getDistance(currentXIndex, currentYIndex);

                // use those to calculate the distance that might replace the
                // west tile distance
                int potentialWestTileDistance
                    = currentTileDistance + westMovementCost;

                // if this new distance to the west tile is shorter than
                // the existing distance to the west tile
                if (potentialWestTileDistance < currentWestTileDistance) {
                    // replace the distance
                    distanceGrid.setDistance(
                        westXIndex,
                        westYIndex,
                        potentialWestTileDistance
                    );
                }
            }


            // mark the current Tile as visited
            distanceGrid.setToVisited(currentXIndex, currentYIndex);


            // set the unvisited Tile with the smallest tentative
            // distance to be the new current Tile
            currentXIndex = distanceGrid.getXIndexOfSmallestUnvisited();
            currentYIndex = distanceGrid.getYIndexOfSmallestUnvisited();
        }

        // when we break out of the loop the DistanceGrid should
        // be completely filled in with the shortest distances to
        // each Tile


        //// highlight all tiles within the movement range ////

        // grab the iterator object
        // this cast is necessary because the iterator
        // method returns an Iterator and I need to use
        // some MapIterator specific methods
        // this cast is safe because I know that this is
        // going to return a MapIterator
        MapIterator mapIterator = (MapIterator) this.iterator();

        // get the xIndex
        int currentXIndexForHighlighting = mapIterator.getCurrentXIndex();

        // get the yIndex
        int currentYIndexForHighlighting = mapIterator.getCurrentYIndex();

        // iterate through all the tiles
        while (mapIterator.hasNext()) {
            // move the Iterator to the next Tile
            // don't assign to anything since we don't actually
            // need the Tile object for this loop
            mapIterator.next();

            // grab the current Distance
            int currentDistance = distanceGrid.getDistance(
                currentXIndexForHighlighting,
                currentYIndexForHighlighting
            );

            // if the current Tile is within the movement range
            if (currentDistance <= movementRange) {
                // tint the current Tile
                Map.tintTile(
                    mapCanvas,
                    currentXIndex,
                    currentYIndex,
                    color,
                    alpha
                );
            }

            // update xIndex and yIndex
            currentXIndexForHighlighting = mapIterator.getCurrentXIndex();
            currentYIndexForHighlighting = mapIterator.getCurrentYIndex();
        }
    }

    /**
     * internal class that holds information about Tile maps
     * that is relevant to the implementation of Dijkstra's algorithm
     * @author Ryan Voor
     */
    private class DistanceGrid {
        // TODO finish the distance grid class

        // see the giant comment at the top of the Map class for
        // clarification on how the grid is laid out and accessed

        // instance variables
        ArrayList<ArrayList<Integer>> distances;
        ArrayList<ArrayList<Boolean>> visited;

        /**
         * constructor for the DistanceGrid class
         * @param width the width of the grid to be constructed
         * @param height the height of the grid to be constructed
         */
        public DistanceGrid(int width, int height) {
            this.distances = new ArrayList<ArrayList<Integer>>();
            this.visited   = new ArrayList<ArrayList<Boolean>>();

            // create 'width' number of rows each with
            // 'height' number of entries (columns)
            for (int i = 0; i < width; i++) {
                this.distances.add(new ArrayList<Integer>());
                this.visited.add(new ArrayList<Boolean>());

                for (int j = 0; j < height; j++) {
                    // Integer.MAX_VALUE is the default value for
                    // the distances
                    this.distances.get(i).add(Integer.MAX_VALUE);

                    // false is the default value for 'visited'
                    this.visited.get(i).add(false);
                }
            }
        }

        /////////////
        // Getters //
        /////////////

        /**
         * returns the current distance to the Tile with
         * the parameter indices
         * @param xIndex the X Index of the Tile with the distance
         * that will be returned
         * @param yIndex the Y Index of the Tile with the distance
         * that will be returned
         * @return int the current distance to the Tile located at
         * the parameter indicies
         */
        public int getDistance(int xIndex, int yIndex) {
            return this.distances.get(yIndex).get(xIndex);
        }

        /**
         * returns whether the Tile at the parameter
         * indicies has been visited
         * @param xIndex the X Index of the Tile that may
         * or may not have been visited
         * @param yIndex the Y Index of the Tile that may
         * or may not have been visited
         * @return boolean whether the Tile at the parameter
         * indicies has been visited
         */
        public boolean isVisited(int xIndex, int yIndex) {
            return this.visited.get(yIndex).get(xIndex);
        }

        /**
         * returns the number of columns in this grid
         * @return int the number of columns in this grid
         */
        private int getNumberOfColumns() {
            return this.distances.get(0).size();
        }

        /**
         * returns the number of rows in this grid
         * @return int the number of rows in this grid
         */
        private int getNumberOfRows() {
            return this.distances.size();
        }

        /**
         * returns the X Index of the smallest unvisited
         * Tile in this grid
         * @return int the X Index of the smallest unvisited
         * Tile in this grid
         */
        public int getXIndexOfSmallestUnvisited() {
            return getIndiciesOfSmallestUnvisited("x");
        }

        /**
         * returns the Y Index of the smallest unvisited
         * Tile in this grid
         * @return int the Y Index of the smallest unvisited
         * Tile in this grid
         */
        public int getYIndexOfSmallestUnvisited() {
            return getIndiciesOfSmallestUnvisited("y");
        }

        /**
         * returns either the X or Y index of the smallest
         * unvisited Tile in this grid
         * NOTE: because of logic in my implementation of
         * dijkstra's algorithm this method should only ever
         * be called if there is at least 1 unvisited Tile with
         * a distance less than infinity
         * @param index either "x" or "y" depending on which
         * index the caller wants returned
         */
        private int getIndiciesOfSmallestUnvisited(String index) {
            int xIndexOfResult = -1;
            int yIndexOfResult = -1;

            for (int i = 0; i < this.getNumberOfColumns(); i++) {
                for (int j = 0; j < this.getNumberOfRows(); j++) {
                    // figure out the distance of the Tile we're iterating
                    // on as well as the tile that is the smallest distance
                    // we've encountered so far
                    int currentDistance = this.getDistance(i, j);
                    int currentResultDistance = Integer.MAX_VALUE;
                    // check if we've already encountered an unvisited
                    // tile w/ distance less than infinity
                    if (-1 != xIndexOfResult && -1 != yIndexOfResult) {
                        currentResultDistance = this.getDistance(
                            xIndexOfResult,
                            yIndexOfResult
                        );
                    }

                    // if the current iteration tile has a smaller distance
                    // than the smallest we've encountered so far then
                    // i guess we have a new smallest we've encountered so far
                    if (currentDistance < currentResultDistance) {
                        xIndexOfResult = i;
                        yIndexOfResult = j;
                    }
                }
            }

            if (index.equals("x")) {
                return xIndexOfResult;
            }
            if (index.equals("y")) {
                return yIndexOfResult;
            }
            // TODO make this throw an exception at the start of the method
            return -1;
        }


        /////////////
        // Setters //
        /////////////

        /**
         * sets the distance of the Tile with the parameter indices
         * to the parameter distance
         * @param xIndex the X Index of the Tile whose distance
         * is to be set
         * @param yIndex the Y Index of the Tile whose distance
         * is to be set
         * @param distance the distance to be set
         */
        public void setDistance(int xIndex, int yIndex, int distance) {
            this.distances.get(yIndex).set(xIndex, distance);
        }

        /**
         * sets the Tile at the parameter indices to the 'visited' status
         * @param xIndex the X Index of the Tile to be set to 'visited'
         * @param yIndex the Y Index of the Tile to be set to 'visited'
         */
        public void setToVisited(int xIndex, int yIndex) {
            this.visited.get(yIndex).set(xIndex, true);
        }

        //////////////////
        // Real Methods //
        //////////////////

        /**
         * returns whether all the unvisited tiles have
         * distance infinity
         * NOTE: 'infinity' is just Integer.MAX_VALUE
         * @return boolean whether all the unvisited tiles have
         * distance infinity
         */
        public boolean allUnvisitedDistancesAreInfinity() {
            // iterate over all the Tiles in this grid
            // 'i' is the X Index or the Column
            // 'j' is the Y Index or the Row
            for (int i = 0; i < this.getNumberOfColumns(); i++) {
                for (int j = 0; j < this.getNumberOfRows(); j++) {
                    if (!this.isVisited(i, j)) {
                        if (Integer.MAX_VALUE != this.getDistance(i, j)) {
                            // if the current Tile is unvisited AND
                            // it is not set to infinity distance then
                            // all the unvisited tiles are not
                            // distance infinity
                            return false;
                        }
                    }
                }
            }

            // execution will only get outside the loop if
            // all the unvisited Tiles have distance 'infinity'
            return true;
        }
    }

    /**
     * draws this Map on the passed in Canvas object
     * @param canvas the Canvas object upon which we will draw this map
     */
    public void draw(Canvas canvas, long time) {
        // grab the iterator object
        // this cast is necessary because the iterator
        // method returns an Iterator and I need to use
        // some MapIterator specific methods
        // this cast is safe because I know that this is
        // going to return a MapIterator
        MapIterator mapIterator = (MapIterator) this.iterator();

        // grab the current X and Y indices before the loop starts
        // because every time next() gets called they increment and
        // the Tile should be drawn using the indices from before
        // the next() call that returned that Tile occurred.

        // get the xPosition for the canvas
        int currentXIndex = mapIterator.getCurrentXIndex();
        int xPosition = currentXIndex * Tile.getWidthOfATileInPixels();

        // get the yPosition for the canvas
        int currentYIndex = mapIterator.getCurrentYIndex();
        int yPosition = currentYIndex * Tile.getHeightOfATileInPixels();

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

            // draw the current tile onto the canvas
            currentTile.draw(canvas, xPosition, yPosition, time);

            // update the X and Y positions after we draw the map because
            // the indices from before the next() call must be used to
            // draw the current Tile in order for it to be in the right
            // place on the canvas

            // get the xPosition for the canvas
            currentXIndex = mapIterator.getCurrentXIndex();
            xPosition = currentXIndex * Tile.getWidthOfATileInPixels();

            // get the yPosition for the canvas
            currentYIndex = mapIterator.getCurrentYIndex();
            yPosition = currentYIndex * Tile.getHeightOfATileInPixels();
        }
    }

    @Override
    public Iterator<Tile> iterator() {
        return new MapIterator();
    }

    /**
     * custom iterator class for this Map class
     * @author Ryan Voor
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

    ///////////////////
    // Class methods //
    ///////////////////

    /**
     * given an X-coordinate in pixels returns
     * the tile column on this Map that contains those coordinates
     * @param xCoordinate the X coordinate that is contained by the
     * Tile that will be returned
     * @return int the column that contains the parameter coordinate
     */
    public static int getColumnFromCoordinate(int xCoordinate) {
        // grab the pixel width of a single Tile
        int widthOfATile = Tile.getWidthOfATileInPixels();

        // calculate how many Tiles over the xPos is
        // (uses integer division)
        return xCoordinate / widthOfATile;
    }

    /**
     * given a Y-coordinate in pixels returns
     * the tile row on this Map that contains those coordinates
     * @param yCoordinate the Y coordinate that is contained by the
     * Tile that will be returned
     * @return int the row that contains the parameter coordinate
     */
    public static int getRowFromCoordinate(int yCoordinate) {
        // grab the pixel height of a single Tile
        int heightOfATile = Tile.getHeightOfATileInPixels();

        // calculate how many Tiles down the yPos is
        // (uses integer division)
        return yCoordinate / heightOfATile;
    }

    /**
     * tint the tile at the parameter indices to the parameter
     * color and alpha
     * @param mapCanvas the canvas upon which this Map is drawn
     * @param columnIndex the X-Index of the Tile that we are tinting
     * @param rowIndex the Y-Index of the Tile that we are tinting
     * @param color the Color to tint the Tile
     * @param alpha the Alpha value used to determine transparency,
     * between 0.0 and 1.0 and lower is more transparent
     */
    public static void tintTile(Canvas mapCanvas,
        int columnIndex, int rowIndex, Color color, double alpha) {
        // grab the tile width and height
        int widthOfATile  = Tile.getWidthOfATileInPixels();
        int heightOfATile = Tile.getHeightOfATileInPixels();

        // calculate the top-left corner of the Tile that we're tinting
        int topLeftCornerOfTileXCoordinate = widthOfATile  * columnIndex;
        int topLeftCornerOfTileYCoordinate = heightOfATile * rowIndex;

        // grab the graphics context
        GraphicsContext graphicsContext = mapCanvas.getGraphicsContext2D();

        // grab previous alpha and paint values so they can be reset later
        double previousGlobalAlpha = graphicsContext.getGlobalAlpha();
        Paint previousFillPaint    = graphicsContext.getFill();

        // set alpha value and paint so the rectangle we draw will be
        // transparent and the parameter color
        graphicsContext.setGlobalAlpha(alpha);
        graphicsContext.setFill(color);

        // draw a transparent box over the Tile
        graphicsContext.fillRect(
            topLeftCornerOfTileXCoordinate,
            topLeftCornerOfTileYCoordinate,
            widthOfATile,
            heightOfATile
        );

        // reset the alpha value to whatever it was before
        graphicsContext.setGlobalAlpha(previousGlobalAlpha);
        graphicsContext.setFill(previousFillPaint);
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
            n => no occupant,
            i => infantry,
            s => sniper,

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
        case "i":
            toBeReturned = new Infantry();
            break;
        case "s":
            toBeReturned = new Sniper();
            break;
        default:
            throw new MapFileReadException(
                "incorrect occupant letter in map file");
        }

        return toBeReturned;
    }
}
