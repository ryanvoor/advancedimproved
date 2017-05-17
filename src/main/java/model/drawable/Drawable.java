package model.drawable;

// java standard library imports
import java.util.ArrayList;

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
public abstract class Drawable {


    ///////////////
    // constants //
    ///////////////


    // this constant was calculated via trial and error
    // in order to determine how long between animation
    // frames in order to make the animations look good
    // see the draw method for information about how it
    // is actually used
    private long animationTimingConstant = 580000000L;


    ////////////////////////
    // instance variables //
    ////////////////////////


    // the urls of the files that will be shown
    // when this class is drawn
    private ArrayList<String> imageFileUrls;


    /////////////////
    // Constructor //
    /////////////////


    /**
     * constructor for the Drawable class
     * @param imageFileUrls the urls of the files of
     * the Images that should be shown on the Tile
     * that this Drawable is located on
     */
    public Drawable(ArrayList<String> imageFileUrls) {
        this.imageFileUrls = imageFileUrls;
    }


    /////////////
    // Getters //
    /////////////


    /**
     * getter for the urls of the Files that
     * should be shown on the Tile that this
     * Building is located on
     * @return ArrayList<String> the urls of the files of
     * the Images that should be shown on the Tile that this
     * Building is located on
     */
    public ArrayList<String> getImageFileUrls() {
        return this.imageFileUrls;
    }


    /**
     * getter for the Image that should be drawn to
     * represent this class
     * @param time the time that should be used to
     * calculate animation images
     * @return Image the image to be returned
     */
    public Image getImage(long time) {
        // calculate which image in the animation we need
        // this cast is ok since we should always have a small number
        // of images so the result will fit into an int
        // we divide the time by a constant and then mod by the
        // number of animation frames we have to work with. This
        // effectively gives us an index that should change every
        // X frames where X is the constant that we divide by.
        int indexOfCurrentImage
            = (int) ((time / this.getAnimationTimingConstant()) % this.getNumberOfImages());

        // grab the image that we need
        ArrayList<Image> images = this.getImages();

        // return it
        return images.get(indexOfCurrentImage);
    }


    /**
     * getter for the Images that should be shown
     * on the Tile that this Building occupies
     * @return ArrayList<Image> the Image that should be shown
     * on the Tile that this Building occupies
     */
    public ArrayList<Image> getImages() {
        // build the ArrayList of images from the
        // ArrayList of file urls
        ArrayList<Image> images = new ArrayList<Image>();
        for (String imageFileUrl : this.getImageFileUrls()) {
            images.add(new Image(imageFileUrl));
        }

        return images;
    }


    /**
     * getter for the number of images in the animation
     * for this Building
     * @return int the number of images in the animation
     * for this Building
     */
    public int getNumberOfImages() {
        ArrayList<String> imageFileUrls = this.getImageFileUrls();
        return imageFileUrls.size();
    }


    /**
     * private getter for the animation timing constant,
     * The number that was calcualted via trial and error
     * in order to determine how long between animation
     * frames in order to make the animations look good
     * @return long the animation timing constant
     */
    private long getAnimationTimingConstant() {
        return this.animationTimingConstant;
    }


    //////////////////
    // Real Methods //
    //////////////////


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
    public void draw(Canvas canvas, int xPosition, int yPosition, long time) {
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
    public void draw(Canvas canvas, int xPosition, int yPosition,
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
