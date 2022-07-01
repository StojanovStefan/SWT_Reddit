DROP DATABASE IF EXISTS SVT_Baza;
CREATE DATABASE SVT_Baza;

DROP TABLE IF EXISTS SVT_Baza.User;
CREATE TABLE SVT_Baza.User (
	user_id int AUTO_INCREMENT PRIMARY KEY,
	display_name nvarchar(40) DEFAULT NULL,
    username nvarchar(40) NOT NULL UNIQUE,
    password nvarchar(100) NOT NULL,
    email nvarchar(100) NOT NULL,
    avatar nvarchar(100) DEFAULT NULL,
    registration_date datetime DEFAULT NOW(),
    description nvarchar(100) DEFAULT NULL
);

DROP TABLE IF EXISTS SVT_Baza.Community;
CREATE TABLE SVT_Baza.Community (
	community_id int AUTO_INCREMENT PRIMARY KEY,
    name nvarchar(40) NOT NULL,
    description nvarchar(100) NOT NULL,
    creation_date datetime DEFAULT NOW(),
    is_suspended boolean DEFAULT FALSE,
    suspended_reason nvarchar(100) DEFAULT NULL
);

DROP TABLE IF EXISTS SVT_Baza.Moderator;
CREATE TABLE SVT_Baza.Moderator (
	user_id int,
    community_id int,
    PRIMARY KEY (user_id, community_id),
    CONSTRAINT FOREIGN KEY (community_id) REFERENCES Community(community_id),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES User(user_id)
);

DROP TABLE IF EXISTS SVT_Baza.Administrator;
CREATE TABLE SVT_Baza.Administrator (
	user_id int PRIMARY KEY,
	CONSTRAINT FOREIGN KEY (user_id) REFERENCES User(user_id)
);

DROP TABLE IF EXISTS SVT_Baza.Post;
CREATE TABLE SVT_Baza.Post (
	post_id int AUTO_INCREMENT PRIMARY KEY,
	user_id int NOT NULL,
	community_id int NOT NULL,
    title nvarchar(100) DEFAULT NULL,
    text nvarchar(200) DEFAULT NULL,
    creation_date datetime DEFAULT NOW(),
    image_path nvarchar(200) DEFAULT NULL,
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES User(user_id),
    CONSTRAINT FOREIGN KEY (community_id) REFERENCES Community(community_id)
);

DROP TABLE IF EXISTS SVT_Baza.Reaction;
CREATE TABLE SVT_Baza.Reaction (
    reaction_id int AUTO_INCREMENT PRIMARY KEY,
    user_id int NOT NULL,
    post_id int NOT NULL,
	type varchar(8) NOT NULL DEFAULT 'LIKE',
	timestamp datetime DEFAULT NOW(),
	CONSTRAINT FOREIGN KEY (user_id) REFERENCES SVT_Baza.User(user_id),
	CONSTRAINT FOREIGN KEY (post_id) REFERENCES SVT_Baza.Post(post_id),
    CONSTRAINT CK_REACTION_VALID_TYPE CHECK (type = 'LIKE' OR type = 'DISLIKE')
);