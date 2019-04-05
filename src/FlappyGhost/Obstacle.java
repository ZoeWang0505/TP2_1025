package FlappyGhost;

import javafx.scene.paint.Color;


public class Obstacle extends Element{

    private enum obstacleType {SIMPLE, SINUS, QUANTIQUE};

    private obstacleType type;

    private int rayonMax = 45;
    private int rayonMin = 10;

    private boolean passe;
    public boolean getPasse(){
        boolean val = this.passe;
        return val;
    }
    public void setPasse(boolean passe){
        this.passe = passe;
    }


    private boolean collision = false;
    public boolean getCollision(){
        return collision;
    }

    public void setCollision(boolean collision){
        this.collision = collision;
        Color coleur = this.collision ? Color.RED : Color.YELLOW;
        this.setCouleur(coleur);
    }
    public Obstacle(){

    }

@Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() + this.getRayon();
        return pointPasse;
    }

    @Override
    public void bouger(double vitesseX, double vitesseY){
        //TODO:
        switch (this.type){
            case SIMPLE:
                // ne bouge pas
                break;
            case SINUS:
                //
                break;
            case QUANTIQUE:
                break;
        }
    }


    public Obstacle(int x, int y,boolean isDebug){
        super(x, y, Color.YELLOW, isDebug);
        int range = rayonMax - rayonMin + 1;
        int rayon = (int)(Math.random() * range) + rayonMin;
        this.setRayon(rayon);
        this.type = getRandonType();
    }

    private obstacleType getRandonType(){
        int typeInt = (int)(Math.random() * 3);
        switch (typeInt){
            case 0:
                return obstacleType.SIMPLE;
            case 1:
                return obstacleType.SINUS;
            case 2:
                return obstacleType.QUANTIQUE;
            default:
                return obstacleType.SIMPLE;
        }
    }

}
