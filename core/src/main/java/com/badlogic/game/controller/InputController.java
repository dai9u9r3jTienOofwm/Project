package com.badlogic.game.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input;

public class InputController implements InputProcessor {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean zPressed;
    private boolean shiftPressed;

    public InputController() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        zPressed = false;
        shiftPressed = false;
    }
    // To do
    // Implement the methods of InputProcessor to handle key events
    // Use switch-case statements to handle specific key events, return true to attribute
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                upPressed = true;
                break;
            case Input.Keys.DOWN:
                downPressed = true;
                break;
            case Input.Keys.LEFT:
                leftPressed = true;
                break;
            case Input.Keys.RIGHT:
                rightPressed = true;
                break;
            case Input.Keys.Z:
                zPressed = true;
                break;
            case Input.Keys.SHIFT_LEFT:
            case Input.Keys.SHIFT_RIGHT:
                shiftPressed = true;
                break;
        }
        return true;
    }

    public boolean isPressedUp() {
        return upPressed;
    }

    public boolean isPressedDown() {
        return downPressed;

    }
    
    public boolean isPressedLeft() {
        return leftPressed;
    }

    public boolean isPressedRight() {
        return rightPressed;
    }

    public boolean isShooting() {
        return zPressed;
    }
    public boolean isFocus() {
        return shiftPressed;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                upPressed = false;
                break;
            case Input.Keys.DOWN:
                downPressed = false;
                break;
            case Input.Keys.LEFT:
                leftPressed = false;
                break;
            case Input.Keys.RIGHT:
                rightPressed = false;
                break;
            case Input.Keys.Z:
                zPressed = false;
                break;
            case Input.Keys.SHIFT_LEFT:
            case Input.Keys.SHIFT_RIGHT:
                shiftPressed = false;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

