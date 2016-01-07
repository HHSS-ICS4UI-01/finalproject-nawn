/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.Model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Vonhn
 */
public class Player extends Entity {

    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 2.0f;
    private final float DAMP = 0.8f;

    // states for mario
    public enum State {

        STANDING, RUNNING
    }

    // the actual state mario is in
    private State state;
    // movement variables
    private Vector2 velocity;
    private Vector2 acceleration;
    // facing
    private boolean isFacingLeft;
    private boolean isFacingDown;
    // animation state counter
    private float stateTime;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);
        state = State.STANDING;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        isFacingLeft = false;
        stateTime = 0;
    }

    public void update(float delta) {
        //acceleration.y = -9.8f;
        velocity.mulAdd(acceleration, delta);
        velocity.x = velocity.x * DAMP;
        if (velocity.x < 0.01f && velocity.x > -0.01f) {
            velocity.x = 0;
        }
        velocity.y = velocity.y * DAMP;
        if (velocity.y < 0.01f && velocity.y > -0.01f) {
            velocity.y = 0;
        }
        addToPosition(velocity.x, velocity.y);

        // moving to the left
        if (velocity.x < 0) {
            isFacingLeft = true;
            if (state != State.RUNNING ) {
                stateTime = 0;
                state = State.RUNNING;
            }
        //moving right
        } else if (velocity.x > 0) {
            isFacingLeft = false;
            if (state != State.RUNNING ) {
                stateTime = 0;
                state = State.RUNNING;
            }
         //moving down
        } else if (velocity.y < 0) {
            isFacingDown = true;
            if (state != State.RUNNING ) {
                stateTime = 0;
                state = State.RUNNING;
            }
         //moving up
        } else if (velocity.y > 0) {
            isFacingDown = false;
            if (state != State.RUNNING ) {
                stateTime = 0;
                state = State.RUNNING;
            }
         //not moving
        } else {
            state = State.STANDING;
            stateTime = 0;
        }

        stateTime += delta;
    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }

    public void setState(State s) {
        if (state != s) {
            stateTime = 0;
        }
        state = s;
    }

    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public State getState() {
        return state;
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isFacingLeft() {
        return isFacingLeft;
    }
}
