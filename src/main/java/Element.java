import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;

public abstract class Element {
    private Position position;

    protected Element() {
    }

    public abstract void setPosition(Position position);
    public abstract Position getPosition();
    public abstract void draw(TextGraphics graphics);

}
