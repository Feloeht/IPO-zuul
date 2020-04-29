/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la création des objets de teleportation du jeu
 * 
 * @author  Théo LEFEVRE
 * @version 2019.05.08
 */
public class Beamer extends Item
{
    private boolean aCharge;
    private Room aChargeRoom;
    
    /**
     * Constructeur d'objets de classe Beamer
     * @param pDescription Decription du Beamer
     * @param pPoids Poids du Beamer dans inventaire
     */
    public Beamer(final String pDescription, final double pPoids)
    {
        super(pDescription, pPoids);
        this.aCharge = false;
    } // Beamer()
    
    /**
     * Accesseur Room chargee par Beamer
     * @return Salle chargee sous forme de Room
     */
    public Room getChargeRoom()
    {
        return this.aChargeRoom;
    } // getChargeRoom()
    
    /**
     * Accesseur Etat chargement Beamer
     * @return Boolean, charge ou non
     */
    public boolean isCharged()
    {
        return this.aCharge;
    } // isCharged()
    
    /**
     * Procedure chargement Beamer
     * @param pChargeRoom Room a charger dans le Beamer
     */
    public void chargeBeamer(final Room pChargeRoom)
    {
        this.aChargeRoom = pChargeRoom;
        this.aCharge = true;
    } // chargeBeamer()
    
    /**
     * Procedure dechargement Beamer
     */
    public void dechargeBeamer()
    {
        this.aCharge = false;
    } // dechargeBeamer()
}
