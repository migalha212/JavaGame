import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import javax.swing.*;
import java.io.IOException;
import java.security.Key;

public class Game {
    private Screen screen;
    private Arena arena;

    Game() {
        try {
            arena = new Arena(100, 30, new Hero(new Position(10, 10)));
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if
            screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            screen.setCharacter(arena.getHero().getPosition().getX(), arena.getHero().getPosition().getY(),
                    TextCharacter.fromCharacter('X')[0]);
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() {
        while (true) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.Character && key.getCharacter()
                        == 'q') {
                    screen.close();
                    break;
                }
                processKey(key);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }


}

