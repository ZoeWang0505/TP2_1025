package FlappyGhost;

import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {
    private ArrayList<Obstacle> listeObstacles;
    private Fantome fantome;
    private int nbreObstaclesPasses = 0;
    private int score = 0;
    private boolean collision = false;
    private boolean modeDebug = false;
    private long tempsRef;
    private int lrgCanva;
    private int htrCanva;

    public Fantome getFantome() {
        return fantome;
    }

    public Jeu(int lrgFenetre, int htrFenetre, int htrBarreTache) {
        this.lrgCanva = lrgFenetre;
        this.htrCanva = htrFenetre - htrBarreTache;
    }


    public void initialiser(){
        this.fantome = new Fantome(this.lrgCanva/2,htrCanva/2);
        this.listeObstacles = new ArrayList<>();
    }


    public void jouer(){
            while (true) {
                this.initialiser();

                tempsRef = System.nanoTime();
                while (!this.collision) {
                    this.collision();
                    this.miseAJourEnvironnement();
                }
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

            if (distCentres < sommeRayons){
                this.collision = true;
                obs.setCollision(true);
            }
        }
    }

    public void miseAJourEnvironnement(){
        this.ajouterObstacles();
        this.obstaclesPasses();
    }

    public void ajouterObstacles(){
        long tempsActuel = System.nanoTime();
        double deltaTemps = (tempsActuel - tempsRef) * 1e-9;

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
