package dev.apipulse.brbhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CompoundIndex(def = "{'jobSeekerId': 1, 'jobId': 1}", unique = true, name = "jobSeekerId_jobId_idx")
public class JobApplicants {
    @Id
    private String id;
    @Field("jobSeekerId")
    String jobSeekerId;
    @Field("jobId")
    String jobId;
}

