package q.q.service;

import q.q.exception.AuthHttpException;

import java.util.HashMap;
import java.util.Map;

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
        String accessToken2 = authHttpSupportService.getAccessToken();
        System.out.println(accessToken);
        System.out.println(accessToken2);

    
        Map map = new HashMap();
        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String key = entry.getKey().toString();
        }

    }
}
