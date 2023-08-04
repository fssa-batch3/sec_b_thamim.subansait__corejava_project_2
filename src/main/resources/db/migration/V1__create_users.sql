create database if not exists DOBOO_backend;
use DOBOO_backend;

create table  if not exists users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    artsit_name varchar(50),
    dob VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table  if not exists tracks(
    id INT AUTO_INCREMENT PRIMARY KEY,
    track_name VARCHAR(50) NOT NULL,
    track_detail VARCHAR(200),
    bpm VARCHAR(50),
    daw VARCHAR(50),
    genre VARCHAR(50),
    scale VARCHAR(50),
    user_id int NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS track_prices(
    id INT AUTO_INCREMENT PRIMARY KEY,
    track_id INT NOT NULL,
    price int not null,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (track_id) REFERENCES tracks(id)
);

create table  if not exists assets(
    id INT AUTO_INCREMENT PRIMARY KEY,
    track_id INT NOT NULL unique,
    FOREIGN KEY (track_id) REFERENCES tracks(id),
    `name` varchar (50) not null,
    extension varchar(50) not null,
    url varchar(500) not null
);





