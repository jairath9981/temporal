package com.jairath.temporal.poc.impl.activity;

import com.jairath.temporal.poc.api.activity.TemporalGreetingActivities;
import org.springframework.stereotype.Component;


@Component
public class TemporalGreetingActivitiesImpl implements TemporalGreetingActivities {

    @Override
    public String composeGreeting(String name) {
        return "Hello " + name + "!";
    }
}
