package FlappyGhost;

import javafx.scene.paint.Color;

public class Fantome extends Element{

    public Fantome(int x, int y){
        super(x, y, 30,  Color.BLACK);
        this.setImage(Resources.getGhost());
    }

    @Override
    public void bouger(int lrgCanva,int htrCanva, double vitesseX, double vitesseY, double deltaTemps){
        int offset = (int)(this.getCoordY() + vitesseY * deltaTemps);
        if( offset < this.getRayon()) {
            this.setCoordY(this.getRayon());
        } else if (offset > htrCanva - this.getRayon()){
            this.setCoordY(htrCanva - this.getRayon());
        } else {
            this.setCoordY((int) (this.getCoordY() + vitesseY * deltaTemps));
        }
    }

    @Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() - this.getRayon();
        return pointPasse;
    }
}
