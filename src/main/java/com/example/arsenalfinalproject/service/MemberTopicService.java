package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.service.TopicHistoryServiceModel;
import com.example.arsenalfinalproject.model.view.MemberTopicView;

import java.io.IOException;
import java.util.List;

public interface MemberTopicService {
    void initializeTopicMember() throws IOException;

    List<MemberTopicView> getMemberTopicAllLimit3();

    void addTopicHistoryUser(TopicHistoryServiceModel topicHistoryServiceModel, String currentUser) throws IOException;
}
