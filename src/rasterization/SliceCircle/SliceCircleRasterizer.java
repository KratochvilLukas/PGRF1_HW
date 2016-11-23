package rasterization.SliceCircle;

import rasterdata.RasterImage;
import rasterization.Line.Line;
import rasterization.Line.LineRasterizer;
import rasterization.Line.LineRasterizerDDA;
import rasterization.Point;

/**
 * Created by Krata on 21.11.2016.
 */
public class SliceCircleRasterizer<PixelType> {

    LineRasterizer<PixelType>liner = new LineRasterizerDDA<>();

    public RasterImage<PixelType> drawSliceCircle(SliceCircle sliceCircle, RasterImage<PixelType> img, PixelType pixel) {
        RasterImage<PixelType> result = img;

        Point center = sliceCircle.getCenter();
        Point radius = sliceCircle.getRadius();
        Point anglePoint = sliceCircle.getAnglePoint();

        float angleStart = Point.getAngle(center, radius) + (float) (2 * Math.PI);
        float angleEnd = Point.getAngle(center, anglePoint) + (float) (2 * Math.PI);

        Line line = new Line(center,radius);
        liner.drawLine(line,img,pixel);
        line.withLine(center,Point.rotatePoint(radius, angleEnd, center));
        liner.drawLine(line,img,pixel);

        if (angleEnd < angleStart)
            angleEnd += (float) (2 * Math.PI);
        for (float angle = angleStart; angle < angleEnd; angle += 0.001f) {
            Point first = radius;
            Point second = Point.rotatePoint(radius, angle, center);
            if (second.getX() >= 0 && second.getY() >= 0 && second.getX() <= 800 && second.getY() <= 600) {
                line.withLine(first,second);
                liner.drawLine(line,img,pixel);
            } else
                return result;
            radius = second;

        }


        return result;

    }
}
