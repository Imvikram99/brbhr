package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeDetail {

    String name;
    String icon;
    String color;
    String paymentMethod;
    Integer leaveCount;
    String periodIn;
    Integer totalDays;

    Boolean isResetAble;

    String reSetBased;

    String carryForwardType;

    Boolean isRequireApproval;

    Boolean isExcludeCompanyLeaves;

    Boolean isExcludeHolidays;

    String leaveType;
}
