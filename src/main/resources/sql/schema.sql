CREATE TABLE IF NOT EXISTS members
(
    member_id  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    login_id                           VARCHAR(255),
    member_name                        VARCHAR(255),
    password                           VARCHAR(255),
    role                               VARCHAR(100),
    change_password_at timestamp default now(),
    created_at                  TIMESTAMP DEFAULT NOW(),
    updated_at                  TIMESTAMP
);
INSERT INTO members (login_id,member_name, password, role, created_at, updated_at) VALUES ('aegis','김기술', '$2a$10$cFhlePW3BLG6awWXlPE6PuRQRW9E9EOQJ/vuWzvrgS3gi4eTEgIsG', 'ADMIN', '2024-01-29 13:42:50.976319', '2024-01-29 13:42:50.976319');
