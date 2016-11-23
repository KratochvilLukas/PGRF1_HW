package rasterization.SliceCircle;

import rasterization.Point;

/**
 * Created by Krata on 19.11.2016.
 */
public class SliceCircle {
    private Point center;
    private Point radius;
    private Point anglePoint;

    public SliceCircle(Point center, Point radius, Point anglePoint) {
        this.center = center;
        this.radius = radius;
        this.anglePoint = anglePoint;
    }

    public Point getCenter() {
        return center;
    }

    public Point getRadius() {
        return radius;
    }

    public Point getAnglePoint() {
        return anglePoint;
    }
}
