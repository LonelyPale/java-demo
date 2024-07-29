package my.demo.openapi.feishu;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TenantAccessTokenInternal {
    private int code;
    private String msg;
    @JSONField(name = "tenant_access_token")
    private String tenantAccessToken;
    private int expire;
}
