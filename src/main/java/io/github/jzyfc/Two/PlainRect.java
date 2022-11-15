package io.github.jzyfc.Two;

public class PlainRect extends Rect {
    public double startX;
    public double startY;

    public PlainRect(double startX, double startY, double width, double height) {
        super(width, height);
        this.startX = startX;
        this.startY = startY;
    }

    public PlainRect() {
        super();
        this.startX = 0.;
        this.startY = 0.;
    }

    public boolean isInside(double x, double y) {
        return x >= startX && x <= startX + width && y >= startY && y <= startY + height;
    }
}
