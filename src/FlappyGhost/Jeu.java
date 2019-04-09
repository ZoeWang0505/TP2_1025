package FlappyGhost;

import java.util.*;

public class Jeu {
    private ArrayList<Obstacle> listeObstacles;
    private Fantome fantome;
    private Background bg;
    private int nbreObstaclesPasses = 0;
    private boolean augmenterVitesseX = false;
    private boolean augmenterGravite = false;
    private int score = 0;
    private boolean collision = false;
    private boolean modeDebug = false;
    private long tempsRef;
    private int lrgCanva;
    private int htrCanva;
    private double vitesseX = 120;
    private double vitesseY = 0;
    private double gravite = 500;
    private double ajoutObstaclesTemps = 0;

    public ArrayList<Obstacle> getListeObstacles(){
        return listeObstacles;
    }


    public Fantome getFantome() {
        return fantome;
    }

    public Background getBackgroud() {
        return bg;
    }

    public Jeu(int lrgFenetre, int htrFenetre) {
        this.lrgCanva = lrgFenetre;
        this.htrCanva = htrFenetre;
        this.initialiser();
    }

    public int getScore() {
        return score;
    }


    public void initialiser(){
        this.fantome = new Fantome(this.lrgCanva/2,htrCanva/2);
        this.listeObstacles = new ArrayList<>();
        this.bg = new Background(0,0);
    }


    public void jouer(double deltaTemps){
         this.collision();
         this.miseAJourEnvironnement(deltaTemps);
    }

    public void collision(){
        Iterator<Obstacle> i = this.listeObstacles.iterator();
        while (i.hasNext()) {
            Obstacle obs = i.next();
            double distX = Math.abs(this.fantome.getCoordX() - obs.getCoordX());
            double distY = Math.abs(this.fantome.getCoordY() - obs.getCoordY());
            double distCentres = Math.sqrt((distX * distX) + (distY * distY));
            double sommeRayons = this.fantome.getRayon() + obs.getRayon();

            if (distCentres < sommeRayons){
                this.collision = true;
                obs.setCollision(true);
            }
        }
    }

    public boolean getCollision(){
        return this.collision;
    }

    public void miseAJourEnvironnement(double deltaTemps){
        // la vitesse du fantome augmente de 15px/s a chaque deux obstacles depasses
        if (this.augmenterVitesseX) {
            this.vitesseX += 15 * ((nbreObstaclesPasses + 1) % 2);
            this.augmenterVitesseX = false;
        }

        // limiter la vitesse en y entre 300 et -300px/s
        if (this.vitesseY > 300) {
            this.vitesseY = 300;
        } else if (this.vitesseY < -300) {
            this.vitesseY = -300;
        }

        // la gravite augmente de 15px/s a chaque deux obstacles depasses
        if (this.augmenterGravite) {
            this.gravite += 15 * ((nbreObstaclesPasses + 1) % 2);
            this.augmenterGravite = false;
        }

        // mettre a jour la vitesse en y
        this.vitesseY += deltaTemps * gravite;
        fantome.bouger(this.lrgCanva, this.htrCanva, this.vitesseX, this.vitesseY, deltaTemps);
        bg.bouger(this.lrgCanva, this.htrCanva, this.vitesseX, this.vitesseY, deltaTemps);

        Iterator it = listeObstacles.iterator();
        while (it.hasNext()){
            Obstacle obstacle = (Obstacle)it.next();
            obstacle.bouger(this.lrgCanva, this.htrCanva, this.vitesseX, this.vitesseY, deltaTemps);
        }

        this.ajouterObstacles(deltaTemps);
        this.obstaclesPasses();
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

    public void obstaclesPasses(){
        Iterator<Obstacle> i = this.listeObstacles.iterator();
        while (i.hasNext()) {
            Obstacle obs = i.next();
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

    public void evenement(String evenement){
        switch (evenement){
            // un saut change instantanement la vitesse en y du fantome a 300px/s vers le haut
            case "sauter":
                this.vitesseY = -300;
                break;
            default:
                break;
        }

    }

}

