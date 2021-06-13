package cntnt;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class AsteroidRect {

    private final Node rectangle;

    AsteroidRect(int x, int y){
        rectangle = new Rectangle(x,y,Main.rectWidth, Main.rectWidth);
    }

    public Node getRectangle(double opacity) {

        if(opacity <= 0) {

            setFill(Color.rgb(0, 0, 200 - (int) ((opacity / Map.maxDepth) * 150)));

        }else{

            if(opacity > Map.maxHeight * 0.4) {
                setFill(Color.grayRgb(150 - (int)((opacity/Map.maxHeight)*100)));
            }else if(opacity > Map.maxHeight * 0.05){
                setFill(Color.rgb(0,150 - (int)((opacity/Map.maxHeight)*150),0));
            }else{
                setFill(Color.rgb(200,200,100));
            }

        }

        return rectangle;
    }

    void setFill(Color color){
        ((Rectangle) rectangle).setFill(color);
    }

}
