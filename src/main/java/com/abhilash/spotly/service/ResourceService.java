package com.abhilash.spotly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhilash.spotly.dto.CreateResourceRequest;
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

       public Resource createResource(CreateResourceRequest createResourceRequest) {
        Resource resource = new Resource();
        resource.setName(createResourceRequest.getName());
        resource.setDescription(createResourceRequest.getDescription());
        return resourceRepository.save(resource);
       }
}