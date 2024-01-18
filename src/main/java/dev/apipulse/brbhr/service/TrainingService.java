package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.TrainingProgram;
import dev.apipulse.brbhr.repository.TrainingRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public TrainingProgram updateTrainingProgram(String id, TrainingProgram programDetails) {
        TrainingProgram program = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Program not found"));
        // Update logic
        return trainingRepository.save(program);
    }

    public void deleteTrainingProgram(String id) {
        TrainingProgram program = trainingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Program not found"));
        trainingRepository.delete(program);
    }
}
