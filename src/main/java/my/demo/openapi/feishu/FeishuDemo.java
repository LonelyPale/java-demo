package my.demo.openapi.feishu;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class FeishuDemo {
    public static void main(String[] args) throws Exception {
        String appId = System.getenv("appId");
        String appSecret = System.getenv("appSecret");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appSecret)) {
            System.out.println("appId or appSecret is empty");
            return;
        }

        Config config = new Config(appId, appSecret);
        //FeishuClientUtil __ = new FeishuClientUtil(config);
        Client client = Client.newBuilder(appId, appSecret).debug(true).build();
//        AppAccessTokenInternalResp resp = client.appAccessToken();
//        System.out.println(resp);
//        TenantAccessTokenInternal resp1 = client.tenantAccessToken();
//        System.out.println(resp1);
        client.messages("on_3f96d5bd08d5e1696461dbcf002b96b9", "text", "{\"text\":\"test content 6 \\n [一个文档](https://www.baidu.com/)\"}");
        //client.batchGetId(new String[]{"18811241010", "13022222222"}, new String[]{"zhangsan@z.com", "lisi@a.com"});
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

}
