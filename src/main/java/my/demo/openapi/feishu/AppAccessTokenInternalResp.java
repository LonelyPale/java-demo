package my.demo.openapi.feishu;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppAccessTokenInternalResp {
    private int code; //错误码，非 0 取值表示失败

    private String msg; //错误描述

    @JSONField(name = "app_access_token")
    private String appAccessToken; //应用访问凭证

    private int expire; //app_access_token 的过期时间，单位为秒

    @JSONField(name = "tenant_access_token")
    private String tenantAccessToken; //租户访问凭证
}
