package mybatis;

import mybatis.interceptor.PageParams;

/**
 * Created by Administrator on 2015/12/18.
 */
public class PageQueryVO {

    private int id;

    private String name;

    private PageParams page;

    public PageParams getPage() {
        return page;
    }

    public void setPage(PageParams page) {
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
