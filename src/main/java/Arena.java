import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;
    private final Hero hero;
    private final List<Wall> walls;
    private final List<Coin> coins;

    Arena(int width, int height, Hero hero){
        this.width = width;
        this.height = height;
        this.hero = hero;
        this.walls = createWalls();
        this.coins = createCoins();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Hero getHero() {
        return hero;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2E8B57"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new
                        TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls){
            wall.draw(graphics);
        }
        for (Coin coin : coins){
            coin.draw(graphics);
        }
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp -> moveHero(getHero().moveUp());
            case ArrowDown -> moveHero(getHero().moveDown());
            case ArrowLeft -> moveHero(getHero().moveLeft());
            case ArrowRight -> moveHero(getHero().moveRight());

        }
    }

    private void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition((position));
        }

    }

    private boolean canHeroMove(Position position) {
        System.out.printf("(%d,%d)%n",position.getX(),position.getY());
        for (Coin coin : coins){
            if ( coin.getPosition().equals(position)){
                removeCoins(coin);
                break;
            }
        }
        return (position.getX() <= width - 1 && position.getX() >= 0
                && position.getY() <= height - 1 && position.getY() >= 0) && isWallThere(position);
    }

    private boolean isWallThere(Position position) {
        for(Wall wall : walls){
            if (wall.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }

    private void removeCoins(Coin coin){
        System.out.println("Coin collected!");
        this.coins.remove(coin);
    }
}
