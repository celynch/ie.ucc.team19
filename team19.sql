/*
drop database team19;
CREATE DATABASE team19 CHARACTER SET utf8 COLLATE utf8_general_ci;
use team19
source C:\Program Files\Tomcat\apache-tomcat-7.0.35\webapps\team19\team19.sql
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

INSERT INTO students (studentID, firstName, lastName, email, passwordHash, addressLine1, addressLine2, addressLine3, country,  telephone, dateOfBirth, gender, authenticated, authString, dateRegistered, cookieToken, emailOPtIn)
VALUES
    (NULL, 'Cormac','Lynch','celynch@gmail.com','123','1 A Street','B-Town','C-City','Ireland','086-1234567','1981-01-01','M',true,'072d-31bbd0e-d3f582ae-340f0e39-f73e8','2013-02-24 16:30:00', '072d-31bbd0e-d3f582ae-340f0e39-f73e8', true),
    (NULL, 'Aaron','Aaronson','101664280@umail.ucc.ie','123','1 A Street','B-Town','C-City','Ireland','086-1234567','1981-01-01','M',true,'072d-31bbd0e-d3f582ae-340f0e39-f73e8','2013-02-24 16:30:00', '072d-31bbd0e-d3f582ae-340f0e39-f73e8', true)
;

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

INSERT INTO lecturers (firstName, lastName, email, lecturerTitle, position, addressLine1, addressLine2, addressLine3, country, telephone)
VALUES 
    ('Luke', 'Manning', 'lmanning@gmail.com', '', 'Project Manager, Team 19', '2 A Street', 'B-Town', 'C-City', 'Ireland', '086-1234567'), 
    ('Eoghan', 'Healy', 'ehealy@gmail.com', 'Dr', 'Software Engineering Lecturer', '3 C Street', 'A-Town', 'B-City', 'Ireland', '420-5913');

DROP TABLE IF EXISTS admins; 

CREATE TABLE admins 
(
    adminName VARCHAR(30),
    adminPassword VARCHAR(30),
    email VARCHAR(30),
    PRIMARY KEY(adminName) 
) ENGINE=InnoDB; 

INSERT INTO admins (adminName, adminPassword, email)
VALUES 
    ('mindaugas', 'rakauskas', 'mrakauskas@gmail.com') 
;

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

INSERT INTO venues (venueRoom, venueBuilding, addressLine1, addressLine2, addressLine3, capacity, onCampus)
VALUES 
    ('G01', 'Western Gateway Building', 'UCC', 'Western Road', 'Cork', 60, false),
    ('Aula Maxima', 'North Wing', 'UCC', 'Western Road', 'Cork', 100, true)
;

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

INSERT INTO courses (courseTitle, fee, spaces, courseCategory, content, enrollStartDate, enrollEndDate, courseStartDate, courseEndDate)
VALUES 
    ('Introduction to Irish Folklore', 190.00, 25, 'Culture', 
'<h3>Course Content</h3>
<ol>
<li>Introduction - Defining "Folklore"</li>
<li>The Otherworld and Fairy Folklore</li>
<li>The Wake: Death and Dying in  
Traditional Worldview</li>
<li>Pattern Days and Rag Trees: Ireland\'s 
Sacred Landscape</li>
<li>Festivals: May Day, St. Brigit\'s Day  
and Lúnasa</li>
<li>Samhain and Halloween</li>
<li>Folklore Collection: The Irish  
Folklore Commission</li>
<li>Urban Legends and Folklore Collection  
in the City</li>
</ol>

<p>This course provides an introduction to the subject of folklore, including 
the history of folklore collection in Ireland. The class covers Irish folklore 
as well as aspects of the folklore of other countries, particularly in 
relation to some supernatural beliefs, which can be compared to other 
cultural contexts. The focus is on belief, custom and narrative traditions 
in the Irish context.</p>', 
'2013-01-01', '2013-05-01', '2013-06-01', '2013-06-05'),
    ('Sci-Fi Lore', 190.00, 25, 'Sci-Fi', 
'<h3>Course Content</h3>
<ol>
<li>Star Wars</li>
<li>Star Trek</li>
</ol>

<p>These are the voyages of....</p>', 
'2013-01-01', '2013-05-01', '2013-06-04', '2013-06-09'),
    ('The Romans', 190.00, 25, 'History', 
'<h3>Course Content</h3>
<ol>
<li>Aquaduct</li>
<li>Sanitation</li>
</ol>

<p>What have the Romans ever done for us?</p>', 
'2013-01-01', '2013-05-01', '2013-06-08', '2013-06-12'),
    ('Programming', 2000.00, 3, 'Technology', 
'<h3>Course Content</h3>
<ol>
<li>zeros</li>
<li>ones</li>
</ol>

<p>This is how to program</p>', 
'2013-01-01', '2013-05-01', '2013-07-01', '2013-07-01')
;

DROP TABLE IF EXISTS teaches; 

CREATE TABLE teaches 
(
    courseId INT NOT NULL,
    lecturerId INT NOT NULL,
    PRIMARY KEY (courseId, lecturerId),
    FOREIGN KEY(courseId) REFERENCES courses(courseId),
    FOREIGN KEY(lecturerId) REFERENCES lecturers(lecturerId)
) ENGINE=InnoDB;

INSERT INTO teaches (courseId, lecturerId) 
VALUES 
    (1,1),
    (1,2);

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

INSERT INTO enrollments (courseId, studentId, pending, paidDeposit, paidFee, issuedRefund, enrollDate) 
VALUES 
    (1, 1, false, true, false, false, '2013-03-23 19:58:27'); 

DROP TABLE IF EXISTS courseLocations; 

CREATE TABLE courseLocations 
(
    courseId INT NOT NULL,
    venueId INT NOT NULL,
    PRIMARY KEY (courseId, venueId),
    FOREIGN KEY(courseId) REFERENCES courses(courseId),
    FOREIGN KEY(venueId) REFERENCES venues(venueId)
) ENGINE=InnoDB;

INSERT INTO courseLocations (courseId, venueId) 
VALUES 
    (1,1),
    (1,2);

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

INSERT INTO comments (commentId, studentId, subject, messageText, reviewed) 
VALUES 
    (NULL, 1, 'help', 'am trapped in website', false),
    (NULL, 1, 'nvrmnd', 'is only project', false)
;