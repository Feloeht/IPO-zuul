import java.util.Stack;
/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la création des joueurs du jeu
 * et de fonctions leur étant associées
 * 
 * @author  Théo LEFEVRE
 * @version 2019.05.08
 */
public class Player
{
    private String aNom;
    private double aPoidsJoueur;
    private double aPoidsMax;
    private Room aCurrentRoom;
    private Stack<Room> aBackRoom;
    private ItemList aItemList;
    
    /**
     * Constructeur d'objets de classe Player
     * @param pNom Nom du joueur
     */
    public Player(final String pNom)
    {
        this.aNom = pNom;
        this.aPoidsJoueur = 60; 
        this.aPoidsMax = 90;
        this.aBackRoom = new Stack<Room>();
        this.aItemList = new ItemList();
    } // Player()

    /**
     * Accesseur nom du joueur
     * @return Nom du joueur
     */
    public String getNom(){
        return this.aNom;
    } // getNom()
    
    /**
     * Accesseur inventaire du joueur
     * @return Inventaire du joueur
     */
    public ItemList getInventory(){
        return this.aItemList;
    } // getItemList()
    
    /**
     * Accesseur position du joueur
     * @return Localisation actuelle du player
     */
    public Room getPosition(){
        return this.aCurrentRoom;
    } // getPosition()

    /**
     * Accesseur salle precedente
     * @return Salle precedente
     */
    public Room getBackRoom(){
        return this.aBackRoom.pop();
    } // getBackRoom()
    
    /**
     * Accesseur poids que peut porter le joueur
     * @return Poids que peut porter le joueur
     */
    public double getPoids(){
        return this.aPoidsJoueur;
    } // getPoids()
        
    /**
     * Accesseur poids max que peut porter le joueur
     * @return Poids max
     */
    public double getPoidsMax(){
        return this.aPoidsMax;
    } // getPoidsMax()
    
    /**
     * Modificateur poids que peut porter le joueur
     * @param pPoids Poids que peut porter le joueur
     */
    public void setPoids(final double pPoids){
        this.aPoidsJoueur = pPoids;
    } // setPoids()
    
    /**
     * Modificateur poids max que peut porter le joueur
     * @param pPoidsMax Poids max
     */
    public void setPoidsMax(final double pPoidsMax){
        this.aPoidsJoueur = pPoidsMax;
    } // setPoidsMax()
    
    /**
     * Methode test si le joueur depasse sa capacite de charge
     * @param pPoidsItem Le Poids d'un item a tester
     * @return true ou false si depasse ou pas
     */
    public boolean takePoidsOK(final double pPoidsItem){
        return this.aPoidsJoueur + pPoidsItem <= this.aPoidsMax;
    } // takePoidsOK()
    
    /**
     * Modificateur de la position du joueur
     * @param pPosition Room dans laquelle le joueur se trouvera
     */
    public void setPosition(final Room pPosition){
        this.aCurrentRoom = pPosition;
    } // setPosition()
    
    /**
     * Procedure ajout Room dans la pile de backroom
     * @param pCurrentRoom Rom actuelle du joueur
     */
    public void setBackRoom(final Room pCurrentRoom){
        this.aBackRoom.push(pCurrentRoom);
    } // setBackRoom()
    
    /**
     * Procedure vidage pile de backroom
     */
    public void clearBackRoom(){
        this.aBackRoom.clear();
    } // setBackRoom()
    
    /**
     * Methode test si Stack vide
     * @return true ou false si vide ou non
     */
    public boolean backRoomIsEmpty(){
        return this.aBackRoom.empty();
    } // backRoomIsEmpty
    
    /**
     * Methode retour Item (adresse) associé a une string
     * @param pNom nom de l'item sous forme de string
     * @return Item
     */
    public Item getPlayerItem(final String pNom){
        return this.aItemList.getItem(pNom);
    } // getPlayerItem()
    
    /**
     * Methode affichage de l'inventaire
     * @return inventaire sous forme de string
     */
    public String inventaire(){
        if(this.aItemList.isEmpty()) return "Votre inventaire est vide";
        else return "Vous possedez : " + this.aItemList.getItemString();
    } // inventaire()
    
} // Player
