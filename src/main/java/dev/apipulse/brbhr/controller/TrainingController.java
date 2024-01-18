package dev.apipulse.brbhr.controller;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingProgram>> getAllTrainingPrograms() {
        List<TrainingProgram> programs = trainingService.getAllTrainingPrograms();
        return ResponseEntity.ok(programs);
    }

    @PostMapping
    public ResponseEntity<TrainingProgram> createTrainingProgram(@RequestBody TrainingProgram program) {
        TrainingProgram createdProgram = trainingService.createTrainingProgram(program);
        return new ResponseEntity<>(createdProgram, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingProgram> updateTrainingProgram(@PathVariable Long id, @RequestBody TrainingProgram program) {
        TrainingProgram updatedProgram = trainingService.updateTrainingProgram(id, program);
        return ResponseEntity.ok(updatedProgram);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingProgram(@PathVariable Long id) {
        trainingService.deleteTrainingProgram(id);
        return ResponseEntity.noContent().build();
    }
}
