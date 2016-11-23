package rasterization.Line;

import rasterdata.RasterImage;
import rasterization.Point;

public interface LineRasterizer<PixelType> {
    /**
     * Returns a raster image with a rasterized line described in a right-handed (y axis inverted with respect to raster image row axis) normalized coordinate system
     *
     * @param line  line, which is construct from 2 NotNull points
     * @param img   raster image to form background to the line, must not be null
     * @param pixel pixel value to store in each line pixel, must not be null
     * @return raster image with the line rasterized over the contents of the given image, never null
     */
    RasterImage<PixelType> drawLine(Line line, RasterImage<PixelType> img,
                                    PixelType pixel);
}
