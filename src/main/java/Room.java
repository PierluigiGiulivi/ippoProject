import java.util.Set;
import java.util.HashMap;

/**
 * Class Room.
 *
 * A "Room" represents one location. It is connected to other rooms via exits.
 * For each existing exit, the room stores a reference to the neighboring room.
 *
 * @author Pierluigi Giulivi
 * @version 02/12/2020
 */

public class Room {

    private HashMap<Direction, Wall> walls;  // stores the walls of the room
    private Set<Item> items;  // stores the items in the room
    private HashMap<Direction, Room> exits;  // stores to which rooms the exits take too


    /**
     * Create a room with initial set of items and predefined walls.
     * @param walls A HashMap of the directions and walls in the room.
     * @param items A Set of the items in the room.
     */
    public Room(HashMap<Direction, Wall> walls, Set<Item> items)
    {
       this.walls = walls;
       this.items = items;
       exits = new HashMap<Direction, Room>();
    }


    /**
     * @return Set of items in the room.
     */
    public Set<Item> getItems()
    {
        return items;
    }

    /**
     * @param direction Either NORTH, WEST, SOUTH or EST.
     * @return Set of items in the room.
     */
    public Wall getWall(Direction direction)
    {
        return walls.get(direction);
    }

    /**
     * @return HashMap of the exits of the room.
     */
    public HashMap<Direction, Room> getExits()
    {
        return exits;
    }

    /**
     * @param mapExits Map of the exits of the room.
     */
    public void addExits(HashMap<Direction, Room> mapExits)
    {
        exits = mapExits;
    }

}
