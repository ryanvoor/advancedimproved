package model;

/**
 * TODO
 * @author Ryan Voor
 * @version 1.0
 */
public class Tile {
    private TileOccupant occupant;
    private Terrain terrain;

    /**
     * TODO
     */
    public Tile(Terrain terrain, TileOccupant occupant) {
        this.terrain = terrain;
        this.occupant = occupant;
    }

    /**
     * TODO
     */
    public Tile(Terrain terrain) {
        this(terrain, null);
    }

    // getters

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

    // setters

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
}
