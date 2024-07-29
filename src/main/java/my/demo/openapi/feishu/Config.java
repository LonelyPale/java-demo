package my.demo.openapi.feishu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Config {
    private String appId;

    private String appSecret;
}
