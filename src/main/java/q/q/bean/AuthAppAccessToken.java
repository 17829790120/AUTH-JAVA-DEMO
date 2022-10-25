package q.q.bean;


import lombok.Data;
import q.q.json.AuthGsonBuilder;

import java.io.Serializable;

/**
 * access token.
 *
 * @author Daniel Qian
 */
@Data
public class AuthAppAccessToken implements Serializable {
    private static final long serialVersionUID = 8709719312922168909L;

    private String accessToken;

    private int expiresIn = -1;

    public static AuthAppAccessToken fromJson(String json) {
        return AuthGsonBuilder.create().fromJson(json, AuthAppAccessToken.class);
    }

}
