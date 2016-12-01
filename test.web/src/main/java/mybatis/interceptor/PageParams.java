package mybatis.interceptor;

/**
 * Created by Administrator on 2015/12/17.
 */
public class PageParams {

    private int index = 0;

    private int count = 10;

    private int totalCount; //总记录数

    private boolean needQueryTotal = true;//是否需要查询总记录数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isNeedQueryTotal() {

        return needQueryTotal;
    }

    public void setNeedQueryTotal(boolean needQueryTotal) {
        this.needQueryTotal = needQueryTotal;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
