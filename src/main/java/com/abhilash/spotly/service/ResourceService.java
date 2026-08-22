package com.abhilash.spotly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhilash.spotly.entity.Resource;
import com.abhilash.spotly.repository.ResourceRepository;

@Service
public class ResourceService {
       private final ResourceRepository resourceRepository;

       public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
       }

       public List<Resource> getAllResources() {
        return resourceRepository.findAll();
       }

       public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElse(null);
       }

       public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
       }
}