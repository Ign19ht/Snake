import javax.swing.*;
import java.awt.*;

public class Body {
    Image img = new ImageIcon("res\\body.png").getImage();

    int x;
    int y;
    myDeque memory = new myDeque();

    public Body(int x, int y) {
        this.x = x;
        this.y = y;
        memory.add(x, y);
    }
}
