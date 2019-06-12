import javax.swing.*;
import java.awt.*;

public class Apple {
    Image img = new ImageIcon("res\\apple.png").getImage();

    private int x;
    private int y;

    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
