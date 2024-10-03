package com.api.v1.domain.audit_trail;

import com.api.v1.domain.customer.Customer;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "customer_audit_trail")
@Getter
public class CustomerChangesRecord {

    @Id
    private UUID id;
    private Customer customer;

    public CustomerChangesRecord(Customer customer) {
        this.id = UUID.randomUUID();
        this.customer = customer;
    }

}
