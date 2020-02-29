package zc.api.util;

public class RestRequestParam {
    private Integer pageno;
    private Integer pagesize;
    private String type;
    private String status;
    private String order;
    private String content;

    @Override
    public String toString() {
        return "RestRequestParam{" +
                "pageno=" + pageno +
                ", pagesize=" + pagesize +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", order='" + order + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
