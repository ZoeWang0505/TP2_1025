package FlappyGhost;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Fantome extends Element{

    public Fantome(int x, int y){
        super(x, y, 30,  Color.BLACK);
        this.setImage(Resources.getGhost());
    }

    @Override
    public void bouger(int lrgCanva,int htrCanva, double vitesseX, double vitesseY, double deltaTemps){
        this.setCoordY((int)(this.getCoordY() + vitesseY * deltaTemps));
    }

    @Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() - this.getRayon();
        return pointPasse;
    }
}
