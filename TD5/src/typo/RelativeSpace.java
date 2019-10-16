package typo;

import java.awt.*;

public class RelativeSpace extends Space {
    //char c;
    //Font f;

    RelativeSpace(double ch, Font font) {
        super(ch * font.getSize(),1);
        //c = ch;
        //f = font;
    }

}
