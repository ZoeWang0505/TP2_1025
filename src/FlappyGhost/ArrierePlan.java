package FlappyGhost;

public class ArrierePlan extends Element {

    /**
     * Constructeur
     * @param x position en x sur le canva
     * @param y position en y sur le canva
     */
    public ArrierePlan(int x, int y){
        super(x,y);
        this.setImage(Ressources.getArrierePlan());
    }

    /**
     * Fonction qui met a jour les coordonnees x et y de l'arriere-plan selon la vitesse en x
     * @param lrgCanva largeur du canva
     * @param htrCanva hauteur du canva
     * @param vitesseX vitesse en x
     * @param deltaTime avancement de temps du jeu
     */
    @Override
    public void bouger(int lrgCanva, int htrCanva, double vitesseX, double deltaTime){
        int offsetX = this.getCoordX() - (int)(vitesseX * deltaTime);
        if(offsetX + lrgCanva > 0){
            this.setCoordX(offsetX);
        } else {
            this.setCoordX(offsetX + lrgCanva);
        }
    }
}
