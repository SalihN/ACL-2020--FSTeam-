package model.game;

public class Stats {
    private int hp;

    public Stats(){
        hp = 5;
    }

    /**
     * Fonction qui permet de redonner de la vie
     * @param heal
     */
    public void heal(int heal){
        //todo gerer les histoires de max et min
        hp+=heal;
    }

    /**
     * Fonction qui permet de retirer de la vie
     * @param damage
     */
    public void hit (int damage){
        //todo gerer les histoires de max et min
        hp-=damage;
    }

    public int getHP() {
        return hp;
    }
}
