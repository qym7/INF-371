package typo;

import java.util.LinkedList;
import java.awt.Graphics;
import java.lang.String;

public class Group extends Box{

    double width;
    double ascent;
    double descent;
    double stretchingCapacity;
    double lineSkip;

    LinkedList<Box> BoxList;
    protected final LinkedList<Box> list = new LinkedList<Box>();

    Group (){
        BoxList = list;
    }

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w){
        return true;
    }

    public void add(Box b){
        this.BoxList.add(b);
    }

    //mais questce que ca sert?
    @Override
    public double getWidth(){
        //return this.list.element().getWidth();
        return 0;
    }

    @Override
    public double getAscent(){
        return 0;
        //return  this.list.element().getAscent();
    }

    @Override
    public double getDescent(){
        return 0;
        // return  this.list.element().getDescent();
    }
    @Override
    public double getStretchingCapacity(){
        return 0;
    }


    //comment je peut distinguer toutes les classes? Functions are dynamic!
    @Override
    public String toString(){
        String temp = super.toString();
        temp.replaceAll("[w=0.00000, a=0.00000, d=0.00000, sC=0.00000]",
                "Hbox[w=0.00000, a=0.00000, d=0.00000, sC=0.00000]");
        return temp;
    }

}
