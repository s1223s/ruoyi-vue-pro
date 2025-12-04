package cn.iocoder.yudao.module.elevator.api;

import cn.iocoder.yudao.module.elevator.config.ElevatorApiProperties;
import com.smec.zhdt.openapi.call.client.common.util.CallClientEncryptUtils;
import com.smec.zhdt.openapi.call.client.vo.EncryptedVo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiClient {

    private final RestTemplate restTemplate;
    private final ElevatorApiProperties properties;

    public ApiClient(RestTemplate restTemplate, ElevatorApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public String querySubscriptionEvent() {
        CallClientEncryptUtils<String> encryptUtils = new CallClientEncryptUtils<>(properties.getCallAppKey(), properties.getCallAppSecretKey(), properties.getCorpId());
        EncryptedVo<String> encryptedData = encryptUtils.getEncryptedData(null);
        String url = properties.getBaseUrl() + properties.getCorpId() + "/v1/openapi/call/common/query/subscription/event";
        return post(url, encryptedData, null);
    }

    public String modifySubscriptionEvent(String eventType, String status, Object spec) {
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("status", status);
        requestParam.put("spec", spec);
        CallClientEncryptUtils<Object> encryptUtils = new CallClientEncryptUtils<>(properties.getCallAppKey(), properties.getCallAppSecretKey(), properties.getCorpId());
        EncryptedVo<Object> encryptedData = encryptUtils.getEncryptedData(requestParam);
        String url = properties.getBaseUrl() + properties.getCorpId() + "/v1/openapi/call/common/modify/subscription/event/" + eventType;
        return post(url, encryptedData, requestParam);
    }

    public String modifyCustomerUrl(String action, String eventType, String url) {
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("url", url);
        CallClientEncryptUtils<Object> encryptUtils = new CallClientEncryptUtils<>(properties.getCallAppKey(), properties.getCallAppSecretKey(), properties.getCorpId());
        EncryptedVo<Object> encryptedData = encryptUtils.getEncryptedData(requestParam);
        String requestUrl = properties.getBaseUrl() + properties.getCorpId() + "/v1/openapi/call/common/modify/customer/url/" + action + "/" + eventType;
        return post(requestUrl, encryptedData, requestParam);
    }

    public String queryEleDetails(Object spec) {
        CallClientEncryptUtils<Object> encryptUtils = new CallClientEncryptUtils<>(properties.getCallAppKey(), properties.getCallAppSecretKey(), properties.getCorpId());
        EncryptedVo<Object> encryptedData = encryptUtils.getEncryptedData(spec);
        String url = properties.getBaseUrl() + properties.getCorpId() + "/v1/openapi/call/ele/details/query";
        return post(url, encryptedData, spec);
    }

    public String modifyMappingRelation(String action, Object spec) {
        CallClientEncryptUtils<Object> encryptUtils = new CallClientEncryptUtils<>(properties.getCallAppKey(), properties.getCallAppSecretKey(), properties.getCorpId());
        EncryptedVo<Object> encryptedData = encryptUtils.getEncryptedData(spec);
        String url = properties.getBaseUrl() + properties.getCorpId() + "/v1/openapi/modify/mapping/relation/id/" + action;
        return post(url, encryptedData, spec);
    }

    private String post(String url, EncryptedVo<?> encryptedData, Object requestParam) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("corpId", properties.getCorpId());
        requestBody.put("appKey", properties.getCallAppKey());
        requestBody.put("dateStr", encryptedData.getDateStr());
        requestBody.put("timeStamp", encryptedData.getTimeStamp());
        requestBody.put("nonce", encryptedData.getNonce());
        requestBody.put("msgSignature", encryptedData.getMsgSignature());
        requestBody.put("requestParam", requestParam);
        return restTemplate.postForObject(url, requestBody, String.class);
    }
}
