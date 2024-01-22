CREATE TABLE IF NOT EXISTS member
(
    id  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    member_name                      VARCHAR(255),
    password                     VARCHAR(255),
    role                VARCHAR(100),
    created_at                  TIMESTAMP DEFAULT NOW(),
    updated_at                  TIMESTAMP
);
