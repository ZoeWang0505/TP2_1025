package FlappyGhost;

public class Background extends Element {

    public Background(int x, int y){
        super(x,y);
        this.setImage(Resources.getBg());
    }

    @Override
    public void bouger(int lrgCanva, int htrCanva, double vitesseX, double vitesseY, double deltaTime){
        //TODO: vitesse need to redefine
        int offsetX = (int)(this.getCoordX() - vitesseX);
        if(offsetX <= - lrgCanva){
            this.setCoordX(0);
        } else {
            this.setCoordX((int) (this.getCoordX() - vitesseX));
        }
    }
}
