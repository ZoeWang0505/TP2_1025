package FlappyGhost;

public class Controleur {

    // La vue est le point d'entrée de l'Application JavaFX
    private Vue vue;

    // Le modèle de l'application est une instance de Jeu
    private Jeu jeu;

    public Controleur(Vue vue) {
        this.vue = vue;
        this.jeu = new Jeu();

        //vue.miseAJour();
        //jeu.jouer();
    }


}
