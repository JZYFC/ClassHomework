package io.github.jzyfc.Four;

public class Cylinder implements C {

    public double radius;
    public double height;
    public String color;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
        this.color = "white";
    }

    @Override
    public double area() {
        return PI * radius * radius * 2 + 2 * PI * radius * height;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public double volume() {
        return PI * radius * radius * height;
    }
}
