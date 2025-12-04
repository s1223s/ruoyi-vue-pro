package cn.iocoder.yudao.module.elevator.service;

import org.springframework.stereotype.Service;

@Service
public class ElevatorServiceImpl implements ElevatorService {

    @Override
    public void handlePushMessage(String message) {
        // TODO: Implement the business logic to handle the push message.
        System.out.println("Handling push message: " + message);
    }

}
