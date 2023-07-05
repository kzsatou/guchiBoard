DROP TABLE IF EXISTS post_test;

CREATE TABLE IF NOT EXISTS post_test (
    id varchar(255) NOT NULL,
    title varchar(50) NULL,
    post varchar(1000) NULL,
    created_date timestamp NULL,
    updated_date timestamp NULL,
    PRIMARY KEY (id)
);