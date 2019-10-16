package typo;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.font.TextLayout;
import java.awt.Color;
import java.awt.Graphics;

public class Glyph extends Box {
	final private static FontRenderContext frc
			= new FontRenderContext(null, false, false);
	final private Font font;
	final private char[] chars;
	final private Rectangle2D bounds;

	@Override
	public String toString() {
		return String.format("Glyph(%s)"+super.toString(),
				chars[0]);
	}

	Glyph(Font f, char c) {
		this.chars = new char[1];
		chars[0] = c;
		font = f;
		TextLayout layout = new TextLayout("" + chars[0], font, frc);
		bounds = layout.getBounds();
	}

	public double getStretchingCapacity() {
		return (0);
	}

	public double getWidth() {
		return (bounds.getWidth());
	}

	public double getAscent() {
		return (-bounds.getY());
	}

	public double getDescent() {
		return bounds.getHeight() + bounds.getY();
	}

	public boolean doDraw(Graphics graph, double x, double y, double w) {
		try {
			graph.setFont(font);
			x = x - bounds.getX();
			y = y - bounds.getY();
			graph.drawChars(chars, 0, 1, (int) x, (int) y);
		} catch (ClassCastException e) {
			return false;
		}
		return true;
	}
}