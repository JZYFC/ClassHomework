package io.github.jzyfc.Two;

public class Rect {
    public double width;
    public double height;

    public Rect() {
        this.width = 10;
        this.height = 10;
    }

    public Rect(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return this.width * this.height;
    }

    public double perimeter() {
        return 2 * this.width + 2 * this.height;
    }
}
