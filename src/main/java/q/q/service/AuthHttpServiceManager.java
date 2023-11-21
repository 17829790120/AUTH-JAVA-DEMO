package q.q.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取调用数据的service
 *
 * @author shawn
 * @since 2022/10/25 17:43
 */
public class AuthHttpServiceManager {


    private static volatile Map<String, AuthHttpSupportService> authHttpSupportServiceMap = new ConcurrentHashMap<>();


    /**
     * 获取调用的service信息
     */
    public static AuthHttpSupportService getAuthHttpSupportService(AuthConfig authConfig) {
        AuthHttpSupportService authHttpSupportService = AuthHttpServiceManager.getAuthHttpSupportService(authConfig);
        return authHttpSupportService;
    }

    /**
     * 获取数据调用的service信息,需要强制刷新数据信息的时候调用
     */
    public static AuthHttpSupportService getAuthHttpSupportService(AuthConfig authConfig, boolean forceUpdate) {
        if (forceUpdate) {
            return doGetAuthHttpSupportService(authConfig);
        }
        AuthHttpSupportService authHttpSupportService = authHttpSupportServiceMap.get(authConfig.getAppId());
        if (authHttpSupportService != null) {
            return authHttpSupportService;
        }
        authHttpSupportService = doGetAuthHttpSupportService(authConfig);
        return authHttpSupportService;
    }

    private static AuthHttpSupportService doGetAuthHttpSupportService(AuthConfig authConfig) {
        AuthHttpSupportService authHttpSupportService = new AuthHttpSupportServiceImpl();
        authHttpSupportService.setAuthConfig(authConfig);
        authHttpSupportServiceMap.put(authConfig.getAppId(), authHttpSupportService);
        return authHttpSupportService;
    }
}
