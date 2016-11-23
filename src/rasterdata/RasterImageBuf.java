package rasterdata;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.function.Function;

public class RasterImageBuf<PixelType> implements RasterImage<PixelType>,
        Presentable<Graphics> {
    private final
    BufferedImage img;
    private final
    Function<Integer, PixelType> toPixelType;
    private final
    Function<PixelType, Integer> fromPixelType;

    public RasterImageBuf(final int width, final int height,
                          final int imageType,
                          final Function<Integer, PixelType> toPixelType,
                          final Function<PixelType, Integer> fromPixelType) {
        img = new BufferedImage(width, height, imageType);
        this.toPixelType = toPixelType;
        this.fromPixelType = fromPixelType;
    }

    @Override
    public Optional<PixelType> getPixel(final int c, final int r) {
        if (c < 0 || r < 0 || c >= getWidth() || r >= getHeight())
            return Optional.empty();
        return Optional.of(toPixelType.apply(img.getRGB(c, r)));
    }

    @Override
    public RasterImage<PixelType> withPixel(final int c, final int r, final PixelType pixel) {
        if (!(c < 0 || r < 0 || c >= getWidth() || r >= getHeight())) {
            img.setRGB(c, r, fromPixelType.apply(pixel));
        }
        return this;
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public RasterImage<PixelType> clear(int pixel) {

        final Graphics gr = img.getGraphics();
        gr.setColor(new Color(pixel));
        gr.fillRect(0, 0, getWidth(), getHeight());

        return this;
    }

    @Override
    public Graphics present(final Graphics device) {
        device.drawImage(img, 0, 0, null);
        return device;
    }

}
