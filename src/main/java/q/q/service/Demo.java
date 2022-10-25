package q.q.service;

/**
 * 类说明
 *
 * @author shawn
 * @since 2022/10/25 17:39
 */
public class Demo {

    public static void main(String[] args) {
        AuthConfig authConfig = new AuthDefaultConfigImpl("", "", "");
        AuthHttpSupportService authHttpSupportService = AuthHttpServiceManager.getAuthHttpSupportService(authConfig);


    }
}
