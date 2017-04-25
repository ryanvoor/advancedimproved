package model.map;

// javafx imports
import javafx.scene.canvas.Canvas;

// this project imports
import model.drawable.building.Building;
import model.drawable.terrain.Terrain;
import model.drawable.tileOccupant.TileOccupant;

// TODO at some point this class will need to be modified to hold
// multiple occupants so the gameplay dream can be implemented
// TODO also alter this class to hold buildings since those will
// be separate from TileOccupants soon enough (if not already)
// TODO I should consider only allowing buildings to be built on certain
// Tiles as a balance/map design tool

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
    private TileOccupant invader;
    private Terrain terrain;
    private Building building;


    //////////////////
    // Constructors //
    //////////////////

    /**
     * constructor for the Tile class, takes in
     * a Terrain, TileOccupant, and building
     * @param terrain the terrain of this Tile
     * @param building the building of this Tile
     * @param occupant the occupant of this Tile
     * @param invader the invading TileOccupant of this Tile
     */
    public Tile(Terrain terrain, Building building, TileOccupant occupant, TileOccupant invader) {
        this.terrain  = terrain;
        this.building = building;
        this.occupant = occupant;
        this.invader  = invader;
    }

    /**
     * constructor for the Tile class, just takes in
     * a Terrain and 2 TileOccupants, this
     * Tile will have no building
     * @param terrain the terrain of this Tile
     * @param occupant the occupant of this Tile
     * @param invader the invading TileOccupant of this Tile
     */
    public Tile(Terrain terrain, TileOccupant occupant, TileOccupant invader) {
        this(terrain, null, occupant, invader);
    }

    /**
     * constructor for the Tile class, just takes in
     * a Terrain, a Building, and a TileOccupant, this
     * Tile will have no invader
     * @param terrain the terrain of this Tile
     * @param building the building of this Tile
     * @param occupant the occupant of this Tile
     */
    public Tile(Terrain terrain, Building building, TileOccupant occupant) {
        this(terrain, building, occupant, null);
    }

    /**
     * constructor for the Tile class, just takes in
     * a Terrain and a TileOccupant, this
     * Tile will have no building and no invader
     * @param terrain the terrain of this Tile
     * @param occupant the occupant of this Tile
     */
    public Tile(Terrain terrain, TileOccupant occupant) {
        this(terrain, null, occupant, null);
    }

    /**
     * constructor for the Tile class, just takes in
     * a Terrain and a Building, this
     * Tile will have no occupant and no invader
     * @param terrain the terrain of this Tile
     * @param building the building of this Tile
     */
    public Tile(Terrain terrain, Building building) {
        this(terrain, building, null, null);
    }

    /**
     * constructor for the Tile class, just takes in
     * a Terrain, this Tile will have no occupant, building, or invader
     * @param terrain the terrain of this Tile
     */
    public Tile(Terrain terrain) {
        this(terrain, null, null, null);
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
     * the getter for the Terrain of this Tile
     * @return Terrain the Terrain of this Tile
     */
    public Terrain getTerrain() {
        return this.terrain;
    }

    /**
     * the getter for the building of this Tile
     * @return Building the building of this Tile
     */
    public Building getBuilding() {
        return this.building;
    }

    /**
     * the getter for the occupant of this Tile
     * @return TileOccupant the occupant of this Tile
     */
    public TileOccupant getOccupant() {
        return this.occupant;
    }

    /**
     * the getter for the invader of this Tile
     * @return TileOccupant the invader of this Tile
     */
    public TileOccupant getInvader() {
        return this.invader;
    }

    /**
     * returns whether this Tile has a building
     * @return boolean whether this Tile has a building
     */
    public boolean hasBuilding() {
        return null != this.getBuilding();
    }


    /**
     * returns whether this Tile has an occupant
     * @return boolean whether this Tile has an occupant
     */
    public boolean hasOccupant() {
        return null != this.getOccupant();
    }

    /**
     * returns whether this Tile has an invader
     * @return boolean whether this Tile has an invader
     */
    public boolean hasInvader() {
        return null != this.getInvader();
    }


    /////////////
    // Setters //
    /////////////

    /**
     * setter for the Terrain of this Tile
     * @param terrain the Terrain to be placed on this Tile
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    /**
     * setter for the building of this Tile
     * @param Building the building to be placed on this Tile
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * setter for the occupant of this Tile
     * @param occupant the occupant to be placed on this Tile
     */
    public void setOccupant(TileOccupant occupant) {
        this.occupant = occupant;
    }

    /**
     * setter for the invader of this Tile
     * @param invader the invader to be placed on this Tile
     */
    public void setInvader(TileOccupant invader) {
        this.invader = invader;
    }


    //////////////////
    // Real Methods //
    //////////////////

    /**
     * draws the terrain, building, and occupant of this Tile onto the
     * canvas at the specified position
     * @param canvas the canvas upon which this Tile will be drawn
     * @param xPosition the X position in pixels of the top-left corner
     * of the Tile where it will be drawn
     * @param yPosition the Y position in pixels of the top-left corner
     * of the Tile where it will be drawn
     */
    public void draw(Canvas canvas, int xPosition, int yPosition, long time) {
        // grab the terrain and draw it on the canvas
        Terrain terrain = this.getTerrain();
        terrain.draw(canvas, xPosition, yPosition, time);

        // if this Tile has a building, then grab
        // it and draw it on the canvas
        if (this.hasBuilding()) {
            Building building = this.getBuilding();
            building.draw(canvas, xPosition, yPosition, time);
        }

        // if this Tile has an occupant, then grab
        // it and draw it on the canvas
        if (this.hasOccupant()) {
            TileOccupant occupant = this.getOccupant();
            occupant.draw(canvas, xPosition, yPosition, time);
        }
    }
}
