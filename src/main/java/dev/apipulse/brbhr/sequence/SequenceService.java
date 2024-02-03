package dev.apipulse.brbhr.sequence;

import dev.apipulse.brbhr.sequence.EmpIdSequence;
import dev.apipulse.brbhr.sequence.EmpIdSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SequenceService {

    private final EmpIdSequenceRepository empIdSequenceRepository;

    @Autowired
    public SequenceService(EmpIdSequenceRepository empIdSequenceRepository) {
        this.empIdSequenceRepository = empIdSequenceRepository;
    }

    @Transactional
    public synchronized int getNextSequence(String sequenceId) {
        // Attempt to mitigate race conditions with synchronized and transactional
        EmpIdSequence sequence = empIdSequenceRepository.findById(sequenceId)
                .orElse(new EmpIdSequence(sequenceId, 1)); // Start with 0 if not present

        int nextValue = sequence.getSequenceValue() + 1;
        sequence.setSequenceValue(nextValue);
        empIdSequenceRepository.save(sequence); // Save the updated sequence

        return nextValue;
    }
}
