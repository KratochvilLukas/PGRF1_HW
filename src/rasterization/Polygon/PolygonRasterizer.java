package rasterization.Polygon;

import rasterdata.RasterImage;
import rasterization.Line.Line;
import rasterization.Line.LineRasterizer;
import rasterization.Line.LineRasterizerDDA;
import rasterization.Point;

import java.util.ArrayList;

/**
 * Created by Krata on 21.11.2016.
 */
public class PolygonRasterizer<PixelType> {

    LineRasterizer<PixelType>liner = new LineRasterizerDDA<>();

    public RasterImage<PixelType> drawPolygon(Polygon polygon,RasterImage<PixelType>img,PixelType pixel){
        RasterImage<PixelType>result = img;
        ArrayList<Point>points = polygon.getPoints();
        Point first = points.get(0);
        Point second = points.get(1);
        Line line = new Line(first,second);
        if (points.size()>0) {
            for (Point point : points) {
                second = point;
                line.withLine(first,second);
                liner.drawLine(line, img, pixel);
                first = second;
            }

        line.withLine(points.get(points.size()-1), points.get(0));
        liner.drawLine(line,img,pixel);
        }
        return result;
    }
}
