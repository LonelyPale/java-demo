package my.demo.openapi.feishu;

import com.lark.oapi.service.contact.v3.model.BatchGetIdUserRespBody;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class FeishuDemo {
    private static Client client;

    public static void main(String[] args) throws Exception {
        String appId = System.getenv("app_id");
        String appSecret = System.getenv("app_secret");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appSecret)) {
            System.out.println("appId or appSecret is empty");
            return;
        }

        Config config = new Config(appId, appSecret);
        //FeishuClientUtil __ = new FeishuClientUtil(config);
        client = Client.newBuilder(appId, appSecret).debug(true).build();

        //messages();
        batchGetId();
    }

    public static void messages() throws Exception {
        //AppAccessTokenInternalResp resp = client.appAccessToken();
        //System.out.println(resp);
        //TenantAccessTokenInternal resp1 = client.tenantAccessToken();
        //System.out.println(resp1);
        client.messages("on_3f96d5bd08d5e1696461dbcf002b96b9", "text", "{\"text\":\"test content 6 \\n [一个文档](https://www.baidu.com/)\"}");
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

    public static void batchGetId() throws Exception {
        BatchGetIdUserRespBody resp = client.batchGetId(new String[]{"18811241010", "18158533076", "15174634420", "17865566591"}, new String[]{"test@email.com"});
        System.out.println(resp);
    }
}

