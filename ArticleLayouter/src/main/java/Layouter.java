import java.util.ArrayList;
import java.util.List;

/**
 * Created by rtiede on 02.10.2015.
 */
public class Layouter {

    private int y;
    private int x;
    private int columnStartY;
    private int columnHeight;

    public List<Page> layout(List<Block> blocks) {
        List<Page> pages= new ArrayList<Page>();
        Page page = new Page();

        int pos = 0;
        if (!blocks.isEmpty() && blocks.get(0).getWidth() > 1) {
            Block block = blocks.get(0);
            page.getElements().add(new PageElement(block, 0, 0));
            pos = 1;
            columnStartY = block.getHeight();
        }

        List<Block> remainingBlocks = blocks.subList(pos, blocks.size());
        int totalHeight = calculateTotalHeight(remainingBlocks);
        columnHeight = totalHeight - totalHeight/2;
        x = 0;
        y = 0;
        for (Block block : remainingBlocks) {
            handleBlockInColumn(page, block);
        }
        pages.add(page);
        return pages;
    }

    private void handleBlockInColumn(Page page, Block block) {
        int remainingHeight = columnHeight - y;
        if (block.getHeight() <= remainingHeight){
            page.getElements().add(new PageElement(block, x, y + columnStartY));
            y += block.getHeight();
            if (y == columnHeight){
                x++;
                y = 0;
            }
        } else {
            page.getElements().add(new PageElement(block, x, y + columnStartY));
            x++;
            y = 0;
            page.getElements().add(new PageElement(block, x, y + columnStartY));
            y = block.getHeight() - remainingHeight;
        }
    }

    private int calculateTotalHeight(List<Block> blocks) {
        int totalHeight = 0;
        for (Block block : blocks) {
            totalHeight += block.getHeight();
        }
        return totalHeight;
    }
}
