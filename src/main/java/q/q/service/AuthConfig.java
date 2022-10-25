package q.q.service;

import q.q.bean.AuthAppAccessToken;

import java.net.Proxy;
import java.util.concurrent.locks.Lock;

/**
 * 类说明
 *
 * @author shawn
 * @since 2022/10/25 10:19
 */
public interface AuthConfig {

    /**
     * Gets access token.
     *
     * @return the access token
     */
    String getAccessToken();

    /**
     * Gets access token lock.
     *
     * @return the access token lock
     */
    Lock getAccessTokenLock();

    /**
     * Is access token expired boolean.
     *
     * @return the boolean
     */
    boolean isAccessTokenExpired();

    /**
     * 强制将access token过期掉
     */
    void expireAccessToken();

    /**
     * 应该是线程安全的
     *
     * @param accessToken 要更新的WxAccessToken对象
     */
    void updateAccessToken(AuthAppAccessToken accessToken);

    /**
     * 应该是线程安全的
     *
     * @param accessToken      新的accessToken值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateAccessToken(String accessToken, int expiresInSeconds);


    /**
     * Gets appid.
     *
     * @return the appid
     */
    String getAppId();

    /**
     * Gets secret.
     *
     * @return the secret
     */
    String getSecret();


    /**
     * Gets expires time.
     *
     * @return the expires time
     */
    long getExpiresTime();

    /**
     * Gets http proxy host.
     *
     * @return the http proxy host
     */
    String getHttpProxyHost();

    /**
     * Gets http proxy port.
     *
     * @return the http proxy port
     */
    int getHttpProxyPort();


    /**
     * 是否自动刷新token
     *
     * @return the boolean
     */
    boolean autoRefreshToken();

    /**
     * 获取请求的baseUrl
     */
    String getBaseUrl();

    /**
     * 获取http代理细腻系
     */
    Proxy getProxy();
}
