package typo;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.font.TextLayout;
import java.awt.Color;
import java.awt.Graphics;

public class Space extends Box {
    double width;
    double stretchingCapacity;

    Space(double w,double sC) {
        width = w;
        stretchingCapacity = sC;
    }
    @Override
    public double getWidth() {
        return (this.width);
    }

    @Override
    public double getStretchingCapacity() {
        return (this.stretchingCapacity);
    }

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w){
        return true;
    }

    @Override
    public double getAscent(){
        return 0;
    };

    @Override
    public double getDescent(){
        return 0;
    };

    @Override
    public String toString() {
        return String.format("Space"+super.toString());
    }

}
