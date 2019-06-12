import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel implements ActionListener {
    public final static int FPS = 20;

    Timer timer = new Timer(FPS, this);

    Image background = new ImageIcon("res\\background.png").getImage();
    Image backgroundScore = new ImageIcon("res\\background_score.png").getImage();
    Image restart = new ImageIcon("res\\restart.png").getImage();
    boolean lose = false;

    Snake snake = new Snake();
    Score score = new Score();
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
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x < 500 && x > 300 && y > 250 && y < 350) {
                    lose = false;
                    snake = new Snake();
                    apples = new ArrayList<>();
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, 800, 600, null);
        g.drawImage(backgroundScore, 800, 0, 200, 600, null);
        g.drawImage(score.score, 825, 50, 150, 100, null);
        score.getScore(snake.getCountBody());
        g.drawImage(score.number1, 825, 175, 60, 60, null);
        g.drawImage(score.number2, 900, 175, 60, 60, null);
        for (Apple apple: apples) {
            g.drawImage(apple.img, apple.x, apple.y, 40, 40, null);
        }
        g.drawImage(snake.img, snake.x, snake.y, 40, 40, null);
        for (Body body: snake.bodys) {
            g.drawImage(body.img, body.x, body.y, 40, 40, null);
        }
        if (lose) {
            g.drawImage(restart, 300, 250, 200, 100, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!lose) {
            snake.move();
            eat();
            createApple();
            if (checkCollision()) {
                lose = true;
            }
            repaint();
        }
    }

    boolean checkCollision() {
        if (snake.x < 0 || snake.x > 760 || snake.y > 560 || snake.y < 0) {
            return true;
        }
        for (Body body: snake.bodys) {
            if (snake.x == body.x && snake.y == body.y) {
                return true;
            }
        }
        return false;
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
                apples.add(new Apple(x, y));
            }
        }
    }

    void eat() {
        int index = -1;
        for (int i = 0; i < apples.size(); i++) {
            if(snake.y == apples.get(i).y && snake.x == apples.get(i).x) {
                snake.addBody();
                index = i;
            }
        }
        if (index != -1) {
            apples.remove(index);
        }
    }
}
