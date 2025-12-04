package cn.iocoder.yudao.module.elevator.util;

import cn.iocoder.yudao.module.elevator.config.ElevatorPushProperties;
import com.smec.zhdt.openapi.push.model.DecryptUtils;
import org.springframework.stereotype.Component;

@Component
public class DecryptUtil {

    private final DecryptUtils decryptUtils;

    public DecryptUtil(ElevatorPushProperties properties) {
        this.decryptUtils = new DecryptUtils(properties.getAppKey(), properties.getAppSecretKey(), properties.getCorpId());
    }

    public String decrypt(String msgSignature, String timeStamp, String nonce, String encryptMsg) {
        try {
            return decryptUtils.getDecryptMsg(msgSignature, timeStamp, nonce, encryptMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
