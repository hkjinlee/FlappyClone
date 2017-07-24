package kr.game.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import kr.game.flappy.FlappyDemo;
import kr.game.flappy.sprites.Bird;
import kr.game.flappy.sprites.Tube;

/**
 * Created by hkjinlee on 2017. 6. 5..
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Array<Tube> tubes;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 350);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();
        for (int i = 1; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(
                    i * (TUBE_SPACING + Tube.TUBE_WIDTH)
            ));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for (Tube tube : tubes) {
            if (cam.position.x - cam.viewportWidth / 2 >
                    tube.getTopTubePosition().x + tube.TUBE_WIDTH) {
                tube.reposition(
                        tube.getTopTubePosition().x +
                                (TUBE_COUNT - 1) * (TUBE_SPACING + Tube.TUBE_WIDTH)
                );
            }

            if (tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getTopTubePosition().x, tube.getTopTubePosition().y);
            sb.draw(tube.getBottomTube(), tube.getBottomTubePosition().x, tube.getBottomTubePosition().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
    }
}
