package com.badlogic.game.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input;

public class InputController extends InputProcessor {
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
        switch (keycode) {}
    }

    public boolean isPressedUp() {
        return upPressed;
    }

    public boolean isPressedDown() {
        return downPressed;

    }
    // To do
    // Làm tương tự cho 2 phím

    public boolean isShooting() {
        return zPressed;
    }

    public boolean isFocus() {
        return shiftPressed;
    }
}
