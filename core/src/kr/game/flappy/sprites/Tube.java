package kr.game.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 150;
    private static final int LOWEST_OPENING = 180;
    private static final int TUBE_GAP = 120;

    private Texture topTube;
    private Texture bottomTube;

    private Vector2 topTubePosition;
    private Vector2 bottomTubePosition;

    private Rectangle topTubeBound;
    private Rectangle bottomTubeBound;
    
    private Random rand;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        topTubePosition = new Vector2(
                x,
                rand.nextInt(FLUCTUATION) +
                        LOWEST_OPENING + TUBE_GAP
        );
        bottomTubePosition = new Vector2(
                x,
                topTubePosition.y - TUBE_GAP - bottomTube.getHeight()
        );
        topTubeBound = new Rectangle(
                topTubePosition.x, topTubePosition.y,
                topTube.getWidth(), topTube.getHeight()
        );
        bottomTubeBound = new Rectangle(
                bottomTubePosition.x, bottomTubePosition.y,
                bottomTube.getWidth(), bottomTube.getHeight()
        );
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getTopTubePosition() {
        return topTubePosition;
    }

    public Vector2 getBottomTubePosition() {
        return bottomTubePosition;
    }

    public void reposition(float x) {
        topTubePosition.x = x;
        bottomTubePosition.x = x;
        topTubeBound.x = x;
        bottomTubeBound.x = x;
    }

    public boolean collides(Rectangle object) {
        return topTubeBound.overlaps(object) ||
                bottomTubeBound.overlaps(object);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
