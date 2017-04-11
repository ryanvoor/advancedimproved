package model.drawable;

// javafx imports
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

// this project imports
import model.map.Tile;

// TODO give some thought to restructuring this interface
// so we can separate the animation stuff from the regular drawing
// the way I've set up the methods etc. here feels really gross
// e.g. I shouldn't have a default implementation for all these methods
// and i shouldnt have these overloaded getImage methods. They really should
// be separate.
//
// consider this solution: make 2 sub classes/sub interfaces of Drawable?
// idk if thatll work....... damn this is hard i might need some diagrams
// or something.

/**
 * Any class that implements this Interface can
 * be drawn onto a Canvas
 * @author Ryan Voor
 */
public interface Drawable {

    /////////////////////////////
    // Non-Implemented Methods //
    /////////////////////////////

    // TODO once restructuring happens I ought to have
    // some methods w/o default implementations again
    // I think that the best plan is probably to just
    // make all the classes that implement this class
    // have ArrayList's of image urls. If you dont want
    // to be animated then you just only put one image
    // url into the constructor

    //////////////////////////////////////////
    // Methods with Default Implementations //
    //////////////////////////////////////////

    /**
     * getter for the Image that should be drawn to
     * represent this class
     * @return Image the image to be returned
     */
    default Image getImage() {
        // this is just here so I don't have to worry about
        // giving every subclass an implementation of this
        // method. There should be no class that implements
        // both or neither of these overloaded methods
        return this.getImage(-1);
    }

    /**
     * getter for the Image that should be drawn to
     * represent this class
     * @param time the time that should be used to
     * calculate animation images
     * @return Image the image to be returned
     */
    default Image getImage(long time) {
        // the default implementation just ignores
        // the parameter and calls the other of the
        // overloaded methods that takes in no parameters
        return this.getImage();
    }

    /**
     * draws the Image that should be drawn to represent this class
     * onto the parameter canvas at the paremeter x and y coordinates
     * @param canvas the canvas upon which to drawn the image
     * @param xPosition the X-coordinate of the location on
     * the canvas where the top left corner of the image should be
     * @param yPosition the Y-coordinate of the location on
     * the canvas where the top left corner of the image should be
     * @param time the time used to calculate animation images
     */
    default void draw(Canvas canvas, int xPosition, int yPosition, long time) {
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
            Tile.getWidthOfATileInPixels(),
            Tile.getHeightOfATileInPixels()
        );
    }

    /**
     * draws the Image that should be drawn to represent this class
     * onto the parameter canvas at the paramter x and y coordinates
     * NOTE: behavior of this method is undefined for classes that
     * implement animation
     * @param canvas the canvas upon which to drawn the image
     * @param xPosition the X-coordinate of the location on
     * the canvas where the top left corner of the image should be
     * @param yPosition the Y-coordinate of the location on
     * the canvas where the top left corner of the image should be
     */
    default void draw(Canvas canvas, int xPosition, int yPosition) {
        // just give a dummy time. We are assuming that if
        // this version of the overloaded methods is getting
        // called then it is on an object that does not
        // implement animation so it shouldn't implement
        // the getImage(time) method so it should just call getImage
        // and completely ignore the time parameter
        this.draw(canvas, xPosition, yPosition, -1);
    }
}
