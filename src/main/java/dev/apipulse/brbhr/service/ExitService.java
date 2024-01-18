package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.ExitProcess;
import dev.apipulse.brbhr.repository.ExitProcessRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExitService {

    private final ExitProcessRepository exitProcessRepository;

    public ExitService(ExitProcessRepository exitProcessRepository) {
        this.exitProcessRepository = exitProcessRepository;
    }

    public ExitProcess initiateExitProcess(ExitProcess exitProcess) {
        return exitProcessRepository.save(exitProcess);
    }

    public ExitProcess getExitDetails(String employeeId) {
        return exitProcessRepository.findByEmployeeId(employeeId);
    }
}
