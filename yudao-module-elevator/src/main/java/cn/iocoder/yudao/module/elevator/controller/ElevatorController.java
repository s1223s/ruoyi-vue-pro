package cn.iocoder.yudao.module.elevator.controller;

import cn.iocoder.yudao.module.elevator.api.ApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/elevator")
public class ElevatorController {

    private final ApiClient apiClient;

    public ElevatorController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Elevator!";
    }

    @GetMapping("/query-subscription-event")
    public String querySubscriptionEvent() {
        return apiClient.querySubscriptionEvent();
    }

    @PostMapping("/modify-subscription-event")
    public String modifySubscriptionEvent(@RequestBody Map<String, Object> body) {
        String eventType = (String) body.get("eventType");
        String status = (String) body.get("status");
        Object spec = body.get("spec");
        return apiClient.modifySubscriptionEvent(eventType, status, spec);
    }

    @PostMapping("/modify-customer-url")
    public String modifyCustomerUrl(@RequestBody Map<String, String> body) {
        String action = body.get("action");
        String eventType = body.get("eventType");
        String url = body.get("url");
        return apiClient.modifyCustomerUrl(action, eventType, url);
    }

    @PostMapping("/query-ele-details")
    public String queryEleDetails(@RequestBody Map<String, Object> body) {
        Object spec = body.get("spec");
        return apiClient.queryEleDetails(spec);
    }

    @PostMapping("/modify-mapping-relation")
    public String modifyMappingRelation(@RequestBody Map<String, Object> body) {
        String action = (String) body.get("action");
        Object spec = body.get("spec");
        return apiClient.modifyMappingRelation(action, spec);
    }
}
