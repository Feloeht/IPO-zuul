/**
 * Cete classe fait parti du jeu "Le colis perdu"
 * Cette classe permet la démarrage du jeu
 * 
 * @author  Théo LEFEVRE
 * @version 2018.05.08
 */
public class Game
{
    private UserInterface gui;
    private GameEngine engine;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        engine = new GameEngine();
        gui = new UserInterface(engine);
        engine.setGUI(gui);
    }// Game()
}