import javax.swing.*;
import java.awt.*;

/**
* Класс нужен для создания тела обьекта (Пример: {@link Snake})
* img - картинка самого тела
* x - координата по оси OX
* y - координата по оси OY
*/

public class Body {
    
    // картинка самого тела
    Image img = new ImageIcon("res\\body.png").getImage();

    // координата по оси OX
    int x;
    // y - координата по оси OY
    int y;
    
    myDeque memory = new myDeque();

    public Body(int x, int y) {
        this.x = x;
        this.y = y;
        memory.add(x, y);
    }
}
