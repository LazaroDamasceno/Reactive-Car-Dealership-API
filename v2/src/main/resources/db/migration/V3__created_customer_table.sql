CREATE TABLE V2_CUSTOMERS(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    address VARCHAR(255) NOT NULL,
    user_fk UUID REFERENCES v2_users(id),
    created_at TIMESTAMP NOT NULL,
    created_at_zone VARCHAR(255) NOT NULL,
    modified_at TIMESTAMP,
    modified_at_zone VARCHAR(255)
)