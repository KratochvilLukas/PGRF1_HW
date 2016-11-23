import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBuf;
import rasterization.Circle.Circle;
import rasterization.Circle.CircleRasterizer;
import rasterization.Circle.CircleRasterizerMyAlg;
import rasterization.Line.Line;
import rasterization.Line.LineRasterizer;
import rasterization.Line.LineRasterizerDDA;
import rasterization.Point;
import rasterization.Polygon.Polygon;
import rasterization.Polygon.PolygonRasterizer;
import rasterization.SliceCircle.SliceCircle;
import rasterization.SliceCircle.SliceCircleRasterizer;

/**
 * Minimal GUI for drawing pixels
 *
 * @author PGRF FIM UHK
 * @version 2016
 */

public class Canvas {

    private final JFrame frame;
    private final JPanel panel;
    private RasterImage<Integer> img;
    private final Presentable<Graphics> imagePresenter;

    private LineRasterizer<Integer> liner = new LineRasterizerDDA<>();
    private CircleRasterizer<Integer> circler = new CircleRasterizerMyAlg<>();
    private PolygonRasterizer<Integer> polygoner = new PolygonRasterizer<>();
    private SliceCircleRasterizer<Integer> sliceCircler = new SliceCircleRasterizer<>();


    public Canvas(final int width, final int height) {
        frame = new JFrame();
        frame.setTitle("UHK FIM PGRF : Canvas");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final RasterImageBuf<Integer> tempImage =
                new RasterImageBuf<>(width, height, BufferedImage.TYPE_INT_RGB,
                        //PixelType toPixelType(Integer), Function<Integer, PixelType>, PixelType = Integer
                        new Function<Integer, Integer/*PixelType*/>() {
                            @Override
                            public Integer/*PixelType*/ apply(final Integer pixel) {
                                return pixel;
                            }
                        },
                        //Integer fromPixelType(PixelType), Function<PixelType, Integer>, PixelType = Integer
                        new Function<Integer/*PixelType*/, Integer>() {
                            @Override
                            public Integer apply(final Integer/*PixelType*/ pixel) {
                                return pixel;
                            }
                        }
                );
        img = tempImage;
        imagePresenter = tempImage;

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });

        //panel.addMouseListener(multiListener);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void clear(final int color) {

        img = img.clear(color);
    }

    public void present() {
        final Graphics graphics = panel.getGraphics();
        if (graphics != null)
            imagePresenter.present(graphics);
    }

    public void draw() {
        clear(0x2f2f2f);
        img = img.withPixel(10, 10, 0xffff00);
        Line line = new Line(new Point(50, 50), new Point(50, 160));
        liner.drawLine(line, img, 0xffff00);
        Circle circle = new Circle(new Point(100, 100), new Point(150, 100));
        circler.drawCircle(circle, img, 0xffff00);
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(10, 10));
        points.add(new Point(30, 30));
        points.add(new Point(10, 180));
        points.add(new Point(200, 350));
        Polygon polygon = new Polygon(points);
        polygoner.drawPolygon(polygon, img, 0xffff00);
        SliceCircle sliceCircle = new SliceCircle(new Point(400, 400), new Point(400, 600), new Point(500, 500));
        sliceCircler.drawSliceCircle(sliceCircle, img, 0xffff00);


    }


    public void start() {
        draw();
        present();
    }

    public static void main(String[] args) {
        final Canvas canvas = new Canvas(800, 600);
        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(() -> {
                SwingUtilities.invokeLater(() -> {
                    SwingUtilities.invokeLater(() -> {
                        canvas.start();
                    });
                });
            });
        });
    }

}