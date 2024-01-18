package dev.apipulse.brbhr.service;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<TrainingProgram> getAllTrainingPrograms() {
        return trainingRepository.findAll();
    }

    public TrainingProgram createTrainingProgram(TrainingProgram program) {
        return trainingRepository.save(program);
    }

    public TrainingProgram updateTrainingProgram(Long id, TrainingProgram programDetails) {
        TrainingProgram program = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Program not found"));
        // Update logic
        return trainingRepository.save(program);
    }

    public void deleteTrainingProgram(Long id) {
        TrainingProgram program = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Program not found"));
        trainingRepository.delete(program);
    }
}
