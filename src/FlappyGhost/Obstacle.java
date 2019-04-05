package FlappyGhost;

public class Obstacle extends Element{

    private boolean m_passe;
    public boolean getPasse(){
        boolean val = m_passe;
        return val;
    }

    public Obstacle(){

    }

@Override
    public int[] getPointPasse(){
        //TODO:
        int[] pointPasse = new int[]{this.getCoodX(), this.getcoodY()};
        return pointPasse;
    }



}
