CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    family_name VARCHAR(100),
    email VARCHAR(100),
    identity VARCHAR(50),
    address VARCHAR(200),
    city VARCHAR(100),
    province VARCHAR(100),
    postal_code VARCHAR(20)
);