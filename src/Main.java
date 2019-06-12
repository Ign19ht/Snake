import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Main extends JPanel implements ActionListener {
    public final static int FPS = 20;

    Timer timer = new Timer(FPS, this);

    Snake snake = new Snake();
    ArrayList<Apple> apples = new ArrayList<>();

    public Main(JFrame frame) {
        timer.start();
        createApple();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Apple apple: apples) {
            g.drawImage(apple.img, apple.getX(), apple.getY(), 40, 40, null);
        }
        g.drawImage(snake.img, snake.x, snake.y, 40, 40, null);
        for (Body body: snake.bodys) {
            g.drawImage(body.img, body.x, body.y, 40, 40, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        eat();
        createApple();
        repaint();
    }

    void createApple() {
        int count = 1;
        if (snake.getCountBody() > 6) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            if (apples.size() < count) {
                int x = (int)(Math.random() * 800 / 40) * 40;
                int y = (int)(Math.random() * 600 / 40) * 40;
                System.out.println(x + " " + y);
                apples.add(new Apple(x, y));
            }
        }
    }

    void eat() {
        int index = -1;
        for (int i = 0; i < apples.size(); i++) {
            if(snake.y == apples.get(i).getY() && snake.x == apples.get(i).getX()) {
                snake.addBody();
                index = i;
            }
        }
        if (index != -1) {
            apples.remove(index);
        }
    }
}
