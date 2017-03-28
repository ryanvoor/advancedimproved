package model.map;

// javafx imports
import javafx.scene.canvas.Canvas;

// this project imports
import model.drawable.terrain.Terrain;
import model.drawable.tileOccupant.TileOccupant;

/**
 * Class that represents a Tile on a Map
 * this class holds the occupant unit(s) and knows the
 * Terrain of this Tile on the Map
 * @author Ryan Voor
 * @version 1.0
 */
public class Tile {

    // constants
    private static final int WIDTH_OF_A_TILE_IN_PIXELS  = 100;
    private static final int HEIGHT_OF_A_TILE_IN_PIXELS = 100;

    // instance variables
    private TileOccupant occupant;
    private Terrain terrain;

    /**
     * TODO
     */
    public Tile(Terrain terrain, TileOccupant occupant) {
        this.terrain  = terrain;
        this.occupant = occupant;
    }

    /**
     * TODO
     */
    public Tile(Terrain terrain) {
        this(terrain, null);
    }

    /////////////
    // Getters //
    /////////////

    /**
     * TODO
     */
    public static int getWidthOfATileInPixels() {
        return Tile.WIDTH_OF_A_TILE_IN_PIXELS;
    }

    /**
     * TODO
     */
    public static int getHeightOfATileInPixels() {
        return Tile.HEIGHT_OF_A_TILE_IN_PIXELS;
    }

    /**
     * TODO
     */
    public TileOccupant getOccupant() {
        return this.occupant;
    }

    /**
     * TODO
     */
    public Terrain getTerrain() {
        return this.terrain;
    }

    /**
     * TODO
     */
    public boolean hasOccupant() {
        return null != this.getOccupant();
    }

    /////////////
    // Setters //
    /////////////

    /**
     * TODO
     */
    public void setOccupant(TileOccupant occupant) {
        this.occupant = occupant;
    }

    /**
     * TODO
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
    public void draw(Canvas canvas, int xPosition, int yPosition) {
        // grab the terrain and draw it on the canvas
        Terrain terrain = this.getTerrain();
        terrain.draw(canvas, xPosition, yPosition);

        // if this Tile has an occupant, then grab
        // it and draw it on the canvas
        if (this.hasOccupant()) {
            TileOccupant occupant = this.getOccupant();
            occupant.draw(canvas, xPosition, yPosition);
        }
    }
}
