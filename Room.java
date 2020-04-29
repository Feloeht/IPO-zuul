import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la création des salles du jeu 
 * et la gestion de leur contenu
 * 
 * @author  Théo LEFEVRE
 * @version 2019.05.08
 */
public class Room
{
    private String aDescription;
    private String imageName;
    private ItemList aItemList;
    private HashMap<String, Room> exits;
    private boolean aTrapDoor;

    /**
     * Item constructor
     * @param pDescription  Description de la Room
     * @param pImage        Image de la Room
     * @param pTraped       Porte piege
     */
    public Room(final String pDescription, final String pImage, final boolean pTraped){
        this.aDescription = pDescription;
        this.imageName = pImage;
        this.aItemList = new ItemList();
        this.exits = new HashMap<String, Room>();
        this.aTrapDoor = pTraped;
    } // Room()
    
    /**
     * Accesseur description Room
     * @return Description de la salle
     */
    public String getDescription(){
        return this.aDescription;
    } // getDescription()

    /**
     * Accesseur TrapDoor sous forme de boolean
     * @return true ou false si porte piege ou non
     */
    public boolean getTrapedDoor(){
        return aTrapDoor;
    } // getTrapedDoor()
    
    /**
     * Accesseur ItemList
     * @return Liste des Items sous forme ItemList
     */
    public ItemList getItemList(){
        return this.aItemList;
    } // getItemList()
    
    /**
     * Procedure denition exits des Room
     * @param pDirection    La direction possible
     * @param pRoom         La Room vers laquelle elle pointe
     */
    public void setExits(final String pDirection, final Room pRoom)
    {
        exits.put(pDirection, pRoom);
    } // setExits()

    /**
     * Methode affichage direction exits
     * @param pDirection    Direction
     * @return Salle associée a la direction
     */
    public Room getExit(final String pDirection)
    {
        return exits.get(pDirection);
    } // getExit()

    /**
     * Methode affichage des sorties
     * @return Sorties de la salle actuelle, sous forme d'une chaine de String
     */
    public String getExitString()
    {
        String vExits = "Les sorties :";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            vExits += " " + exit;
        }
        return vExits;
    } // getExitString()
    
    /**
     * Methode affichage avec test des sorties destine aux String des Rooms
     * @return La liste des Items presents dans une Room
     */
    private String getItemString(){
        if(this.getItemList().isEmpty()){
            return "Aucun objet dans cette salle. \n";
        }
        else{
            return "Les Items : " + this.aItemList.getItemString() + "\n";
        }
    } // getItemString()
    
    /**
     * Methode affichage description complete Room
     * @return Description complète de la salle actuelle
     */
    public String getLongDescription()
    {
        return "Vous etes " + this.getDescription() + "\n" 
                            + this.getExitString() + "\n" 
                            + this.getItemString();
    } // getLongDescription()
    
    /**
     * Return a string describing the room's image name
     * @return nom de l'image
     */
    public String getImageName()
    {
        return this.imageName;
    } // getImageName()
    
} // Room
