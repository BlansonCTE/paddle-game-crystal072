import java.awt.Graphics2D;
import java.awt.Rectangle;

class Ball {
    private static final int DIAMETER = 30;    // initialize ball position and initial velocity
    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    }
    void move() {
        // hits left wall
        if (x + xa < 0)
            xa = game.speed;
        // hits right wall
        if (x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        // hits top wall
        if (y + ya< 0)
            ya = game.speed;
        // goes under paddle
        else if (y + ya > game.getHeight() - DIAMETER)
            game.gameOver();
        // collides with paddle
        if (collision()) {
            ya = -game.speed;
            y = game.paddle.getTopY() - DIAMETER;
            game.speed++;
        }
        x = x + xa;
        y = y + ya;
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    private boolean collision(){
        return game.paddle.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}