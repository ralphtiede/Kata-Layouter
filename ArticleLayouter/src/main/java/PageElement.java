/**
 * Created by rtiede on 02.10.2015.
 */
public class PageElement {
    private Block block;
    private PagePosition position;

    public PageElement(Block block, int positionX, int positionY) {
        this.block = block;
        this.position = new PagePosition(positionX,positionY);

    }

    public Block getBlock() {
        return block;
    }

    public PagePosition getPosition() {
        return position;
    }

}