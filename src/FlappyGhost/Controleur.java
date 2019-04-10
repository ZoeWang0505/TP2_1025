/**
 * Auteurs:     David Raby-Pepin, p0918119
 *              Zoe Wang, p20111352
 *
 * Date:        22/04/2019
 *
 * Description: Ce programme est un jeu en interface graphique avec la librairie JavaFX.
 *              Le joueur incarne un fantôme qui se promène dans un niveau rempli d’obstacles. Le seul objectif
 *              est d’avancer le plus longtemps possible dans le niveau sans toucher d’obstacle.
 */

package FlappyGhost;
import javafx.animation.AnimationTimer;

public class Controleur {
    private Vue vue; // la vue est le point d'entree de l'application JavaFX
    private Jeu jeu; // le modele de l'application est une instance de Jeu

    /**
     * Constructeur qui prends une vue en pararmetre
     * @param vue vue du jeu
     */
    public Controleur(Vue vue) {
        this.vue = vue;
    }

    /**
     * Initialise le jeu
     */
    private void jeuInitialise(){
        this.jeu = new Jeu(vue.getLrgFenetre(), vue.getHtrFenetre() - vue.getHtrBarreTache());
        this.jeu.initialiser();
    }

    /**
     * Fonction qui execute le jeu
     */
    public void start() {
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void start() {
                lastTime = System.nanoTime();
                jeuInitialise();
                super.start();
            }

            @Override
            public void handle(long now) {
                double deltaTemps = (now - lastTime) * 1e-9;
                jeu.jouer(deltaTemps); // fournit l'avancement en temps au jeu

                // s'il y a une collision, le jeu recommence
                if(jeu.getCollision()){
                    if(!vue.getModeDebug()) { // sauf si le mode debug est enclenche
                        jeuInitialise();
                    } else {
                        jeu.setCollision(false);
                    }
                }
                vue.miseAJour(jeu); // met a jour la vue en fonction du modele
                lastTime = now;
            }
        };
        timer.start(); // demarrer le timer
        
    }

    /**
     * Fonction qui recoit des evenements de la vue et les transfert au modele (jeu)
     * @param action action a faire dans le modele
     */
    public void evenement(Jeu.actions action ) {
                this.jeu.evenement(action);
    }
}
