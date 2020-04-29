/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la gestion des commandes du jeu
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + Théo LEFEVRE
 * @version 2008.03.30 + 2013.09.15 + 2019.05.08
 */
public class CommandWords
{
    // tableau constant qui contient tous les mots de commande valides
    private static final String[] sValidCommands = {
        "aller", "quit", "help", "regarder", "manger", "back", "test", "inventaire", "prendre", "lacher", "charger", "utiliser"
    };

    /**
     * Constructeur par defaut
     */
    public CommandWords()
    {
        // rien a faire pour le moment...
    } // CommandWords()

    /**
     * Verifie si une String donnee fait partie des commandes valides. 
     * @param pString la String a tester
     * @return true si pString est une comande valide, false sinon
     */
    public boolean isCommand( final String pString )
    {
        // pour chacune des commandes valides (du tableau constant)
        for ( int i=0; i<sValidCommands.length; i++ ) {
            // si elle est egale a pString
            if ( sValidCommands[i].equals( pString ) )
                return true;
        } // for
        // si nous arrivons la, c'est que la commande
        // n'a pas ete trouvee dans le tableau
        return false;
    } // isCommand()
    
    /**
     * Méthode affichage liste des commandes valides. 
     * @return liste des commandes inscrites dans la liste des commandes valides
     */
    public String getCommandList()
    {
        String vCommandes = "";
        for(String command : sValidCommands)
        {
            vCommandes += command + " ";
        }
        return vCommandes;
    }
} // CommandWords
