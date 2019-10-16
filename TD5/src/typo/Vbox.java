package typo;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;

public class Vbox extends Group{
    double lineSkip;
    Vbox(double lineSkip){
        this.lineSkip = lineSkip;
    }
    @Override
    public double getAscent(){
        return ascent;
    }
    @Override
    public double getDescent(){
        return descent;
    }
    @Override
    public double getStretchingCapacity() {
        return stretchingCapacity;
    }
    @Override
    public double getWidth(){
        return width;
    }

    public void add(Box a){
        super.add(a);
        width = Math.max(a.getWidth(),width);
        ascent += descent + lineSkip + a.getAscent();
        descent = a.getDescent();
        stretchingCapacity = Math.max(a.getStretchingCapacity(),stretchingCapacity);
    }
    public String toString(){
        String head="";
        for (Box b: list){
            head += b.toString()+"\n";
        }
        head = "Vbox"+super.toString()+"{\n"+head+"}";
        head = head.replaceAll("\n","\n   ");
        head = head.replaceAll("   }","}");
        return head;
    }
    public boolean doDraw(Graphics graph, double x, double y, double w){
        double ynow = y;
        for(Box b:list){
            b.doDraw(graph, x, ynow, w);
            ynow += b.getAscent()+b.getDescent()+lineSkip;
        }
        return true;
    }
}