CREATE DATABASE IF NOT EXISTS crm;
use crm;

CREATE TABLE lead_tracker (
    id VARCHAR(32) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    status VARCHAR(16) NOT NULL,
    created_at DATETIME2(6) NOT NULL,
    updated_at DATETIME2(6) NOT NULL,
    received_at DATETIME2(6) NOT NULL DEFAULT SYSDATETIME(),
    INDEX lead_tracker_id_key (id)
);
