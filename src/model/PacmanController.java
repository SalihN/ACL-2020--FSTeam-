package model;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import engine.Cmd;
import engine.GameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	/**
	 *
	 *
	 * @param cmd nouvelle Commande
	 */
	public void setCommand(Cmd cmd) {
		this.commandeEnCours = cmd;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Q:
				this.commandeEnCours = Cmd.LEFT;
				break;
			case KeyEvent.VK_D:
				this.commandeEnCours = Cmd.RIGHT;
				break;
			case KeyEvent.VK_S:
				this.commandeEnCours = Cmd.DOWN;
				break;
			case KeyEvent.VK_Z:
				this.commandeEnCours = Cmd.UP;
				break;
			case KeyEvent.VK_UP:
				this.commandeEnCours = Cmd.MENU_UP;
				break;
			case KeyEvent.VK_DOWN:
				this.commandeEnCours = Cmd.MENU_DOWN;
				break;
			case KeyEvent.VK_ENTER:
				this.commandeEnCours = Cmd.ENTRER;
				break;
		}
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}


}
