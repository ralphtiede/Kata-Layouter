/**
 * Created by rtiede on 02.10.2015.
 */
public class PageElement {
    private Block block;
    private int x;
    private int y;

    public PageElement(Block block, int x, int y) {
        this.block = block;
        this.x = x;
        this.y = y;
    }

    public Block getBlock() {
        return block;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}