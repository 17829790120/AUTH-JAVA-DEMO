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
    private static String appId = "uda_637b3b8ce4b07197c6d2307f";

    private static String screct = "z1z1fhcksx0nyj3zyf190kt671p8dzz7qw4";
    /**
     * apaas/sso前面的域名根据客户服务器进行获取
     */
    private static String baseUrl = "https://dev-cloud.qimaiit.com/apaas/authorization";

    public static void main(String[] args) throws AuthHttpException {

        AuthConfig authConfig = new AuthDefaultConfigImpl(appId, screct, baseUrl);
        AuthHttpSupportService authHttpSupportService = AuthHttpServiceManager.getAuthHttpSupportService(authConfig);

        String accessToken = authHttpSupportService.getAuthUrl("123", "234");
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
