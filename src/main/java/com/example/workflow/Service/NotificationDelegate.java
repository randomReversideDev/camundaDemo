package com.example.workflow.Service;

import org.springframework.stereotype.Service;

@Service
public class NotificationDelegate {

    public void sendNotification(String to, String subject, String message) {
        // Simulated email sending by printing to the console
        System.out.println("Notification sent to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
