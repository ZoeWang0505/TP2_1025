package FlappyGhost;


import javafx.animation.AnimationTimer;

public class Controleur {
    private Vue vue; // la vue est le point d'entree de l'application JavaFX
    private Jeu jeu; // le modele de l'application est une instance de Jeu

    public Controleur(Vue vue) {
        this.vue = vue;
    }

    private void jeuInitialise(){
        this.jeu = new Jeu(vue.getLrgFenetre(), vue.getHtrFenetre() - vue.getHtrBarreTache());
        this.jeu.initialiser();
    }

    public void start() {
        AnimationTimer timer = new AnimationTimer() { // Classe anonyme
            private long lastTime = 0;

            @Override
            public void start() {
                lastTime = System.nanoTime();
                jeuInitialise();
                super.start(); // Commence les appels de handle(...)
            }

            @Override
            public void handle(long now) {
                double deltaTemps = (now - lastTime) * 1e-9;
                jeu.jouer(deltaTemps);
                //if collision is true, die
                if(jeu.getCollision()){
                //    jeuInitialise();
                }
                vue.miseAJour(jeu);
                lastTime = now;
            }
        };
        timer.start(); // demarrer le timer
        
    }

    public void evenement(String evenement) {
        switch (evenement){
            case "sauter":
                this.jeu.evenement("sauter");
                break;
            default:
                break;
        }

    }
}
