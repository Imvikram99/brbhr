package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    private String id;
    JobApplication jobApplication;
}
