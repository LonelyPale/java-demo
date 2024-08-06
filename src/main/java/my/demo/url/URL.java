package my.demo.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URL {
    public static void main(String[] args) {
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
}
