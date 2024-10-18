import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Objects;

public class Coin extends Element {
    private Position position;

    Coin(Position position){
        this.position = position;
    }
    Coin(int x, int y){
        this.position = new Position(x,y);
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(new TerminalPosition(position.getX(),
                position.getY()), "♨");
    }


}
