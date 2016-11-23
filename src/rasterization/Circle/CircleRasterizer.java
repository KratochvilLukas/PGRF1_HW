package rasterization.Circle;

import rasterdata.RasterImage;
import rasterization.Point;

/**
 * Created by Krata on 21.11.2016.
 */
public interface CircleRasterizer<PixelType> {
    RasterImage<PixelType> drawCircle(Circle circle, RasterImage<PixelType> img,
                                      PixelType pixel);
}
