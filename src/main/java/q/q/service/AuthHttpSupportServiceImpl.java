package q.q.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import q.q.bean.AuthAppAccessToken;
import q.q.bean.request.ReqUserAndDeptParam;
import q.q.bean.response.HttpResultBean;
import q.q.bean.response.ResAuthCodeUserInfo;
import q.q.bean.response.RespAllChildDept;
import q.q.bean.response.RespDeptDetail;
import q.q.bean.response.RespDeptMember;
import q.q.bean.response.RespPermScope;
import q.q.bean.response.RespUserDetail;
import q.q.exception.AuthHttpException;
import q.q.json.AuthGsonBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 统一授权工具service
 *
 * @author shawn
 * @since 2022/10/25 11:51
 */
@Slf4j
public class AuthHttpSupportServiceImpl implements AuthHttpSupportService {


    private AuthConfig authConfig;


    protected volatile Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
            .retryIfExceptionOfType(Exception.class)
            .withWaitStrategy(WaitStrategies.fixedWait(300, TimeUnit.MILLISECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();


    @Override
    public AuthConfig getAuthConfig() {
        return authConfig;
    }

    @Override
    public void setAuthConfig(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    @Override
    public String getAccessToken() throws AuthHttpException {
        String accessToken = getAccessToken(false);
        return accessToken;
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws AuthHttpException {
        if (!forceRefresh && !this.getAuthConfig().isAccessTokenExpired()) {
            return this.getAuthConfig().getAccessToken();
        }
        Lock lock = this.getAuthConfig().getAccessTokenLock();
        boolean locked = false;
        try {
            do {
                locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
                if (!forceRefresh && !this.getAuthConfig().isAccessTokenExpired()) {
                    return this.getAuthConfig().getAccessToken();
                }
            } while (!locked);
            String response = doGetAccessTokenRequest();
            return extractAccessToken(response);
        } catch (InterruptedException e) {
            throw new AuthHttpException(-1, e.getMessage());
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    @Override
    public String getAuthUrl(String redirectUrl, String state) throws AuthHttpException {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appId", authConfig.getAppId());
        param.put("redirectUrl", redirectUrl);
        param.put("state", state);
        String requestUrl = StrUtil.format("{}/auth/server/get/authUrl", authConfig.getBaseUrl());
        String oathUrlResult = httpPostWithParam(requestUrl, param, false);
        JSONObject jsonObject = JSONUtil.parseObj(oathUrlResult);
        String oathUrl = jsonObject.get("oathUrl").toString();
        return oathUrl;
    }

    @Override
    public ResAuthCodeUserInfo authCodeToUser(String code) throws AuthHttpException {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appId", authConfig.getAppId());
        param.put("code", code);
        param.put("accessToken", getToken());
        String requestUrl = StrUtil.format("{}/auth/server/codeToUser", authConfig.getBaseUrl());
        String oathUrlResult = httpPostWithParam(requestUrl, param, false);
        ResAuthCodeUserInfo resAuthCodeUserInfo = AuthGsonBuilder.create().fromJson(oathUrlResult, ResAuthCodeUserInfo.class);
        return resAuthCodeUserInfo;
    }

    @Override
    public String getLogoutUrl() throws AuthHttpException {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appId", authConfig.getAppId());
        String requestUrl = StrUtil.format("{}/auth/server/get/logout", authConfig.getBaseUrl());
        String oathUrlResult = httpPostWithParam(requestUrl, param, false);
        JSONObject jsonObject = JSONUtil.parseObj(oathUrlResult);
        String oathUrl = jsonObject.get("logoutUrl").toString();
        return oathUrl;
    }


    @Override
    public String getLogoutUrl(String redirectUrl, String state) throws AuthHttpException {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appId", authConfig.getAppId());
        param.put("redirectUrl", redirectUrl);
        param.put("state", state);
        String requestUrl = StrUtil.format("{}/auth/server/get/logout", authConfig.getBaseUrl());
        String oathUrlResult = httpPostWithParam(requestUrl, param, false);
        JSONObject jsonObject = JSONUtil.parseObj(oathUrlResult);
        String oathUrl = jsonObject.get("logoutUrl").toString();
        return oathUrl;
    }

    @Override
    public RespPermScope getPermScope() throws AuthHttpException {
        String reqPath = "/corp/dep/get/scope";
        String baseUrl = authConfig.getBaseUrl();
        String url = StrUtil.format("{}{}", baseUrl, reqPath);
        ReqUserAndDeptParam reqUserAndDeptParam = ReqUserAndDeptParam.builder()
                .build();
        String result = httpPostWithParam(url, reqUserAndDeptParam, true);
        RespPermScope respPermScope = AuthGsonBuilder.create().fromJson(result, RespPermScope.class);
        return respPermScope;
    }

    @Override
    public RespAllChildDept getAllChildDept(String thirdDepId) throws AuthHttpException {
        String reqPath = "/corp/dep/get/subId";
        String baseUrl = authConfig.getBaseUrl();
        String url = StrUtil.format("{}{}", baseUrl, reqPath);
        ReqUserAndDeptParam reqUserAndDeptParam = ReqUserAndDeptParam.builder()
                .thirdDepId(thirdDepId)
                .build();
        String result = httpPostWithParam(url, reqUserAndDeptParam, true);
        RespAllChildDept respAllChildDept = AuthGsonBuilder.create().fromJson(result, RespAllChildDept.class);
        return respAllChildDept;
    }

    @Override
    public List<RespDeptDetail> getDeptDetail(List<String> thirdDepIdList) throws AuthHttpException {
        String reqPath = "/corp/dep/get/detail";
        String baseUrl = authConfig.getBaseUrl();
        String url = StrUtil.format("{}{}", baseUrl, reqPath);
        ReqUserAndDeptParam reqUserAndDeptParam = ReqUserAndDeptParam.builder()
                .thirdDepIdList(thirdDepIdList)
                .build();
        String result = httpPostWithParam(url, reqUserAndDeptParam, true);
        List<RespDeptDetail> respDeptDetailList = AuthGsonBuilder.create().fromJson(result, new TypeToken<List<RespDeptDetail>>() {
        }.getType());
        return respDeptDetailList;
    }

    @Override
    public RespDeptMember getRespDeptMember(String thirdDepId, boolean fetchChild) throws AuthHttpException {
        String reqPath = "/corp/dep/user/list";
        String baseUrl = authConfig.getBaseUrl();
        String url = StrUtil.format("{}{}", baseUrl, reqPath);
        ReqUserAndDeptParam reqUserAndDeptParam = ReqUserAndDeptParam.builder()
                .thirdDepId(thirdDepId)
                .fetch(fetchChild)
                .build();
        String result = httpPostWithParam(url, reqUserAndDeptParam, true);
        RespDeptMember respDeptMember = AuthGsonBuilder.create().fromJson(result, RespDeptMember.class);
        return respDeptMember;
    }

    @Override
    public List<RespUserDetail> getRespUserDetail(List<String> thirdUserIdList) throws AuthHttpException {
        String reqPath = "/corp/dep/user/detail";
        String baseUrl = authConfig.getBaseUrl();
        String url = StrUtil.format("{}{}", baseUrl, reqPath);
        ReqUserAndDeptParam reqUserAndDeptParam = ReqUserAndDeptParam.builder()
                .thirdUserIdList(thirdUserIdList)
                .build();
        String result = httpPostWithParam(url, reqUserAndDeptParam, true);
        List<RespUserDetail> respUserDetailList = AuthGsonBuilder.create().fromJson(result, new TypeToken<List<RespUserDetail>>() {
        }.getType());
        return respUserDetailList;
    }


    /**
     * 设置当前的AccessToken
     *
     * @param resultContent 响应内容
     * @return access token
     * @throws AuthHttpException 异常
     */
    private String extractAccessToken(String resultContent) throws AuthHttpException {
        AuthConfig config = this.getAuthConfig();
        AuthAppAccessToken authAppAccessToken = AuthAppAccessToken.fromJson(resultContent);
        int expiresIn = authAppAccessToken.getExpireIn();
        config.updateAccessToken(authAppAccessToken.getAccessToken(), expiresIn);
        return authAppAccessToken.getAccessToken();
    }

    private String extractResultObject(String result) throws AuthHttpException {
        HttpResultBean httpResultBean = HttpResultBean.fromJson(result);
        int code = httpResultBean.getStatus();
        if (code != 0) {
            throw new AuthHttpException(code, httpResultBean.getInfo());
        }
        Object resultObject = httpResultBean.getResultObject();
        String toJson = AuthGsonBuilder.create().toJson(resultObject);
        return toJson;
    }

    /**
     * 发起http请求
     * "appId":"应用的Id",
     * "appSecret":"应用的秘钥"
     */
    private String doGetAccessTokenRequest() throws AuthHttpException {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appId", authConfig.getAppId());
        param.put("appSecret", authConfig.getSecret());
        String requestUrl = StrUtil.format("{}/auth/server/get/accessToken", authConfig.getBaseUrl());
        String tokenResult = httpPostWithParam(requestUrl, param, false);
        return tokenResult;
    }


    /**
     * http post请求结果
     *
     * @param requestUrl      请求地址
     * @param param           请求参数
     * @param needAccessToken header中是否需要添加accessToken
     * @return http的请求结果
     */
    private String httpPostWithParam(String requestUrl, Object param, boolean needAccessToken) throws AuthHttpException {
        String toJson = AuthGsonBuilder.create().toJson(param);
        Callable<String> httpWxWork = () -> doHttpPost(requestUrl, toJson, needAccessToken);
        try {
            String call = retryer.call(httpWxWork);
            String resultJson = extractResultObject(call);
            return resultJson;
        } catch (ExecutionException | RetryException e) {

            // 执行异常
            e.printStackTrace();
            throw new AuthHttpException(-1, "请求第三方接口异常");
        }
    }


    /**
     * 企业微信post请求
     */
    private String doHttpPost(String postUrl, String postBody, boolean needAccessToken) {
        Map<String, String> headers = new LinkedHashMap<>();
        try {
            HttpRequest httpRequest = HttpRequest.post(postUrl).body(postBody).timeout(6000);
            if (authConfig.getProxy() != null) {
                httpRequest.setProxy(authConfig.getProxy());
            }
            if (needAccessToken) {
                headers.put("Authorization", getToken());
                httpRequest.addHeaders(headers);
            }
            String body = httpRequest.execute().body();
            return body;
        } catch (HttpException | AuthHttpException e) {
            log.error("HTTP请求异常,请求地址为:{},请求参数:{},请求的header为:{}", postUrl, postBody, AuthGsonBuilder.create().toJson(headers));
            throw new RuntimeException(e);
        }
    }

    private String getToken() throws AuthHttpException {
        String accessToken = authConfig.getAccessToken();
        if (!authConfig.isAccessTokenExpired()) {
            return accessToken;
        }
        String findAccessToken = getAccessToken(true);
        return findAccessToken;
    }


}
