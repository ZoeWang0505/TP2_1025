package FlappyGhost;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Element {
    private int coordX;         // coordonnee x de l'element sur le canva
    private int coordY;         // coordonnee y de l'element sur le canva
    private int rayon;          // rayon de l'element
    private Color couleur;      // couleur de l'element en mode debug
    private Image elementImage; // image utilisee pour afficher l'element en mode normal

    /**
     * Setter pour l'image affichee pour l'element
     * @param img image affichee pour l'element
     */
    public void setImage(Image img){
        this.elementImage = img;
    }

    /**
     * Getter pour l'image affichee pour l'element
     * @return image affichee pour l'element
     */
    public Image getImage(){
        return this.elementImage;
    }

    /**
     * Getter pour la couleur de l'element
     * @return couleur de l'element
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Setter pour la couleur de l'element
     * @param couleur couleur de l'element
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /**
     * Getter pour la coordonnee en x de l'element
     * @return coordonnee en x de l'element
     */
    public int getCoordX() {
        return this.coordX;
    }

    /**
     * Setter pour la coordonnee en x de l'element
     * @param x coordonnee en x de l'element
     */
    public void setCoordX(int x) {
        this.coordX = x;
    }

    /**
     * Getter pour la coordonnee en y de l'element
     * @return coordonnee en y de l'element
     */
    public int getCoordY() {
        return this.coordY;
    }

    /**
     * Setter pour la coordonnee en y de l'element
     * @param y coordonnee en y de l'element
     */
    public void setCoordY(int y){
        this.coordY = y;
    }

    /**
     * Getter pour le rayon de l'element
     * @return rayon de l'element
     */
    public int getRayon(){
        int val = this.rayon;
        return val;
    }

    /**
     * Setter pour le rayon de l'element
     * @param r rayon de l'element
     */
    public void setRayon(int r){
        this.rayon = r;
    }

    /**
     * Constructeur
     * @param x coordonnee en x de l'element
     * @param y coordonnee en y de l'element
     * @param rayon rayon de l'element
     * @param couleur couleur de l'element en mode debug
     */
    public Element(int x, int y, int rayon, Color couleur){
        this.coordX = x;
        this.coordY = y;
        this.rayon = rayon;
        this.couleur = couleur;
    }

    /**
     * Constructeur
     * @param x coordonnee en x de l'element
     * @param y coordonnee en y de l'element
     */
    public Element(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }

    /**
     * Constructeur
     * @param couleur couleur de l'element en mode debug
     */
    public Element(Color couleur){
        this.couleur = couleur;
    }

    /**
     * Getter pour la coordonne en x du point de depassement de l'element
     * @return la coordonne en x du point de depassement de l'element
     */
    public int getPointPasse(){
        return 0;
    }

    /**
     * Fonction qui met a jour les coordonnees x et y de l'element
     */
    public void bouger(int lrgCanva, int htrCanva, double vitesseX, double deltaTime){
    }
}
