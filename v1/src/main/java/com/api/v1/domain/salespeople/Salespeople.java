package com.api.v1.domain.salespeople;

import com.api.v1.domain.users.Users;
import com.api.v1.utils.salespeople.EmployeeIdGeneratorUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Document(collection = "v1_salesperson")
@NoArgsConstructor
public class Salespeople {

    @Id
    private UUID id;
    private BigInteger employeeId;
    private Users user;
    private Instant createdAt;
    private ZoneId createdAtZone;
    private Instant modifiedAt;
    private ZoneId modifiedAtZone;

    private Salespeople(Users user) {
        id = UUID.randomUUID();
        employeeId = EmployeeIdGeneratorUtil.generate();
        this.user = user;
        createdAt = Instant.now();
        createdAtZone = ZoneId.systemDefault();
    }

    public static Salespeople create(Users user) {
        return new Salespeople(user);
    }

    public void modify(Users user) {
        this.user = user;
        modifiedAt = Instant.now();
        modifiedAtZone = ZoneId.systemDefault();
    }

}
