package cn.iocoder.yudao.module.elevator.controller;

import cn.iocoder.yudao.module.elevator.service.ElevatorService;
import cn.iocoder.yudao.module.elevator.util.DecryptUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/elevator/push")
public class PushController {

    private final DecryptUtil decryptUtil;
    private final ElevatorService elevatorService;

    public PushController(DecryptUtil decryptUtil, ElevatorService elevatorService) {
        this.decryptUtil = decryptUtil;
        this.elevatorService = elevatorService;
    }

    @PostMapping("/receive")
    public String receivePush(@RequestBody Map<String, String> pushData) {
        String msgSignature = pushData.get("msgSignature");
        String timeStamp = pushData.get("timeStamp");
        String nonce = pushData.get("nonce");
        String encryptMsg = pushData.get("encryptMsg");

        String decryptedMsg = decryptUtil.decrypt(msgSignature, timeStamp, nonce, encryptMsg);

        elevatorService.handlePushMessage(decryptedMsg);

        return "{\"code\":\"2000\",\"message\":\"success\"}";
    }

}
