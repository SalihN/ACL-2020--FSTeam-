package views;

import engine.Cmd;
import model.PacmanGame;
import model.PacmanPainter;
import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Goetz Alexandre, Gady Emanuel
 * @version 1.0.1
 */
public class EndScreen implements GameScreen{

    private final BufferedImage background;
    private String stateDisplayed;
    private final int sizeOfPolice = 40;
    enum Option{
        RETRY,MENU,QUIT;
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
    public EndScreen(PacmanGame game, String state) throws IOException {
        this.game = game;
        if(state.toUpperCase().equals("LOST") || state.toUpperCase().equals("VICTORY")  ){
            stateDisplayed = state.toLowerCase();
        }
        else{
            stateDisplayed = "lost";
        }
        background = ImageIO.read(new File("resources/images/"+ stateDisplayed +".png"));
        font = new Font("TimesRoman", Font.BOLD,sizeOfPolice);
        currentOption = Option.RETRY;
    }

    /**
     * affiche le menu de fin en fonction de si la partie est gagné où perdue
     * @param im image à afficher sur l'écran
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
        crayon.setColor(Color.RED);
        crayon.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        String message = "You ";
        if(stateDisplayed.equals("lost"))
            message += stateDisplayed;
        if(stateDisplayed.equals("victory"))
            message += "won";
        crayon.drawString(message,(PacmanPainter.tileWidth / 2) - (message.length() * sizeOfPolice / 4) , PacmanPainter.tileHeight * 0.3f  );
        crayon.drawString(Integer.toString(Hero.score),(PacmanPainter.tileWidth /2) - sizeOfPolice, PacmanPainter.tileHeight/2.5f);


        for(Option option : Option.values()){
            checkColorOption(option,crayon);
            crayon.drawString(option.toString(),PacmanPainter.tileWidth * 0.1f,PacmanPainter.tileHeight * 0.4f + sizeOfPolice * 2 * option.ordinal() );
        }
    }

    /**
     * Choisit la couleur dans laquelle l'option doit être affichée
     * @param option l'option à vérifier si elle est en court
     * @param crayon le crayon pour dessiner sur l'écran
     */
    private void checkColorOption(Option option, Graphics2D crayon){
        if(option == currentOption)
            crayon.setColor(Color.YELLOW);
        else
            crayon.setColor(Color.RED);
    }

    /**
     * Gère les différentes entrées faite par le clavier
     * @param cmd Dernière commande du clavier
     * @throws IOException
     */
    @Override
    public void update(Cmd cmd) throws IOException {
        if (cmd == Cmd.MENU_UP) {
            currentOption = currentOption.getPrevious();
        }
        if (cmd == Cmd.MENU_DOWN) {
            currentOption = currentOption.getNext();
        }
        if(cmd == Cmd.ENTRER){
            if(currentOption == Option.QUIT)
                game.setCurrentState(PacmanGame.GameState.Quit);
            if(currentOption == Option.RETRY) {
                game.setCurrentState(PacmanGame.GameState.Maze);
            }
            if(currentOption == Option.MENU) {
                game.setCurrentState(PacmanGame.GameState.MainMenu);
            }
        }
    }

}
