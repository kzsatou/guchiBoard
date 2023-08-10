DROP TABLE IF EXISTS post_test;
DROP TABLE IF EXISTS post_tags;
DROP TABLE IF EXISTS reply_comments;

CREATE TABLE IF NOT EXISTS post_test (
    id SERIAL NOT NULL,
    tag_code int NOT NULL,
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

CREATE TABLE IF NOT EXISTS reply_comments (
    id SERIAL NOT NULL,
    post_id int NOT NULL,
    comments varchar(1000) NULL,
    created_date timestamp NULL,
    updated_date timestamp NULL,
    PRIMARY KEY (id)
);