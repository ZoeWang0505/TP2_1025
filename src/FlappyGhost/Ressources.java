package FlappyGhost;

import javafx.scene.image.Image;

import java.text.MessageFormat;

public class Ressources {
    // repertoire d'images d'obstacles disponibles
    private static String m_Images[] = {
            "0.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png",
            "7.png", "8.png", "9.png", "10.png", "11.png", "12.png", "13.png",
            "14.png", "15.png", "16.png", "17.png", "18.png", "19.png", "20.png", "21.png",
            "22.png", "23.png", "24.png", "25.png", "26.png"
    };

    /**
     * Getter pour les images en repertoire
     * @param n index de l'image en repertoire
     * @return l'image selectionnee
     */
    public static Image getImage(int n){
        String str = "";
        if(n <= m_Images.length - 1) {
            str = MessageFormat.format("file:obstacles/{0}", m_Images[n]);
            return new Image(str);
        }
        return null;
    }

    /**
     * Getter pour l'image du fantome
     * @return l'image du fantome
     */
    public static Image getFantome(){
        return new Image("file:ghost.png");
    }

    /**
     * Getter pour l'image de l'arriere-plan
     * @return l'image de l'arriere-plan
     */
    public static Image getArrierePlan(){
        return new Image("file:bg.png");
    }
}
