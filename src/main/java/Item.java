import javafx.scene.image.Image;

/**
 * Class Item.
 *
 * An "Item" represents something that can be hold by a player or be set in a room.
 *
 * @author Pierluigi Giulivi
 * @version 28/11/2020
 */

public class Item {

    private Image image;  // stores the image of the item


    /**
     * Create an item.
     * @param image Image of the item.
     */
    public Item(Image image)
    {
        this.image = image;
    }


    /**
     * @return Image of the item
     */
    public Image getImage()
    {
        return image;
    }

}
