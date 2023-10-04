DROP TABLE IF EXISTS post_test;
DROP TABLE IF EXISTS post_tags;
DROP TABLE IF EXISTS reply_comments;
DROP TABLE IF EXISTS medical_check;
DROP TABLE IF EXISTS calender;

CREATE TABLE IF NOT EXISTS post_test (
    id SERIAL NOT NULL,
    tag_code int NOT NULL,
    title varchar(50) NOT NULL,
    post varchar(1000) NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS post_tags (
    id int NOT NULL,
    tag varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS reply_comments (
    id SERIAL NOT NULL,
    post_id int NOT NULL,
    comments varchar(1000) NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS medical_check (
	id SERIAL NOT NULL,
    user_id int NOT NULL,
    year int NOT NULL,
    pdf_path varchar(255) NOT NULL default 'default',
    picture_path varchar(255) NOT NULL default 'default',
    url_path varchar(255) NOT NULL default 'default',
    created_date timestamp NOT NULL,
    updated_date timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS calendar (
	id SERIAL NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	title varchar(20) NOT NULL,
	comment varchar(500) NOT NULL,
    created_date timestamp NOT NULL,
    updated_date timestamp NOT NULL,
    PRIMARY KEY (id)
);