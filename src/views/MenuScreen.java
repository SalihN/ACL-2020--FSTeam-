package views;

import engine.Cmd;
import model.PacmanGame;
import model.PacmanPainter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Goetz Alexandre
 * @version 1.0.0
 */
public class MenuScreen implements GameScreen{

    private final BufferedImage background;

    private final int sizeOfPolice = 40;
    enum Option{
        PLAY,OPTIONS,QUIT;
        private static Option[] vals = values();
        public Option getNext() {
            return Option.values()[(this.ordinal()+1) % vals.length];
        }
        public Option getPrevious(){
            return Option.values()[this.ordinal()-1 >= 0  ? this.ordinal()-1 : vals.length - 1];
        }
    }
    Option currentOption;
    Font font;
    PacmanGame game;

    /**
     * Menu de début de jeu
     * @throws IOException Lecture de fichiers impossible
     */
    public MenuScreen(PacmanGame game) throws IOException {
        background = ImageIO.read(new File("resources/images/MenuPrincipale/background.png"));
        font = new Font("TimesRoman", Font.BOLD,sizeOfPolice);
        currentOption = Option.PLAY;
        this.game = game;
        // Comme c'est le menu principale on s'assure de revenir au premier niveau
        PacmanGame.cpt = 0;
    }

    /**
     * Affichage de l'écran
     * @param im Ecran sur lequel le menu est dessiné
     */
    @Override
    public void display(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(
                background,
                0,0,
                PacmanPainter.tileWidth,PacmanPainter.tileHeight,null
        );
        crayon.setFont(font);
        // anti aliasing sur le texte
        crayon.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        for(Option option : Option.values()){
            checkColorOption(option,crayon);
            crayon.drawString(option.toString(),PacmanPainter.tileWidth * 0.1f,PacmanPainter.tileHeight * 0.4f + sizeOfPolice * 2 * option.ordinal() );
        }
    }

    /**
     * Met à jour l'écran en fonction des entrée données
     * @param cmd commande reçu du clavier
     */
    @Override
    public void update(Cmd cmd) throws IOException {
        if (cmd == Cmd.MENU_UP) {
            currentOption = currentOption.getPrevious();
        }
        if (cmd == Cmd.MENU_DOWN) {
            currentOption = currentOption.getNext();
        }
        if (cmd == Cmd.ENTRER) {
            if(currentOption == Option.PLAY){
                game.setCurrentState(PacmanGame.GameState.Maze);
            }
            if(currentOption == Option.QUIT){
                game.setCurrentState(PacmanGame.GameState.Quit);
            }
        }
    }


    /**
     * Choisit la couleur dans laquelle l'option doit être affichée
     * @param option l'option à vérifier si elle est en court
     * @param crayon le crayon pour dessiner sur l'écran
     */
    private void checkColorOption(Option option,Graphics2D crayon){
        if(option == currentOption)
            crayon.setColor(Color.YELLOW);
        else
            crayon.setColor(Color.RED);
    }
}
