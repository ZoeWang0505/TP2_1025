package FlappyGhost;

import java.util.ArrayList;

public class Jeu {
    private ArrayList listeObstacle = null;
    private int nbreObstaclesPasses = 0;
    private int score = 0;
    private boolean collision = false;
    private boolean modeDebug = false;


    public void initialiser(){}

    public void jouer(){
        this.initialiser();

        while (!this.collision){
            this.collision();
            this.miseAJourEnvironnement();
        }
    }

    public void collision(){}

    public void miseAJourEnvironnement(){
        this.ajouterObstacles();
        this.obstaclesPasses();
    }

    public void ajouterObstacles(){}

    public void obstaclesPasses(){}




}
