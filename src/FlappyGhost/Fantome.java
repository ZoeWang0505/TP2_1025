package FlappyGhost;

import javafx.scene.paint.Color;

public class Fantome extends Element{

    private double vitesseX = 120;
    private double vitesseY = 0;

    private double gravite = 500;
    public double getGravite(){
       return this.gravite;
    }
    public void setGravite(double gravite){
        this.gravite = gravite;
    }

    public double getVitesseX(){
        return vitesseX;
    }
    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public double getVitesseY(){
        return vitesseY;
    }
    public void setVitesseY(double vitesseY){
        // limiter la vitesse en y entre 300 et -300px/s
        if (this.vitesseY > 300) {
            this.vitesseY = 300;
        } else if (this.getVitesseY() < -300) {
            this.vitesseY = -300;
        }
        this.vitesseY = vitesseY;
    }

    public Fantome(int x, int y){
        super(x, y, 30,  Color.BLACK);
        this.setImage(Resources.getGhost());
    }

    @Override
    public void bouger(int lrgCanva,int htrCanva, double vitesseX, double deltaTemps){

        setVitesseY(this.getVitesseY() + deltaTemps * this.gravite);

        int offset = (int)(this.getCoordY() + this.vitesseY * deltaTemps);

        if( offset < this.getRayon()) {

            setVitesseY(-this.getVitesseY() + deltaTemps * this.gravite);
            
        } else if (offset > htrCanva - this.getRayon()){

            setVitesseY(-this.getVitesseY() + deltaTemps * this.gravite);
        }

        this.setCoordY((int) (this.getCoordY() + this.vitesseY * deltaTemps));
    }

    @Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() - this.getRayon();
        return pointPasse;
    }
}
