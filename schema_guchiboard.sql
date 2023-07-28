DROP TABLE IF EXISTS post_test;
DROP TABLE IF EXISTS post_tags;

CREATE TABLE IF NOT EXISTS post_test (
    id SERIAL NOT NULL,
    title varchar(50) NULL,
    post varchar(1000) NULL,
    created_date timestamp NULL,
    updated_date timestamp NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS post_tags (
    id int NOT NULL,
    tag varchar(20) NULL,
    PRIMARY KEY (id)
);