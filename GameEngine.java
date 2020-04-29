import java.util.Stack;
import java.util.Scanner;
import java.io.File;
/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe contient la majeur partie de la mechanique du jeu
 * 
 * @author  Michael Kolling and David J. Barnes + Théo LEFEVRE
 * @version 1.0 (Jan 2003) + 2019.05.08
 */
public class GameEngine
{
    private Parser parser;
    private UserInterface gui;
    private Player aPlayer;
    private static int aDeplacements;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        String vNom = javax.swing.JOptionPane.showInputDialog("Choisissez un pseudo :");
        this.aPlayer = new Player(vNom);
        this.createRooms();
        this.parser = new Parser();
        this.aDeplacements = 0;
    } // GameEngine()

    /**
     * Procedure creation interface graphique
     * @param userInterface interface creee par UserInterface
     */
    public void setGUI(UserInterface userInterface)
    {
        this.gui = userInterface;
        printWelcome();
    } // setGUI()
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        this.gui.println("--------------------------------------------------------------");
        this.gui.println("Bienvenue dans le futur " + this.aPlayer.getNom() + " !");
        this.gui.println("Retrouvez le colis perdu et apportez le sur Omicron Persei 8.");
        this.gui.println("Pensez à vous nourrir réguilièrement durant cotre aventure !");
        this.gui.println("Tapez help pour voir la liste des commandes.");
        this.gui.println("Bon jeu !");
        this.gui.println("-------------------------------------------------------------- \n");
        this.gui.println(this.aPlayer.getPosition().getLongDescription());
        this.gui.showImage(this.aPlayer.getPosition().getImageName());
    } // printWelcome()
    
    /**
     * Create all the rooms and link their exits and Items together.
     */
    private void createRooms()
    {
        Room vConf = new Room("dans la salle de conférence de Planet Express","Images/conference.jpg", false);
        Room vCuis = new Room("dans la cuisine de Planet Express","Images/cuisine.jpg", true);
        Room vCoul = new Room("dans le couloir","Images/couloir.jpg", false);
        Room vObse = new Room("dans l'observatoire","Images/observatoire.jpg", false);
        Room vLabo = new Room("dans le labo du professeur","Images/labo.jpg", false);
        Room vInfi = new Room("dans l'infirmerie","Images/infirmerie.jpg", false);
        Room vVais = new Room("dans le vaisseau de Planet Express","Images/vaisseau.jpg", false);
        Room vSout = new Room("dans la soute du vaisseau","Images/soute.jpg", false);
        Room vOmi8 = new Room("sur Omicron Persei 8","Images/omicron.jpg", false);
        Room vChat = new Room("dans le cheateau du Roi de la planète","Images/chateau.jpg", false);
        Room vAmp9 = new Room("sur Amphibios 9","Images/amphibios.jpg", false);
        Room vMara = new Room("dans un marais","Images/marais.jpg", false);
        Room vMare = new Room("dans un marécage","Images/marecage.jpg", false);
        
        Item vCookie = new Item("le magic-cookie", 5);
        Item vCigare = new Item("un cigare cubain", 0.5);
        Item vLunettes = new Item("des lunettes anti-hypnoses", 2);
        Item vColis1 = new Item("un colis avec un ruban bleu", 20);
        Item vColis2 = new Item("un colis en carton", 10);
        Item vBeamer = new Beamer("un téléporteur", 15);
        
        //Terre
        vConf.setExits("vaisseau", vVais);
        vConf.setExits("cuisine", vCuis);
        vConf.setExits("couloir", vCoul);
        vConf.getItemList().addItem("cigare", vCigare);
        
        vCuis.setExits("conference", vConf);
        vCuis.setExits("couloir", vCoul);
        vCuis.getItemList().addItem("cookie", vCookie);
        
        vCoul.setExits("conference", vConf);
        vCoul.setExits("laboratoire", vLabo);
        vCoul.setExits("infirmerie", vInfi);
        vCoul.setExits("observatoire", vObse);
        vCoul.setExits("cuisine", vCuis);
        vObse.setExits("couloir", vCoul);
        vObse.getItemList().addItem("beamer", vBeamer);
        vInfi.setExits("couloir", vCoul);
        vLabo.setExits("couloir", vCoul);
        vLabo.getItemList().addItem("lunettes", vLunettes);
        vVais.setExits("omicron", vOmi8);
        vVais.setExits("amphibios", vAmp9);
        vVais.setExits("soute", vSout);
        vVais.setExits("conference", vConf);
        vSout.setExits("vaisseau", vVais);
        vSout.getItemList().addItem("colis", vColis1);
        
        //Omicron Persei 8
        vOmi8.setExits("vaisseau", vVais);
        vOmi8.setExits("chateau", vChat);
        vChat.setExits("omicron", vOmi8);
        
        //Amphibios 9
        vAmp9.setExits("vaisseau", vVais);
        vAmp9.setExits("marais", vMara);
        vMara.setExits("amphibios", vAmp9);
        vMara.setExits("marecage", vMare);        
        vMare.setExits("marais", vMara);
        vMare.getItemList().addItem("carton", vColis2);
        
        this.aPlayer.setPosition(vConf);
    } // createRooms()

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     * @param pCommande la commande tapee
     */
    public void interpretCommand(final String pCommande) 
    {
        gui.println(pCommande);
        Command vCommande = parser.getCommand(pCommande);
        if(vCommande.isUnknown()) {
            this.gui.println("Commande inconnue... \n");
            return;
        }
        String vCommandWord = vCommande.getCommandWord();
        if(vCommandWord.equals("aller"))this.goRoom(vCommande);
        else if(vCommandWord.equals("regarder"))this.look(vCommande);
        else if(vCommandWord.equals("prendre"))this.take(vCommande);
        else if(vCommandWord.equals("lacher"))this.drop(vCommande);
        else if(vCommandWord.equals("manger"))this.eat(vCommande);
        else if(vCommandWord.equals("test"))this.test(vCommande);
        else if(vCommandWord.equals("charger"))this.charge(vCommande);
        else if(vCommandWord.equals("utiliser"))this.use(vCommande);
        else if(vCommandWord.equals("inventaire")){
            if(vCommande.hasSecondWord())this.gui.println("Commande sans paramêtre \n");
            else this.gui.println(this.aPlayer.inventaire());
        }
        else if(vCommandWord.equals("help")){
            if(vCommande.hasSecondWord())this.gui.println("Commande sans paramêtre \n");
            else this.printHelp();
        }
        else if (vCommandWord.equals("quit")) {
            if(vCommande.hasSecondWord())this.gui.println("Commande sans paramêtre \n");
            else this.endGame();
        }
        else if (vCommandWord.equals("back")) {
            if(vCommande.hasSecondWord())this.gui.println("Commande sans paramêtre \n");
            else this.back();
        }
    } // interpretCommand()

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param pDirection   Direction vers laquelle on veut aller
     */
    private void goRoom(final Command pDirection) 
    {
        if(!pDirection.hasSecondWord()) {
            this.gui.println("Aller où ? \n");
            return;
        }
        String vDirection = pDirection.getSecondWord();
        Room vNextRoom = this.aPlayer.getPosition().getExit(vDirection);
        if (vNextRoom == null)
            this.gui.println("Cette sortie n'existe pas \n");
        else {
            this.aDeplacements += 1;
            this.aPlayer.setBackRoom(this.aPlayer.getPosition());
            this.aPlayer.setPosition(vNextRoom);
            if(this.aDeplacements == 10){
                this.gui.println("Vous n'avez pas mangé durant votre aventure.");
                this.gui.println("Vous êtes mort de faim.");
                this.endGame();
            }
            else{
                this.gui.println(this.aPlayer.getPosition().getLongDescription());
            }
            if(this.aPlayer.getPosition().getImageName() != null) this.gui.showImage(this.aPlayer.getPosition().getImageName());
        }
    } // goRoom(
    
        
    /**
     * Print out help information.
     */
    private void printHelp() 
    {
        this.gui.println("\nCommandes possibles : " + parser.showCommands());
    } // printHelp()
    
    /**
     * Permet de regarder quelque chose
     * affiche location info car exo optionnel non fait
     * @param pCommand  Item que l'on veut regarder
     */
    private void look(final Command pCommand)
    {
        if(pCommand.hasSecondWord())this.gui.println("Impossible de regarder quelque chose en particulier. \n");
        else this.gui.println(this.aPlayer.getPosition().getLongDescription());
    } //look()
    
    /**
     * procédure pour manger magic cookie
     * @param pCommand Item a manger
     */
    public void eat(final Command pCommand)
    {
        String vNom = pCommand.getSecondWord();
        Item vItem = this.aPlayer.getInventory().getItem(vNom);
        if(!pCommand.hasSecondWord())
        {
            this.gui.println("Manger quoi ? \n");
            return;
        }
        else if(!this.aPlayer.getInventory().hasItem(vNom)){
            this.gui.println("Cet item n'est pas dans votre inventaire. \n");
            return;
        }
        else if(vNom.equals("cookie")){
            this.aPlayer.getInventory().removeItem(vNom);
            this.aPlayer.setPoidsMax(this.aPlayer.getPoidsMax() * 2);
            this.gui.println("Vous avez mangé un cookie magique, vous n'avez plus faim !");
            this.gui.println("La charge maximale que vous pouvez porter a été doublée !\n");
        }
        else this.gui.println("Cet Item n'est pas mangeable, ou vous n'avez plus faim. \n");
    } // eat()
    
    /**
     * Procedure retour arriere
     */
    private void back()
    {
        if(!this.aPlayer.backRoomIsEmpty()){
            if(this.aPlayer.getPosition().getTrapedDoor()){
                this.aPlayer.clearBackRoom();
                this.gui.println("Cette porte est verrouillée, impossible de retourner en arrière.");
            }
            else{
                this.aPlayer.setPosition(this.aPlayer.getBackRoom());
                this.gui.println(this.aPlayer.getPosition().getLongDescription());
                if(this.aPlayer.getPosition().getImageName() != null){
                    gui.showImage(this.aPlayer.getPosition().getImageName());
                }
            }
        }
        else{this.gui.println("Vous êtes déjà à votre point de départ");}
    } // back()
    
    /**
     * Procedure prendre item
     * @param pCommand  item a prendre dans la room
     */
    private void take(final Command pCommand){
        String vNom = pCommand.getSecondWord();
        Item vItem = this.aPlayer.getPosition().getItemList().getItem(vNom);
        if(!pCommand.hasSecondWord()){
            this.gui.println("Prendre quoi ? \n");
            return;
        }
        else if(!this.aPlayer.getPosition().getItemList().hasItem(vNom)){
            this.gui.println("Cet item n'existe pas. \n");
            return;
        }
        else if(!this.aPlayer.takePoidsOK(vItem.getPoidsItem())){
            this.gui.println("Objet trop lourd ! \n");
        }
        else{
            this.aPlayer.getInventory().addItem(vNom, vItem);
            this.aPlayer.getPosition().getItemList().removeItem(vNom);
            this.gui.println("Vous avez ramassé " + this.aPlayer.getPlayerItem(vNom));
            this.gui.println(this.aPlayer.inventaire() + "\n");
        }
    } // take()
    
    /**
     * Procedure lacher item
     * @param pCommand  item a lacher de l'inventaire
     */
    private void drop(final Command pCommand){
        String vNom = pCommand.getSecondWord();
        Item vItem = this.aPlayer.getInventory().getItem(vNom);
        if(!pCommand.hasSecondWord()){
            this.gui.println("Lacher quoi ? \n");
            return;
        }
        else if(!this.aPlayer.getInventory().hasItem(vNom)){
            this.gui.println("Cet item n'existe pas. \n");
            return;
        }
        else{
            this.aPlayer.getPosition().getItemList().addItem(vNom, vItem);
            this.gui.println("Vous avez laché : " + this.aPlayer.getPlayerItem(vNom));
            this.aPlayer.getInventory().removeItem(vNom);
            this.gui.println(this.aPlayer.inventaire() + "\n");
        }
    } // drop()
    
    /**
     * Procedure chargement beamer
     * @param pCommand Beamer de l'inventaire à charger
     */
    private void charge(final Command pCommand){
        String vNom = pCommand.getSecondWord();
        Beamer vBeamer = (Beamer)this.aPlayer.getInventory().getItem(vNom);
        if(!pCommand.hasSecondWord()){
            this.gui.println("Charger quoi ? \n");
            return;
        }
        else if(!this.aPlayer.getInventory().hasItem(vNom)){
            this.gui.println("Ce Beamer n'existe pas. \n");
            return;
        }
        else{
            vBeamer.chargeBeamer(this.aPlayer.getPosition());
            this.gui.println("Vous avez chargé ce Beamer ! \n");
        }
    } // charge()
    
    /**
     * Procedure utilisation beamer
     * @param pCommand Beamer de l'inventaire à utiliser
     */
    private void use(final Command pCommand){
        String vNom = pCommand.getSecondWord();
        Beamer vBeamer = (Beamer)this.aPlayer.getInventory().getItem(vNom);
        if(!pCommand.hasSecondWord()){
            this.gui.println("Utiliser quoi ? \n");
            return;
        }
        else if(!this.aPlayer.getInventory().hasItem(vNom)){
            this.gui.println("Vous ne possédez pas ce Beamer. \n");
            return;
        }
        else if(!vBeamer.isCharged()){
            this.gui.println("Ce Beamer n'est pas chargé. \n");
            return;
        }
        else if(this.aPlayer.getPosition() == vBeamer.getChargeRoom()){
            this.gui.println("Vous êtes déjà dans la salle chargée par le Beamer. \n");
            return;
        }
        else{
            this.aPlayer.clearBackRoom();
            this.aPlayer.setPosition(vBeamer.getChargeRoom());
            vBeamer.dechargeBeamer();
            this.gui.println("Vous avez utilisé ce Beamer ! \n");
            this.gui.println(this.aPlayer.getPosition().getLongDescription());
            if(this.aPlayer.getPosition().getImageName() != null){
                gui.showImage(this.aPlayer.getPosition().getImageName());
            }
        }
    } // use()
    
    /**
     * Procedure test
     * @param pCommand fichier de test sans .txt
     */
    private void test(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.gui.println("Tester quoi ? \n");
            return;
        }      
        String vFichier = pCommand.getSecondWord();
        Scanner vScanner = null;
        try{
            vScanner = new Scanner(new File("tests/" + vFichier + ".txt"));
        }
        catch(java.io.FileNotFoundException pE){
            System.out.println( "Erreur -> " + pE.getMessage() );
        }
        while(vScanner.hasNextLine())
        {
            String vCommande = vScanner.nextLine();
            this.interpretCommand(vCommande);
        }
        if(vScanner != null) vScanner.close();
    } // test()
    
    /**
     * Procedure fin du jeu
     */
    private void endGame()
    {
        this.gui.println("\nMerci d'avoir joué ! A bientot ! \n");
        this.gui.enable(false);
    } // endGame()
}
