package typo;

import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.font.TextLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics;

public class Hbox extends Group{

    double width;
    double ascent;
    double descent;
    double stretchingCapacity;

    Hbox(){
        width = 0;
        ascent = 0;
        descent = 0;
        stretchingCapacity = 0;
    }

    Hbox(double w,double a,double d, double s){
        width = w;
        ascent = a;
        descent = d;
        stretchingCapacity = s;
    };

    @Override
    public double getWidth(){
        double w = 0;
        for (Box b:list){
            w+=b.getWidth();
        }
        return w;
    };

    @Override
    public double getAscent(){
        double a = 0;
        for (Box b:list){
            if (b.getAscent()>a) a = b.getAscent();
        }
        return a;
    }

    @Override
    public double getDescent(){
        double a = 0;
        for (Box b:list){
            if (b.getDescent()>a) a =b.getDescent();
        }
        return a;
    }

    @Override
    public double getStretchingCapacity(){
        double a = 0;
        for (Box b:list){
            a +=b.getStretchingCapacity();
        }
        return a;
    }

    @Override
    public void add(Box b){
        this.list.add(b);
        ascent = getAscent();
        descent = getDescent();
        stretchingCapacity = getStretchingCapacity();
        width = getWidth();
    }

    @Override
    public String toString(){
        String head="";
        for (Box b: list){
            head += b.toString()+"\n";
        }
        head = "Hbox"+super.toString()+"{\n"+head+"}";
        head = head.replaceAll("\n","\n   ");
        head = head.replaceAll("   }","}");
        return head;
    }

    public boolean doDraw (Graphics graph, double x, double y, double w){
        double mw = getWidth();
        //double l;
        //if (this.list.element()!=null) l=this.list.element().getAscent();
        //else l =0;
        //double y0 = y;
        if (mw<=w) {
            double eti = w-mw, total =0;
            for (Box b:list){
                if (b instanceof RelativeSpace) total += b.getWidth();
            }
            for (Box b:list){
                if (b instanceof RelativeSpace) {
                    ((RelativeSpace) b).width += b.getWidth()/total*eti;
                }
                if (b instanceof Glyph){
                    //y = y0 +(l - b.getAscent());
                    b.doDraw(graph,x,y,b.getWidth());
                    x += b.getWidth();
                }
                else if(b instanceof Space){
                    x += b.getWidth();
                }
                else {
                    b.doDraw (graph, x, y, b.getWidth());
                    x += b.getWidth();
                }
            }
            return true;
        }
        else {
            for (Box b:list){
                if (b instanceof Glyph){
                    //y = y0 +(l - b.getAscent());
                    b.doDraw(graph,x,y,b.getWidth());
                    x += b.getWidth();
                }
                else if(b instanceof Space) {
                    x += b.getWidth();
                }
                else {
                    b.doDraw (graph, x, y, b.getWidth());
                    x += b.getWidth();
                }
            }
            return false;
        }
    }


}
