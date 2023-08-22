create database if not exists DOBOO_backend;
use DOBOO_backend;
-- drop table users;
-- drop table tracks;
drop table track_prices;
-- drop table assets;
create table  if not exists users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role varchar(30) NOT NULL,
    artsit_name varchar(50),
    dob VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users (name, password, dob, email,artsit_name,role)
VALUES ('thamim', 'thamim@8973','2004-12-07', 'thamim@gamil.com','thamim tommy','seller');

INSERT INTO users (name, password, dob, email,artsit_name,role)
VALUES ('tommy', 'tommy@8973','2001-12-07', 'goofy@gamil.com','travis scoot','seller');

INSERT INTO users (name, password, dob, email,artsit_name,role)
VALUES ('post', 'post@8973','1998-12-07', 'postDawg@gamil.com','post man','seller');

create table  if not exists tracks(
    id INT AUTO_INCREMENT PRIMARY KEY,
    track_name VARCHAR(50) NOT NULL,
    track_detail VARCHAR(200),
    bpm int,
    daw VARCHAR(50),
    genre VARCHAR(50),
    scale VARCHAR(50),
    user_id int NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO tracks (track_name, track_detail, bpm, daw,genre,scale,user_id)
VALUES ('sorry', 'this song is about a rock star',100, 'FL studio','Emo','C minor',1);

INSERT INTO tracks (track_name, track_detail, bpm, daw,genre,scale,user_id)
VALUES ('baby', 'this song is about a man who lost his life',180, 'Logic pro','Sad','A major',3);

INSERT INTO tracks (track_name, track_detail, bpm, daw,genre,scale,user_id)
VALUES ('party', 'feeling little low vibe with me',150, 'ableton','pop','E major',2);



CREATE TABLE IF NOT EXISTS track_prices(
    id INT AUTO_INCREMENT PRIMARY KEY,
    track_id INT NOT NULL,
    price int not null,
   `start_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`end_date`  TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (track_id) REFERENCES tracks(id)
);


INSERT INTO track_prices (track_id, price)
VALUES (1,200);

INSERT INTO track_prices (track_id, price)
VALUES (2,300);

INSERT INTO track_prices (track_id, price)
VALUES (3,10);

INSERT INTO track_prices (track_id, price)
VALUES (2,200);

create table  if not exists assets(
    id INT AUTO_INCREMENT PRIMARY KEY,
    track_id INT NOT NULL,
    FOREIGN KEY (track_id) REFERENCES tracks(id),
    `name` varchar (50) not null,
    extension varchar(50) not null,
    url varchar(500) not null
);


INSERT INTO assets (track_id, name,extension,url)
VALUES (1,'image','jpeg','https://open.spotify.com/artist/7genqfbfOirVIxcIDp97Pk');

INSERT INTO assets (track_id, name,extension,url)
VALUES (1,'audio','mp3','https://open.spotify.com/artist/7genqfbfOirVIxcIDp97Pk');

INSERT INTO assets (track_id, name,extension,url)
VALUES (2,'image','jpeg','https://open.spotify.com/artist/7genqfbfOirVIxcIDp97Pk');

INSERT INTO assets (track_id, name,extension,url)
VALUES (2,'audio','mp3','https://open.spotify.com/track/2WNHUp4s806SJrsj698ZX3?si=36842eb331714414');










