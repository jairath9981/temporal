package com.jairath.temporal.poc;


import com.jairath.temporal.poc.impl.activity.TemporalGreetingActivitiesImpl;
import com.jairath.temporal.poc.impl.workflow.TemporalGreetingWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/*
Listens to a Task Queue
Executes workflows and activities when Temporal server assigns work
Runs indefinitely like a backend service
 */
@Component
public class WorkerStarter implements CommandLineRunner {

    @Override
    public void run(String... args) {

        // Connect to Temporal service
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Create worker
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("GREETING_TASK_QUEUE");

        // Register workflow and activity implementations
        worker.registerWorkflowImplementationTypes(TemporalGreetingWorkflowImpl.class);
        worker.registerActivitiesImplementations(new TemporalGreetingActivitiesImpl());

        // Start listening
        factory.start();  // <-- Starts polling the task queue
    }
}


