package com.example.arsenalfinalproject.service.impl;

import com.example.arsenalfinalproject.model.view.StatsView;
import com.example.arsenalfinalproject.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class StatsServiceImpl implements StatsService {

    private int anonymousRequests, authRequests;
    private final Map<String, Integer> allRequestsName = new HashMap<>();


    @Override
    public void onRequest() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();


        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authRequests++;
            String currentName = ((UserDetails) authentication.getPrincipal()).getUsername();
            if (!allRequestsName.containsKey(currentName)) {

                allRequestsName.put(currentName, 0);
            }

            if (allRequestsName.containsKey(currentName)) {
                allRequestsName.replace(currentName, allRequestsName.get(currentName) + 1);
            }

        } else {
            anonymousRequests++;
        }


    }

    @Override
    public StatsView getStats() {
        return new StatsView(authRequests, anonymousRequests, allRequestsName);
    }

    @Override
    public void resetHistory() {
        System.out.println("Reset History");
        authRequests = 0;
        anonymousRequests = 0;
        allRequestsName.clear();
    }
}

