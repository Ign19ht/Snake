import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake extends Body {
    private Image imgUp = new ImageIcon("res\\head_up.png").getImage();
    private Image imgDown = new ImageIcon("res\\head_down.png").getImage();
    private Image imgRight = new ImageIcon("res\\head_right.png").getImage();
    private Image imgLeft = new ImageIcon("res\\head_left.png").getImage();

    ArrayList<Body> bodys = new ArrayList<>();

    enum Direction {UP, DOWN, LEFT, RIGHT}

    Direction direction;
    Direction directionNew;

    private int speed = 5;
    private int xForNewBody, yForNewBody;

    public Snake() {
        super(200, 320);
        img = imgRight;
        direction = Direction.RIGHT;
        directionNew = direction;
    }

    public void addBody() {
        bodys.add(new Body(xForNewBody, yForNewBody));
    }

    public int getCountBody() {
        return bodys.size();
    }

    public void move() {
        if (x % 40 == 0 && y % 40 == 0) {
            direction = directionNew;
        }
        setVectorsBody();
        switch (direction) {
            case RIGHT:
                x += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        memory.add(x, y);
    }

    private void setVectorsBody() {
        Vector vector;
        if (bodys.size() > 0) {
            vector = bodys.get(bodys.size() - 1).memory.pop();
            xForNewBody = vector.x;
            yForNewBody = vector.y;

            for (int i = bodys.size() - 1; i > 0; i--) {
                vector = bodys.get(i - 1).memory.pop();
                bodys.get(i).x = vector.x;
                bodys.get(i).y = vector.y;
                bodys.get(i).memory.add(vector.x, vector.y);
            }

            vector = memory.pop();
            bodys.get(0).x = vector.x;
            bodys.get(0).y = vector.y;
            bodys.get(0).memory.add(vector.x, vector.y);
        } else {
            vector = memory.pop();
            xForNewBody = vector.x;
            yForNewBody = vector.y;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (direction != Direction.DOWN) {
                    directionNew = Direction.UP;
                    img = imgUp;
                }
                break;
            case KeyEvent.VK_S:
                if (direction != Direction.UP) {
                    directionNew = Direction.DOWN;
                    img = imgDown;
                }
                break;
            case KeyEvent.VK_D:
                if (direction != Direction.LEFT) {
                    directionNew = Direction.RIGHT;
                    img = imgRight;
                }
                break;
            case KeyEvent.VK_A:
                if (direction != Direction.RIGHT) {
                    directionNew = Direction.LEFT;
                    img = imgLeft;
                }
                break;
        }
    }
}
