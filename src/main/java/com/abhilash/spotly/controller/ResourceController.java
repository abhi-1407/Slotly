package com.abhilash.spotly.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abhilash.spotly.dto.CreateResourceRequest;
import com.abhilash.spotly.entity.Resource;
import com.abhilash.spotly.service.ResourceService;

@RestController
@RequestMapping("/resources")
public class ResourceController {
       private final ResourceService resourceService;

       public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
       }

       @GetMapping
       public List<Resource> getAllResources() {
        return resourceService.getAllResources();
       }

       @PostMapping
       public Resource createResource(@RequestBody CreateResourceRequest createResourceRequest) {
        return resourceService.createResource(createResourceRequest);
       }
}