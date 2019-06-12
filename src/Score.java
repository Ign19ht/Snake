import javax.swing.*;
import java.awt.*;

public class Score {
    Image score = new ImageIcon("res\\score.png").getImage();
    Image number1;
    Image number2;

    void getScore(int num) {
        int num1 = num / 10;
        int num2 = num % 10;
        String str = "res\\" + String.valueOf(num1) + ".png";
        number1 = new ImageIcon(str).getImage();
        str = "res\\" + String.valueOf(num2) + ".png";
        number2 = new ImageIcon(str).getImage();
    }
}
