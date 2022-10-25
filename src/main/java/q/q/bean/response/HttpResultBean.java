package q.q.bean.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import q.q.json.AuthGsonBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpResultBean implements Serializable {

    private static final long serialVersionUID = 4311259181040453502L;
    private int status;

    private String info;

    private Object resultObject;

    public HttpResultBean(int status, String info) {
        this.status = status;
        this.info = info;
    }

    public HttpResultBean(int status) {
        this.status = status;
        this.info = "SUCCESS";
    }

    public static HttpResultBean successOf(Object object) {
        HttpResultBean build = HttpResultBean.builder()
                .resultObject(object)
                .status(0)
                .build();
        return build;
    }

    public static HttpResultBean fromJson(String json) {
        return AuthGsonBuilder.create().fromJson(json, HttpResultBean.class);
    }
}
