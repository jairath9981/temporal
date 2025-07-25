package com.jairath.temporal.poc.impl.workflow;


import com.jairath.temporal.poc.api.activity.TemporalGreetingActivities;
import com.jairath.temporal.poc.api.workflow.TemporalGreetingWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.springframework.stereotype.Component;

import java.time.Duration;

// do not use @Component here
/*

As, it will be Called from non workflow or workflow callback thread

it will be called from
register the workflow class with Temporal using:
        Worker worker = factory.newWorker("GREETING_TASK_QUEUE");

        // Register workflow and activity implementations
        worker.registerWorkflowImplementationTypes(TemporalGreetingWorkflowImpl.class);
 */
//@Component
public class TemporalGreetingWorkflowImpl implements TemporalGreetingWorkflow {

    /*
     * At least one of the following options needs to be defined:
     * - setStartToCloseTimeout
     * - setScheduleToCloseTimeout
     */
    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(60))
            .build();

    /*
     * Define the HelloWorldActivity stub. Activity stubs are proxies for activity invocations that
     * are executed outside of the workflow thread on the activity worker, that can be on a
     * different host. Temporal is going to dispatch the activity results back to the workflow and
     * unblock the stub as soon as activity is completed on the activity worker.
     *
     * The activity options that were defined above are passed in as a parameter.
     */
    private final TemporalGreetingActivities activity =
            Workflow.newActivityStub(TemporalGreetingActivities.class, options);

    @Override
    public String getGreeting(String name) {
        return activity.composeGreeting(name);
    }
}
