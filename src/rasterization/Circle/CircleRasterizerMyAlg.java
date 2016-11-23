package rasterization.Circle;

import rasterdata.RasterImage;
import rasterization.Line.Line;
import rasterization.Line.LineRasterizer;
import rasterization.Line.LineRasterizerDDA;
import rasterization.Point;

/**
 * Created by Krata on 21.11.2016.
 */
public class CircleRasterizerMyAlg<PixelType> implements CircleRasterizer<PixelType> {

    LineRasterizer<PixelType>liner = new LineRasterizerDDA<>();

    @Override
    public RasterImage<PixelType> drawCircle(Circle circle,RasterImage<PixelType> img, PixelType pixel) {

        RasterImage<PixelType> result = img;
        Point first = circle.getRadius();
        Line linePom = new Line(circle.getCenter(), circle.getRadius());
        for (float angle = 0; angle < 2 * Math.PI; angle += 0.01f) {
            Point second = Point.rotatePoint(circle.getRadius(), angle, circle.getCenter());
            if (first == circle.getRadius()) {
                first = second;
            }
            if (second.getX() >= 0 && second.getY() >= 0 && second.getX() <= 800 && second.getY() <= 600) {
                linePom = linePom.withLine(first, second);
                liner.drawLine(linePom,img,pixel);
            } else {
                return result;
            }
            first = second;
        }
        return result;
    }
}
