package com.github.DavidMendozaMartinez.RecyclerViewAdapter.model;

import android.graphics.Color;

public class Divider {
    public static final int DEFAULT_COLOR = Color.parseColor("#1E000000");

    private int color;
    private int left, top, right, bottom;

    public Divider() {
        this.color = DEFAULT_COLOR;
    }

    public Divider(int color) {
        this.color = color;
    }

    public Divider(int left, int top, int right, int bottom) {
        this.color = DEFAULT_COLOR;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Divider(int color, int left, int top, int right, int bottom) {
        this.color = color;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setMargins(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }
}
