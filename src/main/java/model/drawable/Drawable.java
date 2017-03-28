package model.drawable;

// javafx imports
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

// this project imports
import model.map.Tile;

/**
 * TODO
 */
public interface Drawable {

    /////////////////////////////
    // Non-Implemented Methods //
    /////////////////////////////

    /**
     * TODO
     */
    Image getImage();

    //////////////////////////////////////////
    // Methods with Default Implementations //
    //////////////////////////////////////////

    /**
     * TODO
     */
    default void draw(Canvas canvas, int xPosition, int yPosition) {
        // grab graphics context object and image
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Image image = this.getImage();

        // draw the image onto the canvas
        graphicsContext.drawImage(
            // the image being drawn
            image,
            // x and y positions in pixels of top left corner of image
            xPosition,
            yPosition,
            // width and height of the image to be drawn
            Tile.getWidthOfATileInPixels(),
            Tile.getHeightOfATileInPixels()
        );
    }
}
