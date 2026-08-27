package com.Shlok.Booking_System.service;

import com.Shlok.Booking_System.entity.Resource;
import com.Shlok.Booking_System.repository.ResourceRepository;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;


    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> resources(){
        return resourceRepository.findAll();
    }

    public Resource resourceById(Long id){
        return resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource Not Found"));
    }

    public Resource createResource(Resource resource){
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long id,Resource resource){
        Resource updatedResource=resourceRepository.findById(id).orElseThrow(()->new RuntimeException("Resource Not Found"));
        updatedResource.setName(resource.getName());

        updatedResource.setDescription(resource.getDescription());

        return resourceRepository.save(updatedResource);
    }

    public void deleteResource(Long id) {
        Resource exsitingResource=resourceById(id);
         resourceRepository.delete(exsitingResource);
    }


}
