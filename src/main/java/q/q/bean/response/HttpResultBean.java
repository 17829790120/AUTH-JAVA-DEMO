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
    private int code;

    private String msg;

    private Object resultObject;

    public HttpResultBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResultBean(int code) {
        this.code = code;
        this.msg = "SUCCESS";
    }

    public static HttpResultBean successOf(Object object) {
        HttpResultBean build = HttpResultBean.builder()
                .resultObject(object)
                .code(0)
                .build();
        return build;
    }

    public static HttpResultBean fromJson(String json) {
        return AuthGsonBuilder.create().fromJson(json, HttpResultBean.class);
    }
}
