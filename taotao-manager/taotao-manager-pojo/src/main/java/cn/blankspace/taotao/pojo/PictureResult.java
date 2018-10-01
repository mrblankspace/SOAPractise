package cn.blankspace.taotao.pojo;

/**
 * 图片上传返回数据类型
 */
public class PictureResult {
    private Integer error;    //success 0 failed1
    private String url;       //回显图片的url
    private String message; //错误消息

    public PictureResult(){

    }

    public PictureResult(Integer state, String url) {
        this.url = url;
        this.error = state;
    }
    public PictureResult(Integer state, String url, String errorMessage) {
        this.url = url;
        this.error = state;
        this.message = errorMessage;
    }
    public Integer getError() {
        return error;
    }
    public void setError(Integer error) {
        this.error = error;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
