package FlappyGhost;

import javafx.scene.paint.Color;

public class Obstacle extends Element{
    private enum obstacleType {SIMPLE, SINUS, QUANTIQUE}; // type d'obstacle
    private obstacleType type;                            // type d'obstacle
    private int rayonMax = 45;                            // rayon maximal d'un obstacle
    private int rayonMin = 10;                            // rayon minimal d'un obstacle
    private int angleActuel = 0;                          // angle actuel (pour le sinus)
    private int angleMax = 360;                           // angle maximal (pour le sinus)
    private boolean passe = false;                        // indique si l'obstacle est passe
    private boolean collision = false;                    // indique si l'obstacle est en collision avec le fantome
    private double tempsOscillement = 0;                  // temps d'oscillement d'obstacle sinus
    private double tempsTeleportation = 0;                // temps de teleportation pour les obstacles quantiques
    private int porteeTeleportation = 30;                  // distance limite de teleportation

    /**
     * Constructeur
     * @param lrgCanva largeur du canva
     * @param htrCanva hauteur du canva
     * @param fantome fantome dans le jeu
     */
    public Obstacle(int lrgCanva, int htrCanva, Fantome fantome){
        super(Color.YELLOW); // on represente les obstacles en jaune lors du mode debug
        int variationRayon = rayonMax - rayonMin + 1;
        int rayon = (int)(Math.random() * variationRayon) + rayonMin; // le rayon est definit de facon aleatoire
        this.setRayon(rayon);
        this.type = getTypeAleatoire(); // on choisit le type de l'obstacle de facon aleatoire

        // si l'obstacle est sinus, on s'assure qu'il ne sorte pas du canva en oscillant
        if (this.type == obstacleType.SINUS) {
            this.setCoordY((int) (this.getRayon() + 25 + (Math.random() * (htrCanva - (2 * (this.getRayon() +25))))));
        } else {
            this.setCoordY((int) (this.getRayon() + (Math.random() * (htrCanva - (2 * this.getRayon())))));
        }

        // on place l'obstacle juste en dehors du canvas
        this.setCoordX(fantome.getCoordX() + lrgCanva/2 + this.getRayon());
        this.setImage();
    }

    /**
     * Getter pour savoir si l'obstacle est passe
     * @return booleen qui indique si l'obstacle est depasse par le fantome
     */
    public boolean getPasse(){
        boolean val = this.passe;
        return val;
    }

    /**
     * Setter pour le booleen qui indique si l'obstacle est depasse par le fantome
     * @param passe booleen qui indique si l'obstacle est depasse par le fantome
     */
    public void setPasse(boolean passe){
        this.passe = passe;
    }

    /**
     * Setter pour le booleen qui indique si l'obstacle est entre en collision avec le fantome
     * @param collision booleen qui indique si l'obstacle est entre en collision avec le fantome
     */
    public void setCollision(boolean collision){
        this.collision = collision;
        Color couleur = this.collision ? Color.RED : Color.YELLOW;
        this.setCouleur(couleur);
    }

    /**
     * Getter pour la coordonne en x du point de depassement de l'obstacle
     * @return la coordonne en x du point de depassement de l'obstacle
     */
    @Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() + this.getRayon();
        return pointPasse;
    }

    /**
     * Fonction qui met a jour les coordonnees x et y des obstacles selon leur type
     * @param lrgCanva largeur du canva
     * @param htrCanva hauteur du canva
     * @param vitesseX vitesse en x du jeu
     * @param deltaTemps avancement du temps de jeu
     */
    @Override
    public void bouger(int lrgCanva, int htrCanva, double vitesseX, double deltaTemps){
        switch (this.type){
            case SIMPLE:
                this.setCoordX((int)(this.getCoordX() - vitesseX * deltaTemps));
                break;
            case SINUS:
                // mettre a jour l'oscillement a chaque 0.1 seconde
                if (tempsOscillement >= 0.1) {
                    if (angleActuel <= angleMax) {
                        angleActuel++;
                    }
                    int offsetY = (int) (Math.sin(angleActuel) * 25);
                    this.setCoordY(this.getCoordY() + offsetY);
                    tempsOscillement = 0;
                } else {
                    tempsOscillement += deltaTemps;
                }
                this.setCoordX((int) (this.getCoordX() - vitesseX * deltaTemps));
                break;
            case QUANTIQUE:
                // teleporter a cahque 0.2 secondes
                if (tempsTeleportation >= 0.2) {
                    double signeAleatoire = Math.pow(-1, Math.round(Math.random()));
                    int offsetX = (int) (Math.random() * porteeTeleportation * signeAleatoire);
                    int offsetY = (int) (Math.random() * porteeTeleportation * signeAleatoire);
                    this.setCoordX(this.getCoordX() + offsetX);
                    this.setCoordY(this.getCoordY() + offsetY);
                    tempsTeleportation = 0;
                } else {
                    tempsTeleportation += deltaTemps;
                }
                this.setCoordX((int) (this.getCoordX() - vitesseX * deltaTemps));
                break;
        }
    }

    /**
     * Fonction qui definit de facon aleatoire un type d'obstacle
     * @return un type d'obstacle
     */
    private obstacleType getTypeAleatoire(){
        int typeInt = (int)(Math.random() * 3);
        switch (typeInt){
            case 0:
                return obstacleType.SIMPLE;
            case 1:
                return obstacleType.SINUS;
            case 2:
                return obstacleType.QUANTIQUE;
            default:
                return obstacleType.SIMPLE;
        }
    }

    /**
     * Setter pour l'image de l'obstacle utilisee
     */
    private void setImage(){
        int num = (int)(Math.random() * 26);
        super.setImage(Ressources.getImage(num));
    }
}
