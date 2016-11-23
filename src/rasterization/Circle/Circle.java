package rasterization.Circle;

import rasterization.Point;

/**
 * Created by Krata on 19.11.2016.
 */
public class Circle {
    private Point center;
    private Point radius;

    public Circle(Point center, Point radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public Point getRadius() {
        return radius;
    }
}
