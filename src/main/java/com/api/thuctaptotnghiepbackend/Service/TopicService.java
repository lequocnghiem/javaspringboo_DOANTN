package com.api.thuctaptotnghiepbackend.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.thuctaptotnghiepbackend.Entity.Topic;


public interface  TopicService {
     Topic createTopic(Topic topic);

    Topic getTopicById(Long topicId);

    Page<Topic> getAllTopics(Pageable pageable);

    Topic updateTopic(Topic topic);

    void deleteTopic(Long topicId);
}
