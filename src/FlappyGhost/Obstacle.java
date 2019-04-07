package FlappyGhost;

import javafx.scene.paint.Color;


public class Obstacle extends Element{
    private enum obstacleType {SIMPLE, SINUS, QUANTIQUE};
    private obstacleType type;
    private int rayonMax = 45;
    private int rayonMin = 10;
    private int currentRadian = 0;
    private int radianMax = 360;
    private boolean passe = false;
    private boolean collision = false;
    private double tempsOscillement = 0;
    private double tempsTeleportation = 0;
    private int rangeTeleportation = 30;


    public Obstacle(int lrgCanva, int htrCanva, Fantome fantome){
        super(Color.YELLOW);
        int range = rayonMax - rayonMin + 1;
        int rayon = (int)(Math.random() * range) + rayonMin;
        this.setRayon(rayon);

        this.setCoordY((int)(Math.random() * htrCanva));
        this.setCoordX(fantome.getCoordX() + lrgCanva/2 + this.getRayon());

        this.type = getRandomType();
        this.setImage();
    }

    public boolean getPasse(){
        boolean val = this.passe;
        return val;
    }

    public void setPasse(boolean passe){
        this.passe = passe;
    }

    public boolean getCollision(){
        return collision;
    }

    public void setCollision(boolean collision){
        this.collision = collision;
        Color coleur = this.collision ? Color.RED : Color.YELLOW;
        this.setCouleur(coleur);
    }

@Override
    public int getPointPasse(){
        int pointPasse = this.getCoordX() + this.getRayon();
        return pointPasse;
    }

    @Override
    public void bouger(int lrgCanva, int htrCanva, double vitesseX, double vitesseY, double deltaTemps){
        //TODO:
        switch (this.type){
            case SIMPLE:
                // ne bouge pas
                this.setCoordX((int)(this.getCoordX() - vitesseX * deltaTemps));
                break;
            case SINUS:
                if (tempsOscillement >= 0.1) {
                    if (currentRadian <= radianMax) {
                        currentRadian++;
                    }
                    int offsetY = (int) (Math.sin(currentRadian) * 25);
                    this.setCoordY(this.getCoordY() + offsetY);
                    tempsOscillement = 0;
                } else {
                    tempsOscillement += deltaTemps;
                }
                this.setCoordX((int) (this.getCoordX() - vitesseX * deltaTemps));
                break;
            case QUANTIQUE:
                if (tempsTeleportation >= 0.2) {
                    double signeAleatoire = Math.pow(-1, Math.round(Math.random()));
                    int offsetX = (int) (Math.random() * rangeTeleportation * signeAleatoire);
                    int offsetY = (int) (Math.random() * rangeTeleportation * signeAleatoire);
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


    private obstacleType getRandomType(){
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

    private void setImage(){
        int num = (int)(Math.random() * 26);
        super.setImage(Resources.getImage(num));
    }

}
