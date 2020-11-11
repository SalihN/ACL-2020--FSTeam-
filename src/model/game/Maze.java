package model.game;

import engine.Cmd;
import model.PacmanGame;
import model.PacmanPainter;
import model.game.floor.*;
import model.game.monster.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Alexis Richer, Goetz Alexandre, Gady Emanuel
 * @version 3.3
 *
 * Labyrinthe du jeu
 */
public class Maze {
    private Hero hero;
    private Floor[][] listFloor;
    private Collection<Monster> listMonsters;
    private int labyHeight, labyWidth;
    //TILE SIZE
    public  int tileWidth = 32;
    public  int tileHeight = 32;

    private BufferedImage life;

    private Timer timer;
    private TimerTask decount;
    private int time;
    private int sizeOfPolice = 24;
    Font font = new Font("TimesRoman", Font.BOLD, sizeOfPolice);

    /**
     * Constructeur labyrinthe
     */
    public Maze() throws IOException {
        hero = new Hero();

        life = ImageIO.read(new File("resources/images/lifebar.png"));
        listMonsters = new ArrayList<>();

        labyHeight = 0;
        labyWidth=0;

        time = 60;
        timer = new Timer();
        TimerTask decount = new TimerTask() {
            @Override
            public void run() {
                countDown();
            }
        };
        timer.schedule(decount, 100, 1000);


    }

    /**
     * Permet de creer un labyrinthe à partir d'un fichier texte
     * Les deux première lignes du fichier sont la largeur et la longueur du labyrinthe
     * Les lignes suivantes sont la compositions de celui ci avec les symbole correspondant
     * @throws IOException if the file cannot be read
     */
    public void generate() throws IOException {
        BufferedReader buff = null;
        String level = "resources/mazes/maze" + PacmanGame.cpt + ".txt";
        String line;
        try {
            buff = new BufferedReader(new FileReader(level));
        } catch (FileNotFoundException err) {
            System.exit(0);
        }
        // lecture du nombre de lignes et de colonnes
        line = buff.readLine();
        this.labyHeight = Integer.parseInt(line) + 2;
        tileHeight = (int) Math.ceil((double) PacmanPainter.tileHeight / labyHeight);

        line = buff.readLine();
        this.labyWidth = Integer.parseInt(line) + 2;
        tileWidth = (int) Math.ceil((double) PacmanPainter.tileWidth / labyWidth);

        hero.setWidth(tileWidth);
        hero.setHeight(tileHeight);


        listFloor = new Floor[labyHeight][labyWidth];
        // lecture de la structure du labyrinthe
        //Construction des murs extérieure du laby

        for (int i = 0; i < labyWidth; i++) {
            listFloor[i][0] = new Wall(
                    new Point(i * tileWidth + tileWidth/2, tileHeight/2),
                    tileWidth, tileHeight
            );
        }
        for (int i = 0; i < labyWidth; i++) {
            listFloor[i][labyHeight - 1] = new Wall(
                    new Point(i * tileWidth + tileWidth/2, (labyHeight - 1) * tileHeight + tileHeight/2),
                    tileWidth, tileHeight
            );
        }
        for (int i = 0; i < labyHeight; i++) {
            listFloor[0][i] = new Wall(
                    new Point(tileWidth/2, i * tileHeight + tileHeight/2),
                    tileWidth, tileHeight
            );
        }
        for (int i = 0; i < labyHeight; i++) {
            listFloor[labyWidth - 1][i] = new Wall(
                    new Point((labyWidth - 1) * tileWidth + tileWidth/2 , i * tileHeight + tileHeight/2),
                    tileWidth, tileHeight
            );
        }
        // we wont read lines that goes beyond the given height
        for (int i = 1; i < labyHeight - 1; i++) {
            // skip empty lines

            line = buff.readLine();
            while(line.isBlank()){
                line = buff.readLine();
            }
            for (int j = 1; j <= line.length() ; j++) {
                // prevent from going beyond the given width
                if (j < labyWidth - 1) {
                    //décalage des tuiles
                    int x= j * tileWidth + tileWidth/2;
                    int y = i * tileHeight + tileHeight/2;
                    //

                    int spriteRatioW = (int)(tileWidth * 0.70);
                    int spriteRatioH = (int)(tileHeight* 0.70);

                    switch (line.charAt(j-1)) {
                        //Wall
                        case 'w':
                            listFloor[i][j] = new Wall(new Point(x, y), tileWidth, tileHeight);
                            break;
                        //Normal floor
                        case 'n':
                            listFloor[i][j] = new NormalFloor(new Point(x, y), tileWidth, tileHeight);
                            break;
                        // Treasure floor
                        case 't':
                            listFloor[i][j] = new TreasureFloor(new Point(x, y), tileWidth, tileHeight);
                            break;
                        // Heal floor
                        case 'h':
                            listFloor[i][j] = new HealthFloor(new Point(x, y), tileWidth, tileHeight);
                            break;
                        // Freeze floor
                        case 'f':
                            listFloor[i][j] = new FreezeFloor(new Point(x, y), tileWidth, tileHeight);
                            break;
                        // Slow floor
                        case 's':
                            listFloor[i][j] = new SlowFloor(new Point(x, y), tileWidth, tileHeight);
                            break;
                        //Normal Monster
                        case 'm':
                            listFloor[i][j] = new NormalFloor(new Point(x,y), tileWidth, tileHeight);
                            listMonsters.add(new NormalMonster(new Point(x,y), spriteRatioW, spriteRatioH));
                            break;
                        //Hero
                        case 'p':
                            listFloor[i][j] = new NormalFloor(new Point(x,y), tileWidth, tileHeight);
                            //,
                            hero.setHeight(spriteRatioH);
                            hero.setWidth(spriteRatioW);
                            hero.setPosition(new Point(x,y));
                            hero.setHeroStartingPos(new Point(x,y));
                            break;
                        //Strong Monster
                        case 'a':
                            listFloor[i][j] = new NormalFloor(new Point(x,y), tileWidth, tileHeight);
                            listMonsters.add(new StrongMonster(new Point(x,y), spriteRatioW, spriteRatioH));
                            break;
                        //Guardian Monster
                        case 'g':
                            listFloor[i][j] = new NormalFloor(new Point(x,y), tileWidth, tileHeight);
                            listMonsters.add(new GuardianMonster(new Point(x,y),spriteRatioW, spriteRatioH));
                            break;
                        //Kidnapping Monster
                        case 'k':
                            listFloor[i][j] = new NormalFloor(new Point(x,y), tileWidth, tileHeight);
                            listMonsters.add(new KidnapMonster(new Point(x,y), spriteRatioW, spriteRatioH));
                            break;
                        default:
                            listFloor[i][j] = new NormalFloor(new Point(x,y), tileWidth, tileHeight);
                    }
                    // check if the line is full according to the width given in the fill
                    // if not the line with normal floor
                    if (line.length() < labyWidth - 2) {
                        for (int k = line.length(); k < labyWidth - 1; k++) {
                            listFloor[i][k] = new NormalFloor(
                                    new Point(k * tileWidth + tileWidth/2, i * tileHeight + tileHeight/2),
                                    tileWidth, tileHeight
                            );
                        }
                    }
                }
            }
        }
    }


    private void countDown(){
        time--;
    }

    /**
     * Permet de dessiner le labyrinthe ainsi que les monstres present dans ce labyrinthe
     * @param im frame buffer
     */
    public void draw(BufferedImage im) {
        // affichage des sols
        for(int i = 0; i< labyHeight;i++){
            for(int j = 0; j< labyWidth;j++){
                listFloor[i][j].draw(im);
            }
        }

        //affichage des monstres
        for(Monster monster : listMonsters){
            monster.draw(im);
        }

        //affichage du compte à rebour
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.red);
        crayon.setFont(font);
        crayon.drawString(Integer.toString(time), getWidth()/2 - sizeOfPolice/2 , sizeOfPolice);

        //Affiche la barre de vie juste en dessous du heros
        float ratioVieVieMax = (float) hero.getStats().getHp() / (float) hero.getStats().getHpMax();
        crayon.drawImage(
                life,
                hero.getPosition().x - hero.getWidth()/2 ,hero.getPosition().y + hero.getHeight()/2 + 2,
                (int)(hero.getWidth() * ratioVieVieMax),hero.getHeight()/4,
                null
        );

        // Affichage du héro
        hero.draw(im);

        // Grande croix qui se coupe au centre de l'écran
        //crayon.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
        //crayon.drawLine(getWidth()/2,0,getWidth()/2,getHeight());
    }

    /**
     * Evolution des événement du labyrinthe
     * Déplacement du héro, activation des sols, collision, actions des monstres
     * @param commande commande reçu par le clavier pour déplacer le héro
     * @throws IOException /
     */
    public void update(Cmd commande) throws IOException {
        //Déplacement du héro
        hero.move(commande,this);

        // Vérifie si un sol a été activé
        if(getFloor(hero.getPosition().x, hero.getPosition().y).isActivateFloor()){
            ActivateFloor activateFloor = (ActivateFloor) getFloor(hero.getPosition().x, hero.getPosition().y);
            activateFloor.activate(hero, this);
        }

        //Déplacement  et collision des monstres
        for(Monster monster : listMonsters) {
            if (monster.isCanMove()) {
                monster.move(this);
            }
            if(hero.checkCollision(monster)){
                monster.action(hero);
            }
        }

        // Vérification de l'état du héro
        if(hero.isDead()){
            //todo Retourner un menu du jeu
            hero.getStats().setSpeed(0); //Actuellement, une fois le heros mort il ne peut plus bouger
        }
    }

    /**
     * Permetde savoir si une case est un mur ou non
     * @param x x position of the floor tile
     * @param y y position of the floor tile
     * @return true if it's a wall, else return a false
     */
    public boolean isAWall(int x, int y){
        return !getFloor(x, y).isWall();
    }

    /**
     * Permet d'empêcher les monstres de bouger un certain temps
     */
    public void freezeMonsters(int time) {
        for (Monster monster : listMonsters) {
            monster.freeze();
        }
        TimerTask defreeze = new TimerTask() {
            @Override
            public void run() {
                defreeze();
            }
        };
        timer.schedule(defreeze, time * 1000);
    }

    /**
     * Permet aux monstres de rebouger
     */
    private void defreeze() {
        for(Monster monster : listMonsters){
            monster.setCanMove(true);
        }
    }

    /**
     * Permet de ralentir les monstres un certain temps
     */
    public void slowMonsters(int time, int slow) {
        for(Monster monster : listMonsters){
            monster.getStats().setSpeed(monster.getStats().getSpeed() - slow);
        }
        TimerTask deslow = new TimerTask() {
            @Override
            public void run() {
                deslow(slow);
            }
        };
        timer.schedule(deslow, time*1000);
    }

    /**
     * Permet aux monstres de rebouger normalement
     */
    private void deslow(int slow) {
        for(Monster monster : listMonsters){
            monster.getStats().setSpeed(monster.getStats().getSpeed() + slow);
        }
    }

    /**
     * Charge le niveau suivant
     */
    public void nextLevel() throws IOException {
        reset();
        PacmanGame.cpt++;
        generate();
    }

    /**
     * Vie le contenu du labyrinthe
     */
    private void reset(){
        listMonsters.clear();
        time = 60;
        //arrête le timer et le vide de ses tâches
        timer.cancel();
        timer.purge();
        // créer un nouveau thread de timer
        timer = new Timer();
        // remise en route du décompte
        TimerTask decount = new TimerTask() {
            @Override
            public void run() {
                countDown();
            }
        };
        timer.schedule(decount, 100, 1000);
    }

    /**
     * renvoit le sol se trouvant aux coordonnées données
     * @param x abscisses
     * @param y ordonnées
     */
    public Floor getFloor(int x, int y){
        return listFloor[(y/tileHeight)][(x/tileWidth)];
    }

    /**
     *
     * @return  la largeur du labyrinthe en pixel
     */
    public int getWidth(){
        return labyWidth * tileWidth;
    }

    /**
     *
     * @return la hauteur du labyrinthe en pixel
     */
    public int getHeight(){
        return labyHeight * tileHeight;
    }

    /**
     *
     * @return la valeur du temps restant pour finir le labyrinthe
     */
    public int getTime() {
        return time;
    }
    /**
     *
     * @return le Héro
     */
    public Hero getHero() {
        return hero;
    }
}
