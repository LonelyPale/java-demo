package my.demo.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class URL {
    public static void main(String[] args) {
        decode();
    }

    public static void encode() {
        String originalString = "Hello World! 你好，世界！";
        String url = "https://chuangyuan.8btc-ops.com/listings/detail?projectUid=Project_783f8ec7-6a3a-43db-8d09-88e7f6354391&name=%20test-0805-3";

        try {
            // 使用URLEncoder进行编码
            String encodedString = URLEncoder.encode(originalString, "UTF-8");
            String encodedUrl = URLEncoder.encode(url, "UTF-8");

            // 输出结果
            System.out.println("Original String: " + originalString);
            System.out.println("Encoded String: " + encodedString);

            System.out.println("Original URL: " + url);
            System.out.println("Encoded URL: " + encodedUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void decode() {
        String url = "https://open.feishu.cn/open-apis/authen/v1/index?redirect_uri=http%3A%2F%2Fsso.meilisci.com%2F%3Fclientid%3DDFlblnDMyP1gYXvq%26redirect_url%3Dhttps%253A%252F%252Fcy.bystack.com%252F&app_id=cli_a3e0b768483ad00d&state=LARK";
        //String url = "https%3A%2F%2Fwww.example.com%2Fpath%2Fto%2Fresource%3Fquery%3Dvalue%26another%3Dtest";
        try {
            String decodedUrl = URLDecoder.decode(url, "UTF-8");
            System.out.println("解码后的URL: " + decodedUrl);
        } catch (UnsupportedEncodingException e) {
            // 如果UTF-8不被支持，这不太可能发生，因为UTF-8总是被Java支持
            e.printStackTrace();
        }
    }
}
