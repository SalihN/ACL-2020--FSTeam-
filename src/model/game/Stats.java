package model.game;

/**
 * @author Emanuel Gady, Alexis Richer
 * @version 1.1.1
 *
 * Applique des statistiques a chaque entité present dans le jeu
 */

public class Stats {
    private int hp;
    private int hpMax;
    private int speed;
    private int baseSpeed;

    /**
     *
     * @param hpMax nombre de point de vie maximal que le personnage a
     * @param speed vitesse du personnage
     */
    public Stats(int hpMax, int speed){
        hp = hpMax;
        this.hpMax = hpMax;
        this.speed = speed;
        this.baseSpeed = speed;
    }

    /**
     * Fonction qui permet de redonner de la vie
     * @param heal nombre de point de vie rendu au personnage
     */
    public void heal(int heal){
        if(hp + heal >= hpMax)
            hp = hpMax;
        else
            hp = Math.max(1,hp+heal);
    }

    /**
     * Fonction qui permet de retirer de la vie
     * @param damage nombre de point de vie perdu par le personnage
     */
    public void hit (int damage){
        if(damage < 0){
            heal(damage);
        }
        else{
         hp = Math.max(0,hp-damage);
        }
    }

    /**
     *
     * @return point de vie actuels du personnage
     */
    public int getHp() {
        return hp;
    }

    /**
     *
     * @return Nombre de point de vie que le personnage peut avoir au maximum
     */
    public int getHpMax(){
        return hpMax;
    }

    /**
     *
     * @return vitesse du personnage
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed nouvelle vitesse souhaité
     */
    public void setSpeed(int speed) {
        this.speed = Math.max(0,speed);
    }

    /**
     *
     * @return la vitesse d'origine du monstre
     */
    public int getBaseSpeed() {
        return baseSpeed;
    }
}
