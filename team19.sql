/*
drop database team19;
CREATE DATABASE team19 CHARACTER SET utf8 COLLATE utf8_general_ci;
use team19
source C:\Program Files\Tomcat\apache-tomcat-7.0.35\webapps\team19\team19.sql
source C:\Program Files\Tomcat\apache-tomcat-7.0.35\webapps\team19\team19data.sql
*/

DROP TABLE IF EXISTS comments; 
DROP TABLE IF EXISTS teaches;
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS courseLocations;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS venues;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS lecturers;
DROP TABLE IF EXISTS admins;

DROP TABLE IF EXISTS students;

CREATE TABLE students
(
    studentId INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    passwordHash CHAR(32),
    addressLine1 VARCHAR(30),
    addressLine2 VARCHAR(30),
    addressLine3 VARCHAR(30),
    country VARCHAR(30),
    telephone VARCHAR(20), 
    dateOfBirth DATE,
    gender CHAR(1),
    authenticated BOOLEAN,
    authString CHAR(36),
    dateRegistered DATETIME,
    cookieToken CHAR(36),
    emailOptIn Boolean,
    PRIMARY KEY(studentId)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS lecturers; 

CREATE TABLE lecturers 
(
    lecturerId INT NOT NULL AUTO_INCREMENT, 
    firstName VARCHAR(30) NOT NULL, 
    lastName VARCHAR(30) NOT NULL, 
    email VARCHAR(50), 
    lecturerTitle VARCHAR(20), 
    position VARCHAR(100), 
    addressLine1 VARCHAR(30), 
    addressLine2 VARCHAR(30), 
    addressLine3 VARCHAR(30), 
    country VARCHAR(30), 
    telephone VARCHAR(20), 
    PRIMARY KEY(lecturerId) 
) ENGINE=InnoDB; 

DROP TABLE IF EXISTS admins; 

CREATE TABLE admins 
(
    adminName VARCHAR(30),
    adminPassword VARCHAR(30),
    email VARCHAR(30),
    PRIMARY KEY(adminName) 
) ENGINE=InnoDB; 

DROP TABLE IF EXISTS venues; 

CREATE TABLE venues 
(
    venueId INT NOT NULL AUTO_INCREMENT,
    venueRoom VARCHAR(30),
    venueBuilding VARCHAR(30),
    addressLine1 VARCHAR(30), 
    addressLine2 VARCHAR(30), 
    addressLine3 VARCHAR(30),
    capacity INT,
    onCampus BOOLEAN,
    PRIMARY KEY(venueId) 
) ENGINE=InnoDB;

DROP TABLE IF EXISTS courses; 

CREATE TABLE courses 
(
    courseId INT NOT NULL AUTO_INCREMENT,
    courseTitle VARCHAR(100),
    fee DOUBLE,
    spaces INT,
    courseCategory VARCHAR(30),
    content TEXT,
    enrollStartDate DATE,
    enrollEndDate DATE,
    courseStartDate DATE,
    courseEndDate DATE,
    PRIMARY KEY(courseId) 
) ENGINE=InnoDB;

DROP TABLE IF EXISTS teaches; 

CREATE TABLE teaches 
(
    courseId INT NOT NULL,
    lecturerId INT NOT NULL,
    PRIMARY KEY (courseId, lecturerId),
    FOREIGN KEY(courseId) REFERENCES courses(courseId),
    FOREIGN KEY(lecturerId) REFERENCES lecturers(lecturerId)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS enrollments; 

CREATE TABLE enrollments 
(
    courseId INT,
    studentId INT,
    pending BOOLEAN,
    paidDeposit BOOLEAN,
    paidFee BOOLEAN,
    issuedRefund BOOLEAN,
    enrolldate DATETIME,
    PRIMARY KEY (courseId, studentId),
    FOREIGN KEY(courseId) REFERENCES courses(courseId),
    FOREIGN KEY(studentId) REFERENCES students(studentId)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS courseLocations; 

CREATE TABLE courseLocations 
(
    courseId INT NOT NULL,
    venueId INT NOT NULL,
    PRIMARY KEY (courseId, venueId),
    FOREIGN KEY(courseId) REFERENCES courses(courseId),
    FOREIGN KEY(venueId) REFERENCES venues(venueId)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS comments; 

CREATE TABLE comments 
(
    commentId INT NOT NULL AUTO_INCREMENT,
    studentId INT NOT NULL,
    subject VARCHAR(100),
    messageText TEXT,
    reviewed BOOLEAN,
    PRIMARY KEY (commentId),
    FOREIGN KEY(studentId) REFERENCES students(studentId)
) ENGINE=InnoDB;
