import java.util.Set;

/**
 * Class Player.
 *
 * A "Player" represents the user. It stores in which room is the player at which direction is he/she looking at
 * and which items she/he holds.
 *
 * @author Pierluigi Giulivi
 * @version 02/12/2020
 */

public class Player {

    private Room currentRoom;  // stores in which room is the player
    private Set<Item> items;  // stores the items in the room
    private Direction direction;  // stores the direction the player is looking at


    /**
     * Create a player with initial set of items and predefined location.
     * @param currentRoom The initial room where the player starts.
     * @param items A Set of the items hold by the player.
     * @param direction The initial direction the player is looking at.
     */
    public Player(Room currentRoom, Set<Item> items, Direction direction)
    {
        this.currentRoom = currentRoom;
        this.items = items;
        this.direction = direction;
    }


    /**
     * @return Set of items hold by the player.
     */
    public Set<Item> getItems()
    {
        return items;
    }

    /**
     * @return Room where the player is.
     */
    public Room getRoom()
    {
        return currentRoom;
    }

    /**
     * @return The direction the player is looking at.
     */
    public Direction getDirection()
    {
        return direction;
    }

    /**
     * @param room New room where the player is going.
     */
    public void changeRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * Changes the direction to the left.
     */
    public void turnLeft()
    {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.EST;
                break;
            case EST:
                direction = Direction.NORTH;
                break;
        }
    }

    /**
     * Changes the direction to the right.
     */
    public void turnRight()
    {
        switch (direction) {
            case NORTH:
                direction = Direction.EST;
                break;
            case EST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
    }

}
