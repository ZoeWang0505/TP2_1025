package FlappyGhost;

import javafx.scene.paint.Color;

public class Fantome extends Element{
    private double gravite;

    public double getGravite(){
        double val = gravite;
        return val;
    }
    public void setGravite(double val){
        gravite = val;
    }

    public void event_click(){

    }

    public Fantome(int x, int y){
        super(x, y, 30,  Color.BLACK);
    }


    @Override
    public void bouger(double vitesseX, double vitesseY){
        //TODO:
    }

    @Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() - this.getRayon();
        return pointPasse;
    }
}
