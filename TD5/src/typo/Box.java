package typo;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Box {
	public abstract double getWidth();

	public abstract double getAscent();

	public abstract double getDescent();

	public abstract double getStretchingCapacity();

	final static boolean debug = false;
	public final boolean draw(Graphics graph, double x, double y, double w) {
		if (debug) {
			graph.setColor(Color.red);
			graph.drawRect((int) x, (int) y, (int) w, (int) (getAscent() + getDescent()));
			graph.setColor(Color.black);
		}
		return doDraw(graph, x, y, w);
	}

	public abstract boolean doDraw(Graphics graph, double x, double y, double w);

	public String toString(){
		return String.format("[w=%g, a=%g, d=%g, sC=%g]",
				this.getWidth(), this.getAscent(),
				this.getDescent(), this.getStretchingCapacity());
	}

}

