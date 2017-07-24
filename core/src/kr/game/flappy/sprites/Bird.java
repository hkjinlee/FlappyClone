package kr.game.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by hkjinlee on 2017. 6. 5..
 */
public class Bird {
    private static final int GRAVITY = -10;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bound;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("bird.png");
        bound = new Rectangle(position.x, position.y,
                texture.getWidth(), texture.getHeight());
    }

    public void update(float dt) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(dt * MOVEMENT, velocity.y, 0);
        velocity.scl(1 / dt);
        if (position.y < 0 ) {
            position.y = 0;
        }
        bound.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void jump() {
        velocity.y = 250;
    }

    public Rectangle getBounds() {
        return bound;
    }

    public void dispose() {
        texture.dispose();
    }
}
