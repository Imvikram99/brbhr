package dev.apipulse.brbhr.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OKRResult {
    @Id
    String id;
    String title;
    String description;

    OKRResultType okrResultType;

    Integer currentValue;
    Integer targetValue;

    PeriodType periodType;

    LocalDate startDate;

    LocalDate endDate;

}
