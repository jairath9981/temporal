package com.jairath.temporal.poc.controller;


import com.jairath.temporal.poc.api.workflow.TemporalGreetingWorkflow;
import io.swagger.annotations.Api;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Scope
@Slf4j
@Validated
@CrossOrigin
@RequestMapping(value = "/jairath/temporal/poc/v1")
@Api
public class GreetingController {

    private final WorkflowClient workflowClient;

    public GreetingController() {
        this.workflowClient = WorkflowClient.newInstance(WorkflowServiceStubs.newInstance());
    }

    @GetMapping("/start")
    public String start(@RequestParam String name) {
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("GREETING_TASK_QUEUE")
                .build();

        TemporalGreetingWorkflow workflow =
                workflowClient.newWorkflowStub(TemporalGreetingWorkflow.class, options);
        return workflow.getGreeting(name);
    }
}
