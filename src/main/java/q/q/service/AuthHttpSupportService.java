package q.q.service;

import q.q.bean.response.ResAuthCodeUserInfo;
import q.q.bean.response.RespAllChildDept;
import q.q.bean.response.RespDeptDetail;
import q.q.bean.response.RespDeptMember;
import q.q.bean.response.RespPermScope;
import q.q.bean.response.RespUserDetail;
import q.q.exception.AuthHttpException;

import java.util.List;

/**
 * 类说明
 *
 * @author shawn
 * @since 2022/10/25 11:46
 */
public interface AuthHttpSupportService {


    /**
     * 获取WxMaConfig 对象.
     *
     * @return WxMaConfig
     */
    AuthConfig getAuthConfig();

    /**
     * 注入 {@link AuthConfig} 的实现.
     *
     * @param authConfig config
     */
    void setAuthConfig(AuthConfig authConfig);

    /////////////////////////////////////oauth授权关联接口////////////////////////////////

    /**
     * 获取access_token, 不强制刷新access_token.
     *
     * @see #getAccessToken(boolean)
     */
    String getAccessToken() throws AuthHttpException;


    /**
     * 获取授权链接信息
     */
    String getAuthUrl(String redirectUrl, String state) throws AuthHttpException;


    /**
     * code2User转换数据
     */
    ResAuthCodeUserInfo authCodeToUser(String code) throws AuthHttpException;

    /**
     * <pre>
     * 获取access_token，本方法线程安全.
     * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
     *
     * 另：本service的所有方法都会在access_token过期是调用此方法
     *
     * 程序员在非必要情况下尽量不要主动调用此方法
     *
     * </pre>
     *
     * @param forceRefresh 强制刷新
     */
    String getAccessToken(boolean forceRefresh) throws AuthHttpException;

    /**
     * 获取登出的地址
     */
    String getLogoutUrl() throws AuthHttpException;

    /////////////////////////////////////组织架构关联接口////////////////////////////////

    /**
     * 获取部门授权范围
     *
     * @return
     */
    RespPermScope getPermScope() throws AuthHttpException;

    /**
     * 获取所有的子部门
     *
     * @return
     */
    RespAllChildDept getAllChildDept(String thirdDepId) throws AuthHttpException;

    /**
     * 获取部门详情
     *
     * @return
     */
    List<RespDeptDetail> getDeptDetail(List<String> thirdDepIdList) throws AuthHttpException;

    /**
     * 获取部门成员的信息
     *
     * @return
     */
    RespDeptMember getRespDeptMember(String thirdDepId, boolean fetchChild) throws AuthHttpException;

    /**
     * 获取部门成员详细信息
     *
     * @return
     */
    List<RespUserDetail> getRespUserDetail(List<String> thirdUserIdList) throws AuthHttpException;


}
