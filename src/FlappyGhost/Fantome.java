package FlappyGhost;

import javafx.scene.paint.Color;

public class Fantome extends Element{
    private double vitesseX = 120; // vitesse en x du jeu et du fantome
    private double vitesseY = 0;   // vitesse en y du fantome
    private double gravite = 500;  // gravite du jeu

    /**
     * Getter pour la gravite dans le jeu
     * @return gravite dans le jeu
     */
    public double getGravite(){
       return this.gravite;
    }

    /**
     * Setter pour la gravite dans le jeu
     * @param gravite gravite dans le jeu
     */
    public void setGravite(double gravite){
        this.gravite = gravite;
    }

    /**
     * Getter pour la vitesse en x du fantome
     * @return vitesse en x du fantome
     */
    public double getVitesseX(){
        return vitesseX;
    }

    /**
     * Setter pour la vitesse en x du fantome
     * @param vitesseX vitesse en x du fantome
     */
    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    /**
     * Getter pour la vitesse en y du fantome
     * @return vitesse en y du fantome
     */
    public double getVitesseY(){
        return vitesseY;
    }

    /**
     * Setter pour la vitesse en y du fantome
     * @param vitesseY vitesse en y du fantome
     */
    public void setVitesseY(double vitesseY){
        // limiter la vitesse en y entre 300 et -300px/s
        if (vitesseY > 300) {
            this.vitesseY = 300;
        } else if (vitesseY < -300) {
            this.vitesseY = -300;
        } else {
            this.vitesseY = vitesseY;
        }
    }

    /**
     * Constructeur
     * @param x coordonnee initiale en x sur le canva
     * @param y coordonnee initiale en y sur le canva
     */
    public Fantome(int x, int y){
        super(x, y, 30,  Color.BLACK);
        this.setImage(Ressources.getFantome());
    }

    /**
     * Fonction qui met a jour les coordonnees x et y du fantome
     * @param lrgCanva largeur du canva
     * @param htrCanva hauteur du canva
     * @param vitesseX vitesse en x du fantome et du jeu
     * @param deltaTemps avancement du temps de jeu
     */
    @Override
    public void bouger(int lrgCanva,int htrCanva, double vitesseX, double deltaTemps){

        // mettre a jour la vitesse en y du fantome
        setVitesseY(this.getVitesseY() + deltaTemps * this.gravite);

        // verifier si le deplacement en y est valide
        int offset = (int)(this.getCoordY() + this.vitesseY * deltaTemps);

        // si le fantome tente de sortir du canva, le faire rebondir
        if( offset < this.getRayon()) {
            setVitesseY(-this.getVitesseY() + deltaTemps * this.gravite);
        } else if (offset > htrCanva - this.getRayon()){
            setVitesseY(-this.getVitesseY() + deltaTemps * this.gravite);
        }

        // mettre a jour la coordonnee y du fantome
        this.setCoordY((int) (this.getCoordY() + this.vitesseY * deltaTemps));
    }

    /**
     * Getter pour la coordonne en x du point de depassement du fantome
     * @return la coordonne en x du point de depassement du fantome
     */
    @Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() - this.getRayon();
        return pointPasse;
    }
}
