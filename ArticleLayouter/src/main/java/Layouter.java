import java.util.ArrayList;
import java.util.List;

/**
 * Created by rtiede on 02.10.2015.
 */
public class Layouter {

    private PagePosition currentPositionOnPage;
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
        currentPositionOnPage = new PagePosition(0,0);

        for (Block block : remainingBlocks) {
            handleBlockInColumn(page, block);
        }
        pages.add(page);
        return pages;
    }

    private void handleBlockInColumn(Page page, Block block) {
        int remainingBlockHeight = AddBlockToPage(page,block,0);
        if (remainingBlockHeight > 0) {
            AddBlockToPage(page,block, block.getHeight()-remainingBlockHeight);
        }
    }

    private int AddBlockToPage(Page page, Block block, int verticalOffset) {
        int remainingHeight = columnHeight - currentPositionOnPage.getTop();
        int heightOfBlockToAdd = block.getHeight()-verticalOffset;
        if (heightOfBlockToAdd > remainingHeight)
            heightOfBlockToAdd = remainingHeight;

       page.getElements().add(new PageElement(block, currentPositionOnPage.getLeft(), currentPositionOnPage.getTop() + columnStartY));
        currentPositionOnPage.moveDown(heightOfBlockToAdd);
        if (currentPositionOnPage.getTop() >= columnHeight){
            currentPositionOnPage.toNextColumn(0);
        }

        return block.getHeight()-verticalOffset-heightOfBlockToAdd;
    }

    private int calculateTotalHeight(List<Block> blocks) {
        int totalHeight = 0;
        for (Block block : blocks) {
            totalHeight += block.getHeight();
        }
        return totalHeight;
    }
}
