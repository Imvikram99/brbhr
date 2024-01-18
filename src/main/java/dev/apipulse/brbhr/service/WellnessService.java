package dev.apipulse.brbhr.service;

@Service
public class WellnessService {

    private final WellnessActivityRepository wellnessActivityRepository;
    private final WellnessProgramRepository wellnessProgramRepository;

    public WellnessService(WellnessActivityRepository wellnessActivityRepository, WellnessProgramRepository wellnessProgramRepository) {
        this.wellnessActivityRepository = wellnessActivityRepository;
        this.wellnessProgramRepository = wellnessProgramRepository;
    }

    public List<WellnessActivity> getActivitiesByEmployee(Long employeeId) {
        return wellnessActivityRepository.findByEmployeeId(employeeId);
    }

    public WellnessActivity logActivity(WellnessActivity activity) {
        return wellnessActivityRepository.save(activity);
    }

    public List<WellnessProgram> getAllWellnessPrograms() {
        return wellnessProgramRepository.findAll();
    }
}
