package zc.api.bean;

import javax.lang.model.element.NestingKind;
import java.util.Map;

/**
 * 带泛型
 * @param <T>
 */
public class AjaxResult<T> {
    private int code ; // 状态码
    private T content ; // 请求内容
    private Map<String,Object> ext; //用于返回额外内容
    private String msg ; // 返回需要的提示信息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public static<T> AjaxResult<T> success(String msg, T content, Map<String,Object> ext){
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(1); // 1 代表成功
        result.setContent(content);
        result.setMsg(msg);
        result.setExt(ext);
        return result;
    }
    public static<T> AjaxResult<T>  fail(String msg, T content, Map<String,Object> ext){
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(0); // 1 代表成功
        result.setContent(content);
        result.setMsg(msg);
        result.setExt(ext);
        return result;
    }

    @Override
    public String toString() {
        return "AJAXResult [code=" + code + ", msg=" + msg + ", content=" + content + ", ext=" + ext + "]";
    }
}
