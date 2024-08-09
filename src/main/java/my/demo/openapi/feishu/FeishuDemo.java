package my.demo.openapi.feishu;

import com.lark.oapi.service.contact.v3.model.BatchGetIdUserRespBody;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
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

        messages();
        //batchGetId();
//        test();
    }

    public static void messages() throws Exception {
        //AppAccessTokenInternalResp resp = client.appAccessToken();
        //System.out.println(resp);
        //TenantAccessTokenInternal resp1 = client.tenantAccessToken();
        //System.out.println(resp1);
        //client.messages("on_3f96d5bd08d5e1696461dbcf002b96b9", "text", "{\"text\":\"union_id: test content 6 \\n [一个文档](https://www.baidu.com/)\"}");
        //client.messages("test@email.com", "text", "{\"text\":\"email: test content 6 \\n [一个文档](https://www.baidu.com/)\"}");
//        client.messages("4ga9f4g3", "text", "{\"text\":\"email: test content 6 \\n [一个文档](https://www.baidu.com/)\"}");
//        client.messages("4ga9f4g3", "text", "{\"text\":\"maze测试1 给你分享了文件「测试图片PNG (1).png」，所属项目「111」 [立即查看](https://chuangyuan.8btc-ops.com/share?projectUid=Project_e98b16c1-465b-4bea-92ab-047397595823&fileUid=File_908809e3-8fd0-4c35-aacb-20b44ac45876&name=测试图片PNG (1).png&packageUid=&type=png&shareType=211)\"}");
        client.messages("4ga9f4g3", "text", "{\"text\":\"maze测试1 给你分享了文件「测试图片PNG (1).png」，所属项目「111」 [立即查看](https://chuangyuan.8btc-ops.com/share?projectUid=Project_e98b16c1-465b-4bea-92ab-047397595823&packageUid=&name=%E6%B5%8B%E8%AF%95%E5%9B%BE%E7%89%87PNG+%281%29.png&type=png&fileUid=File_908809e3-8fd0-4c35-aacb-20b44ac45876&shareType=211)\"}");
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

    public static void batchGetId() throws Exception {
        BatchGetIdUserRespBody resp = client.batchGetId(new String[]{"18811241010", "18158533076", "15174634420", "17865566591"}, new String[]{"test@email.com"});
        System.out.println(resp);
    }

    private static void test() throws MalformedURLException {
        url();
    }

    public static void url() {
        try {
            // 创建一个URL对象
            URL url = new URL("https://chuangyuan.8btc-ops.com/share?projectUid=Project_e98b16c1-465b-4bea-92ab-047397595823&fileUid=File_908809e3-8fd0-4c35-aacb-20b44ac45876&name=测试图片PNG (1).png&packageUid=&type=png&shareType=211");

            // 获取查询字符串
            String query = url.getQuery();

            // 解析查询字符串为键值对
            Map<String, String> queryParams = getQueryParams(query);

            // 对每个参数进行重新编码
            Map<String, String> encodedParams = new HashMap<>();
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                String encodedKey = URLEncoder.encode(entry.getKey(), "UTF-8");
                String encodedValue = URLEncoder.encode(entry.getValue(), "UTF-8");
                encodedParams.put(encodedKey, encodedValue);
            }

            // 将编码后的参数重新拼接成查询字符串
            String encodedQuery = getEncodedQueryString(encodedParams);

            // 重新构建URL
            String newUrl = url.getProtocol() + "://" + url.getHost() +
                    (url.getPort() == -1 ? "" : ":" + url.getPort()) +
                    url.getPath() + "?" + encodedQuery;

            // 打印新的URL
            System.out.println("重新拼接后的URL: " + newUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 解析查询字符串为键值对的Map
    public static Map<String, String> getQueryParams(String query) throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = URLDecoder.decode(keyValue[0], "UTF-8");
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
            queryParams.put(key, value);
        }
        return queryParams;
    }

    // 将Map中的键值对拼接成查询字符串
    public static String getEncodedQueryString(Map<String, String> params) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return queryString.toString();
    }

}
