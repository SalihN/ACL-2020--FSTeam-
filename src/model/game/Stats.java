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

    public Stats(int hpMax, int speed){
        hp = hpMax;
        this.hpMax = hpMax;
        this.speed = speed;
    }

    /**
     * Fonction qui permet de redonner de la vie
     * @param heal
     */
    public void heal(int heal){
        while (hp < hpMax && heal !=0) {
            hp += 1;
            heal--;
        }
    }

    /**
     * Fonction qui permet de retirer de la vie
     * @param damage
     */
    //todo Voir comment faire cette fonction proprement
    //todo Lorque qu'il y a UNE collision, cette collision est détéctée plusieurs fois (Car les 2 images restent l'une sur l'autre pendant plusieur draw) et donc le heros perd tous ses HP d'un coup
    public void hit (int damage){
        if(hp - damage >= 0) {
            hp -= damage;
        }
    }

    /////////////////
    //Getter&Setter//
    /////////////////
    public int getHp() {
        return hp;
    }

    public int getHpMax(){
        return hpMax;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
