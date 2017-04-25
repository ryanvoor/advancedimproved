package model.drawable;

// javafx imports
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

// this project imports
import model.map.Tile;

/**
 * Any class that implements this Interface can
 * be drawn onto a Canvas
 * @author Ryan Voor
 */
public interface Drawable {

    /////////////////////////////
    // Non-Implemented Methods //
    /////////////////////////////

    /**
     * getter for the Image that should be drawn to
     * represent this class
     * @param time the time that should be used to
     * calculate animation images
     * @return Image the image to be returned
     */
    Image getImage(long time);

    //////////////////////////////////////////
    // Methods with Default Implementations //
    //////////////////////////////////////////

    /**
     * draws the Image that should be drawn to represent this class
     * onto the parameter canvas at the paremeter x and y coordinates
     * overloaded with a version that takes in a variable width and
     * height rather than using the width and height of a Tile as defaults
     * @param canvas the canvas upon which to drawn the image
     * @param xPosition the X-coordinate of the location on
     * the canvas where the top left corner of the image should be
     * @param yPosition the Y-coordinate of the location on
     * the canvas where the top left corner of the image should be
     * @param time the time used to calculate animation images
     */
    default void draw(Canvas canvas, int xPosition, int yPosition, long time) {
        this.draw(
            canvas,
            xPosition,
            yPosition,
            Tile.getWidthOfATileInPixels(),
            Tile.getHeightOfATileInPixels(),
            time
        );
    }

    /**
     * draws the Image that should be drawn to represent this class
     * onto the parameter canvas at the paremeter x and y coordinates,
     * overloaded with a version that does not take in a width and height
     * and uses the width and height of a Tile as defaults
     * @param canvas the canvas upon which to drawn the image
     * @param xPosition the X-coordinate of the location on
     * the canvas where the top left corner of the image should be
     * @param yPosition the Y-coordinate of the location on
     * the canvas where the top left corner of the image should be
     * @param width the width in pixels that should be used to draw the image
     * @param height the height in pixels that should be used to draw the image
     * @param time the time used to calculate animation images
     */
    default void draw(Canvas canvas, int xPosition, int yPosition,
        int width, int height, long time) {
        // grab graphics context object and image
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Image image = this.getImage(time);

        // draw the image onto the canvas
        graphicsContext.drawImage(
            // the image being drawn
            image,
            // x and y positions in pixels of top left corner of image
            xPosition,
            yPosition,
            // width and height of the image to be drawn
            width,
            height
        );
    }
}
