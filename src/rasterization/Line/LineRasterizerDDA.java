package rasterization.Line;


import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;
import rasterization.Point;

public class LineRasterizerDDA<PixelType> implements LineRasterizer<PixelType> {

    @Override
    public
    @NotNull
    RasterImage<PixelType> drawLine(Line line, final @NotNull RasterImage<PixelType> img, final @NotNull PixelType pixel) {
        RasterImage<PixelType> result = img;
        double x;
        double k, y;
        double deltaX;
        double deltaY;
        Point X = line.getX();
        Point Y = line.getY();
        deltaX = (Y.getX() - X.getX());
        deltaY = (Y.getY() - X.getY());

        if (Math.abs(deltaY) <= Math.abs(deltaX)) {
            if (Y.getX() < X.getX()) {
                Point pom = X;
                X = Y;
                Y = pom;
            }
            x = (int) X.getX();
            y = X.getY();
            k = (deltaY / deltaX);

            do {
                result.withPixel((int) x, (int) Math.round(y), pixel);
                x += 1;
                y += k;
            } while (x <= Y.getX());

        } else {

            if (Y.getY() < X.getY()) {
                Point pom = X;
                X = Y;
                Y = pom;
            }
            k = deltaX / deltaY;

            x = X.getX();
            y = (int) X.getY();
            do {
                x += k;
                y += 1;
                result.withPixel((int) x, (int)y, pixel);

            } while (y < Y.getY());


        }
        return result;

    }
}

		/*
        RasterImage<PixelType> result = img; // no final, non functional approach, yuck
		final double c1 = (img.getWidth() - 1) * 0.5 * (x1 + 1); 
		final double r1 = (img.getHeight() - 1) * 0.5 * (1 - y1); 
		final double c2 = (img.getWidth() - 1) * 0.5 * (x2 + 1); 
		final double r2 = (img.getHeight() - 1) * 0.5 * (1 - y2);
		final double k = (r2 - r1) / (c2 - c1);
		final double q = r1 - k * c1;
		for (int c = (int) c1; c <= c2; c++) { // non functional approach, yuck
			result = result.withPixel(c, (int) (k * c + q), pixel); // yuck
		}
		return result;
		*/



