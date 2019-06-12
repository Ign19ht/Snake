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
    private Direction directionForNewBody;

    public Snake() {
        super(200, 320, Direction.RIGHT);
        img = imgRight;
    }

    public void addBody() {
        bodys.add(new Body(xForNewBody, yForNewBody, directionForNewBody));
    }

    public int getCountBody() {
        return bodys.size();
    }

    public void move() {
        setVectorsBody();
        if (x % 40 == 0 && y % 40 == 0) {
            direction = directionNew;
        }
        for (Body body : bodys) {
            if (body.x % 40 == 0 && body.y % 40 == 0) {
                body.direction = body.directionNew;
            }
        }
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
    }

    private void setVectorsBody() {
        if (bodys.size() > 0) {
            xForNewBody = getPos(true, )bodys.get(bodys.size() - 1).x;
            yForNewBody = bodys.get(bodys.size() - 1).y;

            for (int i = bodys.size() - 1; i > 0; i--) {
                bodys.get(i).x = bodys.get(i - 1).x;
                bodys.get(i).y = bodys.get(i - 1).y;
            }

            bodys.get(0).x = x;
            bodys.get(0).y = y;
        } else {
            xForNewBody = x;
            yForNewBody = y;
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
