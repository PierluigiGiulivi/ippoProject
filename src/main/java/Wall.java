import javafx.scene.image.Image;

/**
 * Class Wall.
 *
 * A "Wall" represents one side of a room.
 *
 * @author Pierluigi Giulivi
 * @version 28/11/2020
 */

public class Wall {

    private Image image;  // stores the image of the wall


    /**
     * Create a wall.
     * @param image Image of the wall.
     */
    public Wall(Image image)
    {
        this.image = image;
    }


    /**
     * @return Image of the wall
     */
    public Image getImage()
    {
        return image;
    }

}
