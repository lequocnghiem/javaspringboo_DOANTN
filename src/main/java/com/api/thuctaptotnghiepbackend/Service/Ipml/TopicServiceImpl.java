package com.api.thuctaptotnghiepbackend.Service.Ipml;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.thuctaptotnghiepbackend.Entity.Topic;
import com.api.thuctaptotnghiepbackend.Repository.Topic.TopicRepository;
import com.api.thuctaptotnghiepbackend.Service.TopicService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService { // Rename the class

    private TopicRepository topicRepository; // Rename the repository

    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Topic getTopicById(Long topicId) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        return optionalTopic.orElse(null);
    }

    @Override
    public Page<Topic> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        Topic existingTopic = topicRepository.findById(topic.getId()).orElse(null);
        if (existingTopic != null) {
            existingTopic.setName(topic.getName());
            existingTopic.setSlug(topic.getSlug());
         
        
            existingTopic.setCreatedAt(topic.getCreatedAt());
            existingTopic.setUpdatedAt(topic.getUpdatedAt());
            
            existingTopic.setStatus(topic.getStatus());

            Topic updatedTopic = topicRepository.save(existingTopic);
            return updatedTopic;
        }
        return null;
    }

    @Override
    public void deleteTopic(Long topicId) {
        topicRepository.deleteById(topicId);
    }
}