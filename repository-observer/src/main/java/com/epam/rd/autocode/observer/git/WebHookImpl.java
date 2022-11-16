package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class WebHookImpl implements WebHook {
    private final Event.Type type;
    private final List<Event> events;
    private final String branchName;

    public WebHookImpl(Event.Type type, String branchName) {
        this.type = type;
        this.branchName = branchName;
        this.events = new ArrayList<>();
    }

    @Override
    public String branch() {
        return branchName;
    }

    @Override
    public Event.Type type() {
        return type;
    }

    @Override
    public List<Event> caughtEvents() {
        return new ArrayList<>(events);
    }

    @Override
    public void onEvent(Event event) {
        this.events.add(event);
    }
}