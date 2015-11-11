import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by rtiede on 02.10.2015.
 *
 * Requirements
 * 1. Place List of Blocks on one Page
 * 2. Blocks are placed in two columns of similar height starting from top. Last column must not be longer than previous column. Reading sequence must not be changed.
 * 3. Blocks may have different integer heights. Blocks can be divided into parts. Each part must have an integer height.
 * 4. The first block might be of type headline. Headline blocks may span multiple columns. All other blocks span just one column
 *
 * Not yet implemented:
 * 5. Pages have a maximum height and a maximum number of columns. Nevertheless two columns should be used whenever possible.
 * 6. Multiple pages may be added. Each page except the last one must be filled as much as possible.
 * 7. In any column, the total height of non-headline blocks must not be less than 5
 * 8. Blocks may be of type Picture. Pictures must not be divided into parts.
 * 9. If a picture needs to be placed in a new column due to its height, regular text blocks shall be placed before to fill the current column.
 * 10. Blocks may be of type Picture Caption. If a Picture Caption is directly below a Picture, it must be placed always directly below the picture inside the same column.
 * 11. One Picture may span more than one column. In this case, the picture shall always be placed at the bottom right corner of the final placement.
 *
 */
public class LayoutTest {
    @Test
    public void nothing(){
        Layouter layouter = new Layouter();
        List<Page> pages = layouter.layout(Collections.<Block>emptyList());
        Assert.assertEquals(1, pages.size());
        Page page = pages.get(0);
        Assert.assertEquals(0, page.getElements().size());
    }

    @Test
    public void oneBlockNoHamcrest(){
        Layouter layouter = new Layouter();
        Block block = block(1, 1);
        List<Page> pages = layouter.layout(Collections.singletonList(block));
        Assert.assertEquals(1, pages.size());
        Page page = pages.get(0);
        List<PageElement> elements = page.getElements();
        Assert.assertEquals(1, elements.size());
        PageElement element = elements.get(0);
        Assert.assertNotNull(element);
        Assert.assertEquals(block, element.getBlock());
        Assert.assertEquals(0, element.getPosition().getLeft());
        Assert.assertEquals(0, element.getPosition().getTop());
    }

    @Test
    public void oneBlock(){
        assertThat(layout(block(1, 1)),
                contains(hasProperty("elements", contains(
                        matchElement(0, 0)
                ))));
    }

    @Test
    public void twoBlocks(){
        assertThat(layout(block(1, 1), block(1, 1)),
                contains(hasProperty("elements", contains(
                        matchElement(0, 0),
                        matchElement(1, 0)
                ))));
    }

    @Test
    public void splitBlocksToMatchSize(){
        assertThat(layout(block(7, 1), block(4, 1)),
                contains(hasProperty("elements", contains(
                        matchElement(0, 0),
                        matchElement(1, 0),
                        matchElement(1, 1)
                ))));
    }

    @Test
    public void headline(){
        assertThat(layout(block(1, 2), block(7, 1), block(4, 1)),
                contains(hasProperty("elements", contains(
                        matchElement(0, 0),
                        matchElement(0, 1),
                        matchElement(1, 1),
                        matchElement(1, 2)
                ))));
    }

    private Matcher<Object> matchElement(int expectedX, int expectedY) {
        return allOf(

                hasProperty("position", equalTo(new PagePosition(expectedX,expectedY)))
        );
    }

    private Block block(int height, int width) {
        return new Block(width, height);
    }

    private List<Page> layout(Block ... blocks) {
        return new Layouter().layout(Arrays.asList(blocks));
    }
}
