package FlappyGhost;

import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {
    private ArrayList listeObstacles;
    private Fantome fantome;
    private int nbreObstaclesPasses = 0;
    private int score = 0;
    private boolean collision = false;
    private boolean modeDebug = false;


    public void initialiser(){
        fantome = new Fantome(0,10, false);
        listeObstacles = new ArrayList();
    }

    public void jouer(){
        while (true) {
            this.initialiser();

            while (!this.collision) {
                this.collision();
                this.miseAJourEnvironnement();
            }
        }
    }

    public void collision(){
        Iterator i = this.listeObstacles.iterator();
        while (i.hasNext()) {
            double distX = Math.abs(this.fantome.getCoordX() - ((Obstacle)i.next()).getCoordX());
            double distY = Math.abs(this.fantome.getCoordY() - ((Obstacle)i.next()).getCoordY());
            double distCentres = Math.sqrt((distX * distX) + (distY * distY));
            double sommeRayons = this.fantome.getRayon() + ((Obstacle)i.next()).getRayon();

            if (distCentres < sommeRayons){
                this.collision = true;
                ((Obstacle)i.next()).setCollision(true);
            }
        }
    }

    public void miseAJourEnvironnement(){
        this.ajouterObstacles();
        this.obstaclesPasses();
    }

    public void ajouterObstacles(){}

    public void obstaclesPasses(){
        Iterator i = this.listeObstacles.iterator();
        while (i.hasNext()) {
            if (!((Obstacle)i.next()).getPasse() && ((Obstacle)i.next()).getPointPasse() < this.fantome.getPointPasse()){
                ((Obstacle)i.next()).setPasse(true);
                this.score += 5;
                this.nbreObstaclesPasses++;
            }
        }

    }




}
