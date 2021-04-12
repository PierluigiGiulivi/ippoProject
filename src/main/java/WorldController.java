import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Class WorldController.
 *
 * A "WorldController" is the bridge between the View class and the Model (all the other classes);
 * The WorldController initiates all the other classes and sets up an initial situation.
 * It will receive orders from the View and execute them using the other classes.
 *
 * @author Pierluigi Giulivi
 * @version 02/12/2020
 */

public class WorldController {

    private Player player;

    @FXML private ImageView roomView;
    @FXML private ImageView itemView1;
    @FXML private ImageView itemView2;
    @FXML private Button Enter;
    @FXML private Button PutDown;


	public void Initialise() {

        // create the images
        Image imgRoomANORTH, imgRoomAWEST, imgRoomASOUTH, imgRoomAEST, imgItem1, imgItem2,imgRoomBNORTH, imgRoomBWEST, imgRoomBSOUTH, imgRoomBEST;
        imgRoomANORTH = new Image("imgRoomANORTH.png");
        imgRoomAWEST = new Image("imgRoomAWEST.png");
        imgRoomASOUTH = new Image("imgRoomASOUTH.png");
        imgRoomAEST = new Image("imgRoomAEST.png");
        imgItem1 = new Image("palmTree.png");
        imgItem2 = new Image("camera.png");
        imgRoomBNORTH = new Image("imgRoomBNORTH.png");
        imgRoomBWEST = new Image("imgRoomBWEST.png");
        imgRoomBSOUTH = new Image("imgRoomBSOUTH.png");
        imgRoomBEST = new Image("imgRoomBEST.png");

        // create the walls
        Wall wallRoomANORTH, wallRoomAWEST, wallRoomASOUTH, wallRoomAEST, wallRoomBNORTH, wallRoomBWEST, wallRoomBSOUTH, wallRoomBEST;
        wallRoomANORTH = new Wall(imgRoomANORTH);
        wallRoomAWEST = new Wall(imgRoomAWEST);
        wallRoomASOUTH = new Wall(imgRoomASOUTH);
        wallRoomAEST = new Wall(imgRoomAEST);
        wallRoomBNORTH = new Wall(imgRoomBNORTH);
        wallRoomBWEST = new Wall(imgRoomBWEST);
        wallRoomBSOUTH = new Wall(imgRoomBSOUTH);
        wallRoomBEST = new Wall(imgRoomBEST);

        // create the items
        Item palmTree, camera;
        palmTree = new Item(imgItem1);
        camera = new Item(imgItem2);

        // create the Map Direction -> Wall for a room
        HashMap<Direction, Wall> wallsRoomA = new HashMap<Direction, Wall>();
        wallsRoomA.put(Direction.NORTH,wallRoomANORTH);
        wallsRoomA.put(Direction.WEST,wallRoomAWEST);
        wallsRoomA.put(Direction.SOUTH,wallRoomASOUTH);
        wallsRoomA.put(Direction.EST,wallRoomAEST);

        HashMap<Direction, Wall> wallsRoomB = new HashMap<Direction, Wall>();
        wallsRoomB.put(Direction.NORTH,wallRoomBNORTH);
        wallsRoomB.put(Direction.WEST,wallRoomBWEST);
        wallsRoomB.put(Direction.SOUTH,wallRoomBSOUTH);
        wallsRoomB.put(Direction.EST,wallRoomBEST);

        // create the Sets of items for the rooms
        Set<Item> roomAItems = new HashSet<Item>();
        roomAItems.add(palmTree);

        Set<Item> roomBItems = new HashSet<Item>();

        // create the Set of items for the player
        Set<Item> playerItems = new HashSet<Item>();
        playerItems.add(camera);

        // create the rooms
        Room roomA, roomB;
        roomA = new Room(wallsRoomA, roomAItems);
        roomB = new Room(wallsRoomB, roomBItems );

        // create the Maps Direction -> Room for the exits of the rooms
        HashMap<Direction, Room> exitsRoomA = new HashMap<Direction, Room>();
        exitsRoomA.put(Direction.NORTH,roomB);
        roomA.addExits(exitsRoomA);

        HashMap<Direction, Room> exitsRoomB = new HashMap<Direction, Room>();
        exitsRoomB.put(Direction.SOUTH,roomA);
        roomB.addExits(exitsRoomB);

        // create the player
        player = new Player(roomA, playerItems, Direction.SOUTH);

        // sets up initial environment
        roomView.setImage(getCurrentImage());
        isExit();
        showItems();
    }


    /**
     * @return Image of the wall the player is looking at.
     */
    public Image getCurrentImage()
    {
        Room room = player.getRoom();
        Direction direction = player.getDirection();
        Wall wall = room.getWall(direction);
        return wall.getImage();
    }

    /**
     * Checks if wall is an exit and accordingly enables or disables the Enter Button.
     */
    public void  isExit()
    {
        Room room = player.getRoom();

        if ( room.getExits().containsKey(player.getDirection()) ) {
            Enter.setDisable(false);
        }
        else Enter.setDisable(true);
    }

    /**
     * Turns the player to the left.
     */
    public void  turnLeft()
    {
        player.turnLeft();
        roomView.setImage(getCurrentImage());
        isExit();
    }

    /**
     * Turns the player to the Right.
     */
    public void  turnRight()
    {
        player.turnRight();
        roomView.setImage(getCurrentImage());
        isExit();
    }

    /**
     * The player moves forward and changes room.
     */
    public void  moveForward()
    {
        Room room = player.getRoom();
        player.changeRoom( room.getExits().get( player.getDirection() ) );
        roomView.setImage(getCurrentImage());
        isExit();
        showItems();
    }

    /**
     * The player clicks on an Item image,
     * this item is added to the player items and remove from the items in the room.
     */
    public void pickUp(MouseEvent event) // inspired from https://stackoverflow.com/questions/45867871/fxml-javafx-how-to-make-an-imageview-clickable [02/12/2020]
    {
        Room room = player.getRoom();
        ImageView source = (ImageView) event.getSource(); // inspired from https://stackoverflow.com/questions/51456418/how-to-check-which-button-in-fxml-has-invoked-a-function-in-controller-java-wh [02/12/2020]

        Iterator<Item> iterator = room.getItems().iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (source.getImage() == item.getImage())
            {
                player.getItems().add(item);
                iterator.remove();
            }
        }
        showItems();
        PutDown.setDisable(false);
    }

    /**
     * The player puts in the room all the items he/she holds,
     * this items are removed from the player items and added to the items in the room.
     */
    public void putDown()
    {
        Room room = player.getRoom();

        Iterator<Item> iterator = player.getItems().iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            room.getItems().add(item);
            iterator.remove();
        }
        showItems();
        PutDown.setDisable(true);
    }

    /**
     * Shows the items in the present room.
     */
    public void  showItems()
    {
        itemView1.setImage(null);
        itemView2.setImage(null);

        Iterator<Item> item = player.getRoom().getItems().iterator();
        while(item.hasNext())
        {
            if (itemView1.getImage() == null)
            {
                itemView1.setImage(item.next().getImage());
            }
            else itemView2.setImage(item.next().getImage());
        }
    }

}
