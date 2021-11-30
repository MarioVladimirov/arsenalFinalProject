package com.example.arsenalfinalproject.model.view;

import java.util.Map;

public class StatsView {

    private final int authRequests;
    private final int anonRequests;
    private final Map<String, Integer> allRequestsName;

    public StatsView(int authRequests, int anonRequests, Map<String, Integer> allRequestsName) {
        this.authRequests = authRequests;
        this.anonRequests = anonRequests;
        this.allRequestsName = allRequestsName;
    }

    public int getTotalRequests() {
        return authRequests + anonRequests;
    }

    public int getAuthRequests() {
        return authRequests;
    }

    public int getAnonRequests() {
        return anonRequests;
    }

    public Map<String, Integer> getAllRequestsName() {
        return allRequestsName;
    }
}
