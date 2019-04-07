package FlappyGhost;

public class Background extends Element {

    public Background(int x, int y){
        super(x,y);
        this.setImage(Resources.getBg());
    }

    @Override
    public void bouger(int lrgCanva, int htrCanva, double vitesseX, double vitesseY, double deltaTime){
        int offsetX = this.getCoordX() - (int)(vitesseX * deltaTime);
        if(offsetX + lrgCanva > 0){
            this.setCoordX(offsetX);
        } else {
            this.setCoordX( offsetX + lrgCanva);
        }
    }
}
