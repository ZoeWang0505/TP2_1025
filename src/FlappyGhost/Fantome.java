package FlappyGhost;

import javafx.scene.image.Image;
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
        this.setImage(Resources.getGhost());
    }


    @Override
    public void bouger(int lrgCanva,int htrCanva, double vitesseX, double vitesseY, double detlaTime){
        //TODO:
        //this.setCoordY(offset);
    }

    @Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() - this.getRayon();
        return pointPasse;
    }
}
