package model.drawable.building;

// java standard library imports
import java.util.ArrayList;

// javafx imports
import javafx.scene.image.Image;

// this project imports
import model.drawable.Drawable;

/**
 * abstract class that represents a building on the Map
 * that is located on a Tile, these can produce units or
 * heal units or provide vision etc.
 * @author Ryan Voor
 */
public abstract class Building implements Drawable {

    // TODO I need to abstract of lot of this class and
    // the TileOccupant class into the Drawable Interface

    // instance variables
    private ArrayList<String> fileImageUrls;

    /**
     * constructor for the Building class
     * @param fileImageUrl the url of the file of
     * the Image that should be shown on the Tile
     * that this Buildling is located on
     */
    public Building(ArrayList<String> fileImageUrls) {
        this.fileImageUrls = fileImageUrls;
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
    public ArrayList<String> getFileImageUrls() {
        return this.fileImageUrls;
    }

    @Override
    public Image getImage(long time) {
        return this.getCurrentImage(time);
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
        for (String fileImageUrl : this.getFileImageUrls()) {
            images.add(new Image(fileImageUrl));
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
