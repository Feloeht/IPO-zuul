import java.util.HashMap;
import java.util.Set;
/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la création et la gestion de l'inventaire du joueur
 * 
 * @author  Théo LEFEVRE
 * @version 2019.05.08
 */
public class ItemList
{
    private HashMap<String, Item> aItemList;
    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList()
    {
        this.aItemList = new HashMap<String, Item>();
    } // ItemList()
    
    /**
     * Procedure ajout item dans hashmap
     * @param   pNom    Nom de l'item sous forme de string
     * @param   pItem   Item associé
     */
    public void addItem(final String pNom, final Item pItem){
        this.aItemList.put(pNom, pItem);
    } // addItem()
    
    /**
     * Procedure retrait item dans hashmap
     * @param   pNom    Nom de l'item sous forme de string
     */
    public void removeItem(final String pNom){
        this.aItemList.remove(pNom);
    } // removeItem()
    
    /**
     * Methode retour Item (adresse) associé a une string
     * @param pItem nom de l'item sous forme de string
     * @return Item
     */
    public Item getItem(final String pItem){
        return this.aItemList.get(pItem);
    } // getItem()
    
    /**
     * Methode test presence Item dans inventaire joueur
     * @param pNom Nom de l'Item a chercher
     * @return Boolean true ou false si vide ou non
     */
    public boolean hasItem(final String pNom){
        return aItemList.containsKey(pNom);
    } // hasItem()
    
    /**
     * Methode test inventaire vide
     * @return true ou false si vide ou non
     */
    public boolean isEmpty(){
        return aItemList.isEmpty();
    } // isEmpty()
    
    /**
     * Methode retour Item présents dans une Room
     * @return  Item de la Room
     */
    public String getItemString(){
        String vItems = "";
        Set<String> vKeys = this.aItemList.keySet();
        for(String item : vKeys)
        {
            vItems += " " + item;
        }
        return vItems;
    } // getItemString()
}
