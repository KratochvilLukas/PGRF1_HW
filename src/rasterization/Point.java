package rasterization;

/**
 * Created by Krata on 19.11.2016.
 */
public class Point {
    private double posX;
    private double posY;


    public Point(double x, double y) {
        posX = x;
        posY = y;
    }


    public double getX() {

        return posX;
    }

    public double setX(int posX) {

        return this.posX = posX;
    }

    public double getY() {

        return posY;
    }

    public double setY(int posY) {
        return this.posY = posY;
    }

    @Override
    public String toString() {
        return "x: " + posX + " , y: " + posY;
    }

    public static Point rotatePoint(Point point, float angle, Point center) {

        float distance = distance(point, center);
        return new Point(center.getX() + ((float) Math.cos(angle) * distance),
                center.getY() + ((float) Math.sin(angle) * distance));
    }

    public static float distance(Point a, Point b) {
        double X1 = a.getX();
        double X2 = b.getX();
        double Y1 = a.getY();
        double Y2 = b.getY();
        return (float) Math.sqrt((Math.pow(X2 - X1, 2)) + (Math.pow(Y2 - Y1, 2)));
    }

    public static float getAngle(Point point, Point point2) {
        double dX1 = point2.getX() - point.getX();
        double dY1 = point2.getY() - point.getY();
        return (float) Math.atan2(dY1, dX1);

    }
}
