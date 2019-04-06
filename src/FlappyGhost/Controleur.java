package FlappyGhost;

public class Controleur {
    private Vue vue; // la vue est le point d'entree de l'application JavaFX
    private Jeu jeu; // le modele de l'application est une instance de Jeu

    public Controleur(Vue vue) {
        this.vue = vue;
        this.jeu = new Jeu(vue.getLrgFenetre(), vue.getHtrFenetre(), vue.getHtrBarreTache());

        // faire rouler le jeu dans un second thread
        Thread t = new Thread(() -> {
        jeu.jouer();
        });
        t.start();

        Fantome fantome = jeu.getFantome();  //TODO: figure out why coordX and coordY do not appear
        //System.out.println(fantome.getCoordX());
        //System.out.println(fantome.getCoordY());

        //int yFantome = fantome.getCoordY();

        //vue.miseAJour(xFantome, yFantome);
    }


}
