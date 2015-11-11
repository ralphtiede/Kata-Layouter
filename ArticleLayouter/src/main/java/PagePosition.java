/**
 * Created by rtiede on 11.11.2015.
 */
public class PagePosition {
    private int left;
    private int top;

    public PagePosition(int left, int top) {
        this.left = left;
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setPosition(int left, int top) {
        this.left = left;
        this.top = top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PagePosition that = (PagePosition) o;

        if (left != that.left) return false;
        return top == that.top;

    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + top;
        return result;
    }
}
