package com.badlogic.game.task;

public abstract class TaskBase {
    protected boolean isActive;
    protected float elapsedTime;
    protected float taskDuration;

    public TaskBase(float duration) {
        this.taskDuration = duration;
        this.elapsedTime = 0;
        this.isActive = true;
    }

    public abstract void update(float delta);

    protected abstract void executeTask(float delta); // Abstract method for task behavior

    protected void completeTask() {
        isActive = false; // Mark task as complete
        onComplete();
    }

    protected abstract void onComplete(); // Runs upon task completion

    public boolean isTaskActive() {
        return isActive;
    }

    public void resetTask() {
        elapsedTime = 0;
        isActive = true;
    }
}



