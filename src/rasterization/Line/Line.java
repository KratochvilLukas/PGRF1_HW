package rasterization.Line;


import rasterization.Point;

/**
 * Created by Krata on 21.11.2016.
 */
public class Line {
    private
    Point X;
    private
    Point Y;

    public Line(Point x, Point y) {
        this.X = x;
        this.Y = y;

    }

    public Line withLine(Point x, Point y) {
        this.X = x;
        this.Y = y;
        return this;
    }

    public Point getX() {
        return X;
    }

    public Point getY() {
        return Y;
    }
}
