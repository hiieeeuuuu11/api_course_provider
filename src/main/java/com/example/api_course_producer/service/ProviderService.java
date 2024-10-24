package com.example.api_course_producer.service;

import com.example.api_course_producer.entity.course.Provider;
import com.example.api_course_producer.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

  private final ProviderRepository ProviderRepository;

  @Autowired
  public ProviderService(ProviderRepository ProviderRepository) {
    this.ProviderRepository = ProviderRepository;
  }

  public List<Provider> getAllProviders() {
    return ProviderRepository.findAll();
  }

  public Provider getProviderById(int id) {
    return ProviderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Provider not found"));
  }

  public Provider createProvider(Provider provider) {

    return ProviderRepository.save(provider);
  }

  public Provider updateProvider(Provider updatedProvider) {
    Provider existingProvider =
        ProviderRepository.findById(updatedProvider.getId())
            .orElseThrow(() -> new RuntimeException("Provider not found"));
    if (existingProvider != null) {
      existingProvider.setName(updatedProvider.getName());
      existingProvider.setDescription(updatedProvider.getDescription());
      existingProvider.setWebsite(updatedProvider.getWebsite());
      return ProviderRepository.save(existingProvider);
    }
    return null;
  }

  public void deleteProvider(int id) {
    Provider provider =
        ProviderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Provider not found"));
    if (provider != null) {
      ProviderRepository.delete(provider);
    }
  }
}
