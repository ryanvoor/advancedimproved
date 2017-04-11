package model.drawable.tileOccupant;

// java standard library imports
import java.util.ArrayList;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.Drawable;

/**
 * abstract class that represents something on the Map
 * that occupies a Tile, these can be units in an
 * army, cities or other buildings and anything else we
 * come up with to put onto Tiles
 * @author Ryan Voor
 */
public abstract class TileOccupant implements Drawable {

    // instance variables
    private ArrayList<String> fileImageUrls;

    /**
     * constructor for the TileOccupant class
     * @param fileImageUrls the urls of the files of
     * the Images that should be shown on the Tile
     * that this TileOccupant occupies
     */
    public TileOccupant(ArrayList<String> fileImageUrls) {
        this.fileImageUrls = fileImageUrls;
    }

    /////////////
    // Getters //
    /////////////

    /**
     * getter for the url of the File that
     * @return ArrayList<String> the url of the file of
     * the Image that should be shown on the
     * Tile that this TileOccupant occupies
     */
    public ArrayList<String> getFileImageUrls() {
        return this.fileImageUrls;
    }

    @Override
    public Image getImage(long time) {
        return this.getCurrentImage(time);
    }

    /**
     * getter for the Images that should be shown
     * on the Tile that this TileOccupant occupies
     * @return ArrayList<Image> the Image that should be shown
     * on the Tile that this TileOccupant occupies
     */
    public ArrayList<Image> getImages() {
        // build the ArrayList of images from the
        // ArrayList of file urls
        ArrayList<Image> images = new ArrayList<Image>();
        for (String fileImageUrl : this.getFileImageUrls()) {
            images.add(new Image(fileImageUrl));
        }

        return images;
    }

    /**
     * getter for the number of images in the animation
     * for this TileOccupant
     * @return int the number of images in the animation
     * for this TileOccupant
     */
    public int getNumberOfImages() {
        ArrayList<String> fileImageUrls = this.getFileImageUrls();
        return fileImageUrls.size();
    }

    /**
     * getter for the Image that should currently be shown
     * in the animation based on the parameter time
     * @return Image the image that should currently be shown
     * in the animation based on the parameter time
     */
    public Image getCurrentImage(long time) {
        // TODO I need to move that animation constant or this whole method
        // out of this class/method and to like a static centralized
        // place somewhere I dont necessarily intend to use it anywhere else but
        // that would be a solid idea just for style and consistency
        // purposes

        // calculate which image in the animation we need
        // this cast is ok since we should always have a small number
        // of images so the result will fit into an int
        // we divide the time by a constant and then mod by the
        // number of animation frames we have to work with. This
        // effectively gives us an index that should change every
        // X frames where X is the constant that we divide by.
        // that constant was calculated via trial and error to make
        // the animation timing look good
        int indexOfCurrentImage
            = (int) ((time / 580000000L) % this.getNumberOfImages());

        // grab the image that we need
        ArrayList<Image> images = this.getImages();

        // return it
        return images.get(indexOfCurrentImage);
    }
}
