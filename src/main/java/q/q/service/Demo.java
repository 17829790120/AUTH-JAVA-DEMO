package q.q.service;

import cn.hutool.core.collection.CollectionUtil;
import q.q.bean.response.*;
import q.q.exception.AuthHttpException;

import java.util.List;

/**
 * 类说明
 *
 * @author shawn
 * @since 2022/10/25 17:39
 */
public class Demo {

    public static void main(String[] args) throws AuthHttpException {
        AuthConfig authConfig = new AuthDefaultConfigImpl("", "", "127.0.0.1:8080/");
        AuthHttpSupportService authHttpSupportService = AuthHttpServiceManager.getAuthHttpSupportService(authConfig);
        List<RespUserDetail> respUserDetail = authHttpSupportService.getRespUserDetail(CollectionUtil.newArrayList("128"));
        System.out.println(respUserDetail);

    }
}
