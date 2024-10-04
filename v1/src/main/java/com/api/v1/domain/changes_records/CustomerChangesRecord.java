package com.api.v1.domain.changes_records;

import com.api.v1.domain.customers.Customer;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "customer_changes_record")
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
