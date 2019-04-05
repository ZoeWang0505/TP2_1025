package FlappyGhost;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Element {
    private double vitesseX;
    private double vitesseY;

    private Color couleur;
    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    private int coordX;
    public int getCoordX() {
        return coordX;
    }
    public void setCoordX(int x) {
        this.coordY = x;
    }

    private int coordY;
    public int getCoordY() {
        return coordY;
    }
    public void setCoordY(int y){
        this.coordY = y;
    }

    private int rayon = 0;
    public int getRayon(){
        int val = this.rayon;
        return val;
    }
    public void setRayon(int r){
        this.rayon = r;
    }


    private boolean modeDebug;
    public boolean getModeDebug(){
        return modeDebug;
    }
    public void setmodeDebug(boolean debug){
        modeDebug = debug;
        //TODO:
        //update view
    }

    public Element(int x, int y, int rayon, Color couleur, boolean isDebug){
        this.coordX = x;
        this.coordY = y;
        this.rayon = rayon;
        this.modeDebug = isDebug;
        this.couleur = couleur;
    }

    public Element(int x, int y,Color couleur, boolean isDebug){
        this.coordX = x;
        this.coordY = y;
        this.modeDebug = isDebug;
        this.couleur = couleur;
    }

    public Element(){
    }

    private Image elementImage;
    public void setImage(Image img){
        this.elementImage = img;
    }


    public int getPointPasse(){
        return 0;
    }

    public void bouger(double vitesseX, double vitesseY){
        //TODO:
    }

}
