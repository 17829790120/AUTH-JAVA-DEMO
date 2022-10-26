package q.q.service;

import lombok.Getter;
import q.q.bean.AuthAppAccessToken;
import q.q.json.AuthGsonBuilder;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Getter
public class AuthDefaultConfigImpl implements AuthConfig {
    protected volatile String appId;


    private volatile String secret;
    /**
     * 访问地址的baseUrl
     */
    protected volatile String baseUrl;


    private volatile Proxy proxy;


    protected Lock accessTokenLock = new ReentrantLock();

    private volatile String accessToken;

    private volatile long expiresTime;

    private volatile String httpProxyHost;
    private volatile int httpProxyPort;


    private volatile int retrySleepMillis = 1000;
    private volatile int maxRetryTimes = 5;


    /**
     * 基础构造方法
     */
    public AuthDefaultConfigImpl(String appId, String secret, String baseUrl) {
        this.appId = appId;
        this.secret = secret;
        this.baseUrl = baseUrl;
    }

    /**
     * 代理构造方法
     */
    public AuthDefaultConfigImpl(String appId, String secret, String baseUrl, String httpProxyHost, int httpProxyPort) {
        this.appId = appId;
        this.secret = secret;
        this.baseUrl = baseUrl;
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpProxyHost, httpProxyPort));
    }


    /**
     * 会过期的数据提前过期时间，默认预留200秒的时间
     */
    protected long expiresAheadInMillis(int expiresInSeconds) {
        return System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }

    /**
     * 判断 expiresTime 是否已经过期
     */
    protected boolean isExpired(long expiresTime) {
        return System.currentTimeMillis() > expiresTime;
    }

    @Override
    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Lock getAccessTokenLock() {
        return this.accessTokenLock;
    }

    public void setAccessTokenLock(Lock accessTokenLock) {
        this.accessTokenLock = accessTokenLock;
    }

    @Override
    public boolean isAccessTokenExpired() {
        return isExpired(this.expiresTime);
    }


    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        setAccessToken(accessToken);
        setExpiresTime(expiresAheadInMillis(expiresInSeconds));
    }


    @Override
    public void expireAccessToken() {
        this.expiresTime = expiresTime;
    }

    @Override
    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    @Override
    public long getExpiresTime() {
        return this.expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }


    @Override
    public String getHttpProxyHost() {
        return this.httpProxyHost;
    }

    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    @Override
    public int getHttpProxyPort() {
        return this.httpProxyPort;
    }

    public void setHttpProxyPort(int httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }


    @Override
    public String toString() {
        return AuthGsonBuilder.create().toJson(this);
    }


    @Override
    public boolean autoRefreshToken() {
        return true;
    }


    @Override
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public synchronized void updateAccessToken(AuthAppAccessToken accessToken) {
        updateAccessToken(accessToken.getAccessToken(), accessToken.getExpireIn());
    }


    @Override
    public Proxy getProxy() {
        return proxy;
    }


}
