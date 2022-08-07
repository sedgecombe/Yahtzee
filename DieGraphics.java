import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class DieGraphics extends Canvas {

    private int xPos;
    private int yPos;
    private int dieNumber;

    final private int sideLength = 50;
    final private int arcConst = 5;

    public DieGraphics(int x, int y, int n) {
        this.xPos = x;
        this.yPos = y;
        this.dieNumber = n;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(this.xPos, this.yPos, this.sideLength + 10,
                this.sideLength + 10);

        g.setColor(Color.blue);
        g.fillRoundRect(this.xPos + 5, this.yPos - 5, this.sideLength,
                this.sideLength, this.arcConst, this.arcConst);
    }
}
