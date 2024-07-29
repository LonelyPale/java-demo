package my.demo.openapi.feishu;

import com.lark.oapi.service.authen.v1.model.CreateOidcAccessTokenResp;
import com.lark.oapi.service.authen.v1.model.GetUserInfoResp;
import com.lark.oapi.service.authen.v1.model.GetUserInfoRespBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class FeishuClientUtil {
    private static Client inner;
    private static final String CACHE_PREFIX_APP_ACCESS_TOKEN = "back3d#feishu#app_access_token";
    private static final String CACHE_PREFIX_ACCESS_TOKEN = "back3d#feishu#access_token#";
    private static final String CACHE_PREFIX_REFRESH_TOKEN = "back3d#feishu#refresh_token#";
    private static final int TOKEN_FAULT_TOLERANCE_TIME = 300; //秒

    public FeishuClientUtil(Config config) {
        inner = Client.newBuilder(config.getAppId(), config.getAppSecret()).debug(true).build();
    }

    public static GetUserInfoRespBody login(String code) throws Exception {
        //String appAccessToken = RedisClientUtil.get(CACHE_PREFIX_APP_ACCESS_TOKEN, String.class);
        String appAccessToken = "";
        log.debug("app_access_token: {}", appAccessToken);

        if (StringUtils.isEmpty(appAccessToken)) {
            AppAccessTokenInternalResp resp = inner.appAccessToken();
            appAccessToken = resp.getAppAccessToken();
            int expire = resp.getExpire() - TOKEN_FAULT_TOLERANCE_TIME;
            //long result = RedisClientUtil.set(CACHE_PREFIX_APP_ACCESS_TOKEN, appAccessToken, expire);
            log.debug("RedisClientUtil.set: result={}", appAccessToken);
        }

        CreateOidcAccessTokenResp resp = inner.userAccessToken(code);
        if (!resp.success()) {
            String msg = String.format("[inner.userAccessToken] code: %s, msg: %s", resp.getCode(), resp.getMsg());
            throw new Exception(msg);
        }
        String accessToken = resp.getData().getAccessToken();

        GetUserInfoResp userInfoResp = inner.userInfo(accessToken);
        if (!userInfoResp.success()) {
            String msg = String.format("[inner.userInfo] code: %s, msg: %s", userInfoResp.getCode(), userInfoResp.getMsg());
            throw new Exception(msg);
        }

        return userInfoResp.getData();
    }
}
