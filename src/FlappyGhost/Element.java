package FlappyGhost;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Element {
    private int coordX;
    private int coordY;
    private int rayon;
    private Color couleur;
    private Image elementImage;


    public void setImage(Image img){
        this.elementImage = img;
    }
    public Image getImage(){
        return this.elementImage;
    }


    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }


    public int getCoordX() {
        return this.coordX;
    }
    public void setCoordX(int x) {
        this.coordX = x;
    }


    public int getCoordY() {
        return this.coordY;
    }

    public void setCoordY(int y){
        this.coordY = y;
    }


    public int getRayon(){
        int val = this.rayon;
        return val;
    }
    public void setRayon(int r){
        this.rayon = r;
    }


    public Element(int x, int y, int rayon, Color couleur){
        this.coordX = x;
        this.coordY = y;
        this.rayon = rayon;
        this.couleur = couleur;
    }

    public Element(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }

    public Element(Color couleur){
        this.couleur = couleur;
    }

    public int getPointPasse(){
        return 0;
    }

    public void bouger(int lrgCanva, int htrCanva, double vitesseX, double vitesseY, double deltaTime){
    }

}
