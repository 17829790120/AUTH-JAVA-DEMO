package q.q.service;

import q.q.exception.AuthHttpException;

/**
 * 类说明
 *
 * @author shawn
 * @since 2022/10/25 17:39
 */
public class Demo {

    public static void main(String[] args) throws AuthHttpException {
        AuthConfig authConfig = new AuthDefaultConfigImpl("uda_635796b1284a9464114b1532", "9r3ix88lizjac812finrgg6pjszxs5mp4mu", "https://api.dev.161.54188.online/apaas/sso");
        AuthHttpSupportService authHttpSupportService = AuthHttpServiceManager.getAuthHttpSupportService(authConfig);
        String accessToken = authHttpSupportService.getAccessToken();
        System.out.println(accessToken);

    }
}
