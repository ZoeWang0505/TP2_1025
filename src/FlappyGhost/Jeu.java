package FlappyGhost;

import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {
    public enum actions {SAUTER, PAUSE};            // les actions/evenements possibles dans le jeu
    private ArrayList<Obstacle> listeObstacles;     // la liste des obstacles dans la fenetre
    private Fantome fantome;                        // le fantome dans le jeu
    private Background bg;                          // l'arriere-plan dans le jeu
    private int nbreObstaclesPasses = 0;            // le nombre total d'obstacles passes par le fantome
    private boolean augmenterVitesseX = false;      // la vitesse en x du jeu
    private boolean augmenterGravite = false;       // la gravite dans le jeu
    private int score = 0;                          // le score du joueur
    private boolean collision = false;              // indiqie si le fantome a eu une collision avec un obstacle
    private int lrgCanva;                           // la largeur du canva d'affichage
    private int htrCanva;                           // la hauteur du canva d'affichage
    private double ajoutObstaclesTemps = 0;         // temps depuis le dernier ajout d'un obstacle
    private Boolean pause = false;                  // indique si le jeu est en pause

    /**
     * Getter pour la liste d'obstacles actuels dans la fenetre
     * @return liste d'obstacles actuels dans la fenetre
     */
    public ArrayList<Obstacle> getListeObstacles(){
        return listeObstacles;
    }

    /**
     * Getter pour le fantome dans le jeu
     * @return le fantome dans le jeu
     */
    public Fantome getFantome() {
        return fantome;
    }

    /**
     * Getter pour l'arriere-plan du jeu
     * @return l'arriere-plan du jeu
     */
    public Background getBackgroud() {
        return bg;
    }

    public int getScore() {
        return score;
    }

    public boolean getCollision(){
        return this.collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Jeu(int lrgFenetre, int htrFenetre) {
        this.lrgCanva = lrgFenetre;
        this.htrCanva = htrFenetre;
        this.initialiser();
    }

    public void initialiser(){
        this.fantome = new Fantome(this.lrgCanva/2,htrCanva/2);
        this.listeObstacles = new ArrayList<>();
        this.bg = new Background(0,0);
    }

    public void jouer(double deltaTemps){
         this.collision();
         if(!this.pause) {
             this.miseAJourEnvironnement(deltaTemps);
         }
    }

    public void collision(){
        Iterator<Obstacle> i = this.listeObstacles.iterator();
        while (i.hasNext()) {
            Obstacle obs = i.next();
            double distX = Math.abs(this.fantome.getCoordX() - obs.getCoordX());
            double distY = Math.abs(this.fantome.getCoordY() - obs.getCoordY());
            double distCentres = Math.sqrt((distX * distX) + (distY * distY));
            double sommeRayons = this.fantome.getRayon() + obs.getRayon();

            if (distCentres < sommeRayons ){
                this.collision = true;
                obs.setCollision(true);
            }
        }
    }

    public void miseAJourEnvironnement(double deltaTemps){
        // la vitesse du fantome augmente de 15px/s a chaque deux obstacles depasses
        if (this.augmenterVitesseX) {
            fantome.setVitesseX(fantome.getVitesseX() + 15 * ((nbreObstaclesPasses + 1) % 2));
            this.augmenterVitesseX = false;
        }

        // la gravite augmente de 15px/s a chaque deux obstacles depasses
        if (this.augmenterGravite) {
            fantome.setGravite(fantome.getGravite() + 15 * ((nbreObstaclesPasses + 1) % 2));
            this.augmenterGravite = false;
        }

        // mettre a jour la vitesse en y
        fantome.bouger(this.lrgCanva, this.htrCanva, fantome.getVitesseX(), deltaTemps);
        bg.bouger(this.lrgCanva, this.htrCanva, fantome.getVitesseX(), deltaTemps);

        Iterator it = listeObstacles.iterator();
        while (it.hasNext()){
            Obstacle obstacle = (Obstacle)it.next();
            obstacle.bouger(this.lrgCanva, this.htrCanva, fantome.getVitesseX(), deltaTemps);
        }

        this.ajouterObstacles(deltaTemps);
        this.obstaclesPasses();
        this.enleverObstacles();
    }

    public void ajouterObstacles(double deltaTemps){

        if (ajoutObstaclesTemps >= 3) {
            Obstacle obstacle = new Obstacle(lrgCanva, htrCanva, fantome);
            this.listeObstacles.add(obstacle);
            ajoutObstaclesTemps = 0;

        } else {
            ajoutObstaclesTemps += deltaTemps;
        }
    }

    public void enleverObstacles() {
        for(int i = 0; i < this.listeObstacles.size(); i ++ ){
            Obstacle obs = this.listeObstacles.get(i);
            if(obs.getPasse() && obs.getCoordX() < - obs.getRayon()){
                this.listeObstacles.remove(obs);
            }
        }
    }

    public void obstaclesPasses(){
        Iterator<Obstacle> it = this.listeObstacles.iterator();
        while (it.hasNext()) {
            Obstacle obs = it.next();
            // a chaque obstacles passe
            if (!obs.getPasse() && obs.getPointPasse() < this.fantome.getPointPasse()) {
                obs.setPasse(true);
                this.score += 5;               // on augmente le score de 5 points
                this.nbreObstaclesPasses++;    //
                this.augmenterVitesseX = true;
                this.augmenterGravite = true;
            }
        }
    }

    public void evenement(actions evenement){
        switch (evenement){
            // un saut change instantanement la vitesse en y du fantome a 300px/s vers le haut
            case SAUTER:
                this.fantome.setVitesseY(-300);
                break;
            case PAUSE:
                this.pause = this.pause == true ? false : true;
                break;
            default:
                break;
        }

    }
}

