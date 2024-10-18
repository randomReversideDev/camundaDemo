package com.example.workflow.Service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SalaryCheckDelegate implements JavaDelegate {

    private final NotificationDelegate notificationDelegate;

    // Constructor injection for NotificationDelegate
    public SalaryCheckDelegate(NotificationDelegate notificationDelegate) {
        this.notificationDelegate = notificationDelegate;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Double salary = (Double) execution.getVariable("salary");
        boolean salaryApproved = salary >= 30000;
        execution.setVariable("salaryApproved", salaryApproved);

        // Log notification instead of sending email
        String applicantEmail = (String) execution.getVariable("applicantEmail");
        if (salaryApproved) {
            notificationDelegate.sendNotification(applicantEmail, "Salary Check Passed", "You meet the salary threshold for loan approval.");
        } else {
            notificationDelegate.sendNotification(applicantEmail, "Salary Check Failed", "Unfortunately, your salary does not meet the required threshold.");
        }
    }
}
