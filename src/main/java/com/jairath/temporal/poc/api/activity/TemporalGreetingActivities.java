package com.jairath.temporal.poc.api.activity;


import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;


@ActivityInterface
public interface TemporalGreetingActivities {

    // Define your activity methods which can be called during workflow execution
    @ActivityMethod
    String composeGreeting(String name);
}
