package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.Asset;
import dev.apipulse.brbhr.repository.AssetRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public List<Asset> getAssetsByEmployee(String employeeId) {
        return assetRepository.findByEmployeeId(employeeId);
    }

    public Asset assignAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset updateAsset(String id, Asset assetDetails) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        // Update logic
        return assetRepository.save(asset);
    }

    public void deallocateAsset(String id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        assetRepository.delete(asset);
    }
}
