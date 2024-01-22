package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holidays {
    @Id
    private String id;
    String holidayName;
    LocalDate startDate;
    LocalDate endDate;

    Boolean recurring;
}
