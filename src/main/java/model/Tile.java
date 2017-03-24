package model;

// javafx imports
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class that represents a Tile on a Map
 * this class holds the occupant unit(s) and knows the
 * Terrain of this Tile on the Map
 * @author Ryan Voor
 * @version 1.0
 */
public class Tile {

    // constants
    private static final int widthOfATileInPixels  = 100;
    private static final int heightOfATileInPixels = 100;

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
        return Tile.widthOfATileInPixels;
    }

    /**
     * TODO
     */
    public static int getHeightOfATileInPixels() {
        return Tile.heightOfATileInPixels;
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
     * TODO
     */
    public void draw(Canvas canvas, int xPosition, int yPosition) {
        // TODO this needs to be finished
        // TODO this needs to be tested, it might be finished if it works lol
        // TODO in order to test this I need to implement getImage for the Terrains

        // TODO maybe I could draw the terrain image and then the
        // occupant image on top of it, so using 2 separate draw calls
        // I won't know if this works until I try it however
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Image terrainImage = this.getTerrain().getImage(); // TODO
        graphicsContext.drawImage(
            terrainImage,
            xPosition,
            yPosition,
            xPosition + Tile.getWidthOfATileInPixels(),
            yPosition + Tile.getHeightOfATileInPixels()
        );

        // TODO NOTE: if I did it this way then this would require all the
        // occupant images be the same size as the terrainImages except
        // they would need transparent backgrounds so you can see the terrain
        if (this.hasOccupant()) {
            Image occupantImage = this.getOccupant().getImage(); // TODO
            graphicsContext.drawImage(
                occupantImage,
                xPosition,
                yPosition,
                xPosition + Tile.getWidthOfATileInPixels(),
                yPosition + Tile.getHeightOfATileInPixels()
            );
        }
    }

}
