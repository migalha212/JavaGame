import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{

    private Position position;

    Wall(Position position){
        this.position = position;
    }
    Wall(int x, int y){
        this.position = new Position(x,y);
    }

    public Position getPosition() {
        return position;
    }
    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(position.getX(),
                position.getY()), "W");
    }
}
