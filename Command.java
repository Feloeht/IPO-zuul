/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la gestion et l'interpretation des commandes du jeu
 * 
 * @author  Théo LEFEVRE
 * @version 2019.05.08
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Natural constructor
     * @param pCommandWord  le mot de commande
     * @param pSecondWord   le second mot de commande
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    } // Command()
    
    /**
     * Methode getCommandWord
     * @return mot de commande
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    } // getCommandWord()
    
    /**
     * Methode getSecondWord
     * @return second mot de commande
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    } // getSecondWord()
        
    /**
     * Methode isUnknown
     * @return True si CommandWord vaut null ou inconnue
     */
    public boolean isUnknown()
    {
        return (this.aCommandWord == null);
    } // isUnknown()
    
    /**
     * Methode hasSecondWord
     * @return True si secondWord existe ou false sinon
     */
    public boolean hasSecondWord()
    {
        return (this.aSecondWord != null);
    } // hasSecondWord()

} // Command
