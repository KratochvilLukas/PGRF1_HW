package rasterization.Polygon;

import rasterization.Point;

import java.util.ArrayList;

/**
 * Created by Krata on 19.11.2016.
 */
public class Polygon {

    private ArrayList<Point>points;

    public Polygon (ArrayList<Point>points){
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
