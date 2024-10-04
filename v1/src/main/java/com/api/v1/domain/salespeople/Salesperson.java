package com.api.v1.domain.salespeople;

import com.api.v1.domain.users.User;
import com.api.v1.utils.salespeople.EmployeeIdGeneratorUtil;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Document(collection = "v1_salesperson")
public class Salesperson {

    @Id
    private UUID id;
    private BigInteger employeeId;
    private User user;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    public Salesperson(User user) {
        id = UUID.randomUUID();
        employeeId = EmployeeIdGeneratorUtil.generate();
        this.user = user;
        createdAt = Instant.now();
        createdAtZone = ZoneId.systemDefault();
    }

    public void modify(User user) {
        this.user = user;
        modifiedAt = Instant.now();
        modifiedAtZone = ZoneId.systemDefault();
    }

}
