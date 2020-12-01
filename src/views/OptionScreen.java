package views;

import engine.Cmd;
import engine.GameController;
import model.PacmanGame;
import model.PacmanPainter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OptionScreen implements GameScreen {

    private final BufferedImage background;

    private final int sizeOfPolice = 40;
    enum Option{
        ZQSD, WASD, RETOUR;
        private static OptionScreen.Option[] vals = values();
        public OptionScreen.Option getNext() {
            return OptionScreen.Option.values()[(this.ordinal()+1) % vals.length];
        }
        public OptionScreen.Option getPrevious(){
            return OptionScreen.Option.values()[this.ordinal()-1 >= 0  ? this.ordinal()-1 : vals.length - 1];
        }
    }
    OptionScreen.Option currentOption;
    OptionScreen.Option chosenMode;
    Font font;
    PacmanGame game;

    /**
     * Menu de début de jeu
     * @throws IOException Lecture de fichiers impossible
     */
    public OptionScreen(PacmanGame game) throws IOException {
        background = ImageIO.read(new File("resources/images/MenuPrincipale/background.png"));
        font = new Font("TimesRoman", Font.BOLD,sizeOfPolice);
        currentOption = Option.ZQSD;
        chosenMode = Option.ZQSD;
        this.game = game;
        // Comme c'est le menu principale on s'assure de revenir au premier niveau
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

        for(OptionScreen.Option option : OptionScreen.Option.values()){
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
            if(currentOption == Option.ZQSD){
                game.azertyMode();
                chosenMode = Option.ZQSD;
            }
            if(currentOption == Option.WASD){
                game.qwertyMode();
                chosenMode = Option.WASD;
            }
            if(currentOption == Option.RETOUR){
                game.setCurrentState(PacmanGame.GameState.MainMenu);
            }
        }
    }


    /**
     * Choisit la couleur dans laquelle l'option doit être affichée
     * @param option l'option à vérifier si elle est en court
     * @param crayon le crayon pour dessiner sur l'écran
     */
    private void checkColorOption(OptionScreen.Option option, Graphics2D crayon){
        if(option == currentOption)
            crayon.setColor(Color.YELLOW);
        else if(option == chosenMode) {
            crayon.setColor(Color.BLUE);
        }
        else
            crayon.setColor(Color.RED);
    }

}
