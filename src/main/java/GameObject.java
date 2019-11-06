
/**
 *
 * Generic Game Object.
 */
public class GameObject {

    private int x, y, velocity;
    private int height, width;
    private Type type;

    public GameObject(int x, int y, int velocity, int height, int width, Type type) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.height = height;
        this.width = width;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
