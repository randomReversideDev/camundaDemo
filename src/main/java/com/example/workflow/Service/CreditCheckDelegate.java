package com.example.workflow.Service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CreditCheckDelegate implements JavaDelegate {

    private final NotificationDelegate notificationDelegate;

    // Constructor injection for NotificationDelegate
    public CreditCheckDelegate(NotificationDelegate notificationDelegate) {
        this.notificationDelegate = notificationDelegate;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer creditScore = (Integer) execution.getVariable("creditScore");
        boolean creditApproved = creditScore >= 650;
        execution.setVariable("creditApproved", creditApproved);

        // Log notification instead of sending email
        String applicantEmail = (String) execution.getVariable("applicantEmail");
        if (creditApproved) {
            notificationDelegate.sendNotification(applicantEmail, "Credit Check Passed", "Your credit check passed successfully.");
        } else {
            notificationDelegate.sendNotification(applicantEmail, "Credit Check Failed", "Unfortunately, your credit score did not meet the required threshold.");
        }
    }
}
