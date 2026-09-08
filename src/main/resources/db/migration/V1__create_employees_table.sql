CREATE SCHEMA IF NOT EXISTS employee_schema;

CREATE TABLE employee_schema.employees (
                                           id BIGSERIAL PRIMARY KEY,
                                           name VARCHAR(100),
                                            age INTEGER,
                                            department VARCHAR(100),
                                            salary DOUBLE PRECISION

);