package FlappyGhost;

import javafx.scene.paint.Color;

public class Fantome extends Element{

    private double m_gravite;
    public double getGravite(){
        double val = m_gravite;
        return val;
    }
    public void setGravite(double val){
        m_gravite = val;
    }

    public void event_click(){

    }

    public Fantome(int x, int y,boolean isDebug){
        super(x, y, 30,  Color.BLACK, isDebug);
    }

    // @Override
//    public int[] getPasspoint(){
//        //TODO:
//
//    }
    @Override
    public void bouger(double vitesseX, double vitesseY){
        //TODO:
    }
}
