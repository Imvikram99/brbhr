package dev.apipulse.brbhr.controller;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Asset>> getAssetsByEmployee(@PathVariable Long employeeId) {
        List<Asset> assets = assetService.getAssetsByEmployee(employeeId);
        return ResponseEntity.ok(assets);
    }

    @PostMapping
    public ResponseEntity<Asset> assignAsset(@RequestBody Asset asset) {
        Asset assignedAsset = assetService.assignAsset(asset);
        return new ResponseEntity<>(assignedAsset, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        Asset updatedAsset = assetService.updateAsset(id, asset);
        return ResponseEntity.ok(updatedAsset);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deallocateAsset(@PathVariable Long id) {
        assetService.deallocateAsset(id);
        return ResponseEntity.noContent().build();
    }
}
