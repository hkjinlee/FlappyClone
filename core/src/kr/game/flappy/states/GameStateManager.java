package kr.game.flappy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by hkjinlee on 2017. 5. 29..
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
    }

    public State peek() {
        return states.peek();
    }

    public void set(State state) {
        states.pop();
        states.push(state);
    }

    public void update(float dt) {
        State state = states.peek();
        state.update(dt);
    }

    public void render(SpriteBatch sb) {
        State state = states.peek();
        state.render(sb);
    }
}
