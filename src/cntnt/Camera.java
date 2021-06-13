package cntnt;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.CacheHint;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Camera {

    int dx, dy;
    Timeline timeline;
    Pane root;
    double timestamp1, timestamp2;

    Camera(Pane root){

        root.setCache(true);
        root.setCacheHint(CacheHint.SPEED);

        this.root = root;
    }

    void initTimeline(){
        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {

            timestamp2 = System.currentTimeMillis();

            if(timestamp1 != 0)
                update((double)(timestamp2 - timestamp1)/1000);

            timestamp1 = System.currentTimeMillis();

        }));

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    void update(double timePast){

        root.setTranslateX(root.getTranslateX() + dx * timePast);
        root.setTranslateY(root.getTranslateY() + dy * timePast);

    }


}
