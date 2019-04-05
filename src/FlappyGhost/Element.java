package FlappyGhost;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Element {
    private double vitesseX;
    private double vitesseY;
    private Color couleur;

    private int coodX;
    public int getCoodX() {
        return coodX;
    }

    private int coodY;
    public int getcoodY() {
        return coodY;
    }

    private double rayon;


    private boolean modeDebug;
    public boolean getModeDebug(){
        return modeDebug;
    }
    public void setmodeDebug(boolean debug){
        modeDebug = debug;
        //TODO:
        //update view
    }

    public Element(int x, int y, double rayon, Color couleur, boolean isDebug){
        this.coodX = x;
        this.coodY = y;
        this.rayon = rayon;
        this.modeDebug = isDebug;
        this.couleur = couleur;
    }

    public Element(){
    }

    private Image elementImage;
    public void setImage(Image img){
        this.elementImage = img;
    }


    public int[] getPointPasse(){
        //TODO:
        int[] pointPasse = new int[]{this.coodY, this.coodY};
        return pointPasse;
    }

    public void bouger(double vitesseX, double vitesseY){
        //TODO:
    }

}
