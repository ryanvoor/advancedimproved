package model.map;

// javafx imports
import javafx.scene.canvas.Canvas;

// this project imports
import model.drawable.terrain.Terrain;
import model.drawable.tileOccupant.TileOccupant;

// TODO at some point this class will need to be modified to hold
// multiple occupants so the gameplay dream can be implemented

/**
 * Class that represents a Tile on a Map
 * this class holds the occupant unit(s) and knows the
 * Terrain of this Tile on the Map
 * @author Ryan Voor
 */
public class Tile {

    // constants
    private static final int WIDTH_OF_A_TILE_IN_PIXELS  = 100;
    private static final int HEIGHT_OF_A_TILE_IN_PIXELS = 100;

    // instance variables
    private TileOccupant occupant;
    private Terrain terrain;

    /**
     * constructor for the Tile class, takes in
     * both a Terrain and a TileOccupant
     * @param terrain the terrain of this Tile
     * @param occupant the occupant of this Tile
     */
    public Tile(Terrain terrain, TileOccupant occupant) {
        this.terrain  = terrain;
        this.occupant = occupant;
    }

    /**
     * constructor for the Tile class, just takes in
     * a Terrain, this Tile will have no occupant
     * @param terrain the terrain of this Tile
     */
    public Tile(Terrain terrain) {
        this(terrain, null);
    }

    /////////////
    // Getters //
    /////////////

    /**
     * returns the number of pixels wide that
     * a Tile is on the Map
     * @return int the pixel width of a Tile
     */
    public static int getWidthOfATileInPixels() {
        return Tile.WIDTH_OF_A_TILE_IN_PIXELS;
    }

    /**
     * returns the number of pixels high that
     * a Tile is on the Map
     * @return int the pixel height of a Tile
     */
    public static int getHeightOfATileInPixels() {
        return Tile.HEIGHT_OF_A_TILE_IN_PIXELS;
    }

    /**
     * the getter for the occupant of this Tile
     * @return TileOccupant the occupant of this Tile
     */
    public TileOccupant getOccupant() {
        return this.occupant;
    }

    /**
     * the getter for the Terrain of this Tile
     * @return Terrain the Terrain of this Tile
     */
    public Terrain getTerrain() {
        return this.terrain;
    }

    /**
     * returns whether this Tile has an occupant
     * @return boolean whether this Tile has an occupant
     */
    public boolean hasOccupant() {
        return null != this.getOccupant();
    }

    /////////////
    // Setters //
    /////////////

    /**
     * setter for the occupant of this Tile
     * @param occupant the occupant to be placed on this Tile
     */
    public void setOccupant(TileOccupant occupant) {
        this.occupant = occupant;
    }

    /**
     * setter for the Terrain of this Tile
     * @param terrain the Terrain to be placed on this Tile
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    //////////////////
    // Real Methods //
    //////////////////

    /**
     * draws the terrain and occupant of this Tile onto the
     * canvas at the specified positions
     * @param canvas the canvas upon which this Tile will be drawn
     * @param xPosition the X position in pixels of the top-left corner
     * of the Tile where it will be drawn
     * @param yPosition the Y position in pixels of the top-left corner
     * of the Tile where it will be drawn
     */
    public void draw(Canvas canvas, int xPosition, int yPosition, long time) {
        // grab the terrain and draw it on the canvas
        Terrain terrain = this.getTerrain();
        terrain.draw(canvas, xPosition, yPosition);

        // if this Tile has an occupant, then grab
        // it and draw it on the canvas
        if (this.hasOccupant()) {
            TileOccupant occupant = this.getOccupant();
            occupant.draw(canvas, xPosition, yPosition, time);
        }
    }
}
