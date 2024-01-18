package dev.apipulse.brbhr.controller;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    private final GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @PostMapping
    public ResponseEntity<Grievance> fileGrievance(@RequestBody Grievance grievance) {
        Grievance filedGrievance = grievanceService.fileGrievance(grievance);
        return new ResponseEntity<>(filedGrievance, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Grievance>> getGrievancesByEmployee(@PathVariable Long employeeId) {
        List<Grievance> grievances = grievanceService.getGrievancesByEmployee(employeeId);
        return ResponseEntity.ok(grievances);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grievance> updateGrievance(@PathVariable Long id, @RequestBody Grievance grievance) {
        Grievance updatedGrievance = grievanceService.updateGrievance(id, grievance);
        return ResponseEntity.ok(updatedGrievance);
    }
}
