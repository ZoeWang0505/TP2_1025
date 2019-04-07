package FlappyGhost;

import java.util.*;

public class Jeu {
    private ArrayList<Obstacle> listeObstacles;
    private Fantome fantome;
    private Background bg;
    private int nbreObstaclesPasses = 0;
    private int score = 0;
    private boolean collision = false;
    private boolean modeDebug = false;
    private long tempsRef;
    private int lrgCanva;
    private int htrCanva;
    //private int htrBarreTache = 40;
    //TODO:
    //vitesse need to be modify
    private double vitesseX = 10;
    private double vitesseY = 4;


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


    public void initialiser(){
        this.fantome = new Fantome(this.lrgCanva/2,htrCanva/2);
        this.listeObstacles = new ArrayList<>();
        this.bg = new Background(0, 0);
    }


    public void jouer(double detlaTime){
         //tempsRef = System.nanoTime();
         //tempsRef = detlaTime;
         this.collision();
         this.miseAJourEnvironnement(detlaTime);
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

    public void miseAJourEnvironnement(double detlaTime){
        fantome.bouger(this.lrgCanva, this.htrCanva, this.vitesseX, this.vitesseY, detlaTime);
        bg.bouger(this.lrgCanva, this.htrCanva, this.vitesseX, this.vitesseY, detlaTime);

        this.ajouterObstacles(detlaTime);
        this.obstaclesPasses();
    }

    public void ajouterObstacles(double deltaTemps){
        long tempsActuel = System.nanoTime();

        if (deltaTemps >= 3) {
            Obstacle obstacle = new Obstacle(lrgCanva, htrCanva);
            this.listeObstacles.add(obstacle);
            tempsRef = tempsActuel;
        }
    }

    public void obstaclesPasses(){
        Iterator<Obstacle> i = this.listeObstacles.iterator();
        while (i.hasNext()) {
            Obstacle obs = i.next();
            if (!obs.getPasse() && obs.getPointPasse() < this.fantome.getPointPasse()) {
                obs.setPasse(true);
                this.score += 5;
                this.nbreObstaclesPasses++;
            }
        }
    }

}

