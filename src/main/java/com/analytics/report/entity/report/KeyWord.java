package com.analytics.report.entity.report;

public class KeyWord {
    private int keyWasClicked;
    private int keyWasNotReachGoal;
    private int keyHasConversationPer;

    public KeyWord() {
    }

    public int getKeyWasClicked() {
        return keyWasClicked;
    }

    public void setKeyWasClicked(int keyWasClicked) {
        this.keyWasClicked = keyWasClicked;
    }

    public int getKeyWasNotReachGoal() {
        return keyWasNotReachGoal;
    }

    public void setKeyWasNotReachGoal(int keyWasNotReachGoal) {
        this.keyWasNotReachGoal = keyWasNotReachGoal;
    }

    public int getKeyHasConversationPer() {
        return keyHasConversationPer;
    }

    public void setKeyHasConversationPer(int keyHasConversationPer) {
        this.keyHasConversationPer = keyHasConversationPer;
    }
}
