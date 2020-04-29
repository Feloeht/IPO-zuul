/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la création des Items du jeu
 * 
 * @author  Théo LEFEVRE
 * @version 2019.05.08
 */
public class Item
{
    private String aDescription;
    private double aPoids;
    
    /**
     * Natural constructor
     * @param pDescription  Description de l'item à créer
     * @param pPoids        Poids de l'item à créer
     */
    public Item(final String pDescription, final double pPoids)
    {
        this.aDescription = pDescription;
        this.aPoids = pPoids;
    } // Item()
    
    /**
     * Methode affichage description item
     * @return Description de l'item
     */
    public String getDescriptionItem()
    {
        return this.aDescription;
    } // getDescriptionItem()
    
    /**
     * Methode affichage poids item
     * @return Poids de l'item
     */
    public double getPoidsItem()
    {
        return this.aPoids;
    } // getPoidsItem()

} // Item
