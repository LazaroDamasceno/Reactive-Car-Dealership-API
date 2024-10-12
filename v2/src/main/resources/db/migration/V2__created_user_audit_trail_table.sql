CREATE TABLE USER_AUDIT_TRAIL(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    ssn VARCHAR(9) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(255) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_at_zone VARCHAR(255) NOT NULL
)