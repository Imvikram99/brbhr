package dev.apipulse.brbhr.ai;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String model;
    @JsonIgnore
    private long created;
    @JsonIgnore
    private String object;
    private List<Choice> choices;

    // Constructors, Getters, and Setters
}
