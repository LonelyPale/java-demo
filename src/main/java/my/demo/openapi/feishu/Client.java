package my.demo.openapi.feishu;

import com.alibaba.fastjson.JSON;
import com.lark.oapi.core.request.RequestOptions;
import com.lark.oapi.service.auth.v3.model.*;
import com.lark.oapi.service.contact.v3.enums.BatchGetIdUserUserIdTypeEnum;
import com.lark.oapi.service.im.v1.enums.CreateMessageReceiveIdTypeEnum;
import com.lark.oapi.service.im.v1.model.*;
import com.lark.oapi.service.contact.v3.model.*;
import com.lark.oapi.service.authen.v1.model.CreateOidcAccessTokenReq;
import com.lark.oapi.service.authen.v1.model.CreateOidcAccessTokenReqBody;
import com.lark.oapi.service.authen.v1.model.CreateOidcAccessTokenResp;
import com.lark.oapi.service.authen.v1.model.GetUserInfoResp;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class Client {
    private final String appId;
    private final String appSecret;
    private final boolean debug;
    private final com.lark.oapi.Client client;

    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    public Client(Builder builder) {
        this.appId = builder.appId;
        this.appSecret = builder.appSecret;
        this.debug = builder.debug;

        //默认配置为自建应用
        this.client = com.lark.oapi.Client.newBuilder(this.appId, this.appSecret).logReqAtDebug(this.debug).build();
    }

    public TenantAccessTokenInternal tenantAccessToken() throws Exception {
        InternalTenantAccessTokenReqBody reqBody = InternalTenantAccessTokenReqBody.newBuilder().appId(appId).appSecret(appSecret).build();
        InternalTenantAccessTokenReq req = InternalTenantAccessTokenReq.newBuilder().internalTenantAccessTokenReqBody(reqBody).build();
        InternalTenantAccessTokenResp resp = client.auth().tenantAccessToken().internal(req);
        if (!resp.success()) {
            String msg = String.format("[feishu.Client.tenantAccessTokenInternal] code: %s, msg: %s, reqId: %s", resp.getCode(), resp.getMsg(), resp.getRequestId());
            throw new Exception(msg);
        }

        byte[] body = resp.getRawResponse().getBody();
        String bodyStr = new String(body);
        return JSON.parseObject(bodyStr, TenantAccessTokenInternal.class);
    }

    public AppAccessTokenInternalResp appAccessToken() throws Exception {
        InternalAppAccessTokenReqBody reqBody = InternalAppAccessTokenReqBody.newBuilder().appId(this.appId).appSecret(this.appSecret).build();
        InternalAppAccessTokenReq req = InternalAppAccessTokenReq.newBuilder().internalAppAccessTokenReqBody(reqBody).build();
        InternalAppAccessTokenResp resp = client.auth().appAccessToken().internal(req);
        if (!resp.success()) {
            String msg = String.format("[feishu.Client.appAccessTokenInternal] code: %s, msg: %s, reqId: %s", resp.getCode(), resp.getMsg(), resp.getRequestId());
            throw new Exception(msg);
        }

        byte[] body = resp.getRawResponse().getBody();
        String bodyStr = new String(body);
        return JSON.parseObject(bodyStr, AppAccessTokenInternalResp.class);
    }

    public CreateOidcAccessTokenResp userAccessToken(String code) throws Exception {
        CreateOidcAccessTokenReqBody body = CreateOidcAccessTokenReqBody.newBuilder().grantType(GRANT_TYPE_AUTHORIZATION_CODE).code(code).build();
        CreateOidcAccessTokenReq req = CreateOidcAccessTokenReq.newBuilder().createOidcAccessTokenReqBody(body).build();
        return client.authen().oidcAccessToken().create(req);
    }

    public GetUserInfoResp userInfo(String userAccessToken) throws Exception {
        RequestOptions opt = RequestOptions.newBuilder().userAccessToken(userAccessToken).build();
        return client.authen().userInfo().get(opt);
    }

    public CreateMessageRespBody messages(String receiveId, String msgType, String content) throws Exception {
        CreateMessageReqBody reqBody = CreateMessageReqBody.newBuilder()
                .receiveId(receiveId)
                .msgType(msgType)
                .content(content)
                .uuid(UUID.randomUUID().toString())
                .build();
        CreateMessageReq req = CreateMessageReq.newBuilder()
                //.receiveIdType(CreateMessageReceiveIdTypeEnum.UNION_ID)
                .receiveIdType(CreateMessageReceiveIdTypeEnum.EMAIL)
                .createMessageReqBody(reqBody)
                .build();
        CreateMessageResp resp = client.im().message().create(req);
        if (!resp.success()) {
            String msg = String.format("[feishu.Client.message] code: %s, msg: %s, reqId: %s", resp.getCode(), resp.getMsg(), resp.getRequestId());
            throw new Exception(msg);
        }
        return resp.getData();
    }

    public BatchGetIdUserRespBody batchGetId(String[] mobiles, String[] emails) throws Exception {
        BatchGetIdUserReqBody reqBody = BatchGetIdUserReqBody.newBuilder()
                .mobiles(mobiles)
                .emails(emails)
                .includeResigned(true)
                .build();
        BatchGetIdUserReq req = BatchGetIdUserReq.newBuilder()
                .userIdType(BatchGetIdUserUserIdTypeEnum.OPEN_ID)
                .batchGetIdUserReqBody(reqBody)
                .build();
        BatchGetIdUserResp resp = client.contact().user().batchGetId(req);
        if (!resp.success()) {
            String msg = String.format("[feishu.Client.batchGetId] code: %s, msg: %s, reqId: %s", resp.getCode(), resp.getMsg(), resp.getRequestId());
            throw new Exception(msg);
        }
        return resp.getData();
    }

    public static Builder newBuilder(String appId, String appSecret) {
        return new Builder(appId, appSecret);
    }

    public static final class Builder {
        private String appId;
        private String appSecret;
        private boolean debug = false;

        public Builder(String appId, String appSecret) {
            this.appId = appId;
            this.appSecret = appSecret;
        }

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder appSecret(String appSecret) {
            this.appSecret = appSecret;
            return this;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
