import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item item;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room exit) 
    {
        exits.put(direction, exit);
    }
    
    /**
     * Insere um item na sala
     * @param item o item a inserir
     */
    public void setItem(Item item)
    {
    	this.item = item;
    }
    
    
    /**
     * @param direction uma String com a direção
     * @return A saída adequada
     */
    public Room getExit(String direction)
    {	
    	return exits.get(direction);
    }
    
    /**
     * Retorna uma descrição das saídas da sala,
     * por exemplo, "Saídas: norte oeste".
     * @return Uma descrição das saídas disponíveis
     */
    public String getExitString() 
    {
    	String returnString = "Saídas: ";
        
    	for (String exit : exits.keySet()) {
    		returnString += exit + " ";
    	}
        
        return returnString;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Retorna uma descrição longa desta sala, na forma:
     *   Você está na cozinha.
     *   Saídas: norte oeste
     * @return Uma descrição da sala, incluindo saídas.
     */
    public String getLongDescription()
    {
    	String itemStr = item != null ? 
				   "Esta sala contém " + item.getDescription() + "\n"
				   : "";
    	return "Você está " + description + ".\n" +
    		    itemStr
    			+ getExitString();
    }

}