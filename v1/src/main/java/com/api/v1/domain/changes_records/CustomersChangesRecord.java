package com.api.v1.domain.changes_records;

import com.api.v1.domain.customers.Customers;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "customers_changes_record")
@Getter
public class CustomersChangesRecord {

    @Id
    private UUID id;
    private Customers customer;

    public CustomersChangesRecord(Customers customer) {
        this.id = UUID.randomUUID();
        this.customer = customer;
    }

}
