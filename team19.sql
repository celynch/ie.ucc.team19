/*
source ~/cs3305/team19.sql
*/

DROP TABLE IF EXISTS Teaches;
DROP TABLE IF EXISTS Enrollments;
DROP TABLE IF EXISTS Course_locations;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Venues;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Lecturers;
DROP TABLE IF EXISTS Admins;

DROP TABLE IF EXISTS Students;

CREATE TABLE Students
(
    student_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password_hash CHAR(32),
    address_line1 VARCHAR(30),
    address_line2 VARCHAR(30),
    address_line3 VARCHAR(30),
    country VARCHAR(30),
    telephone VARCHAR(20), 
    date_of_birth DATE,
    gender CHAR(1),
    authenticated BOOLEAN,
    auth_string CHAR(36),
    date_ac_created DATETIME,
    cookie_token CHAR(36),
    PRIMARY KEY(student_id)
) ENGINE=InnoDB;

INSERT INTO Students 
VALUES
    (NULL, 'Cormac','Lynch','celynch@gmail.com','123','1 A Street','B-Town','C-City','Ireland','086-1234567','1981-01-01','M',true,'072d-31bbd0e-d3f582ae-340f0e39-f73e8','2013-02-24 16:30:00', '072d-31bbd0e-d3f582ae-340f0e39-f73e8')
;

DROP TABLE IF EXISTS Lecturers; 

CREATE TABLE Lecturers 
(
    lecturer_id INT NOT NULL AUTO_INCREMENT, 
    first_name VARCHAR(30) NOT NULL, 
    last_name VARCHAR(30) NOT NULL, 
    email VARCHAR(50), 
    lecturer_title VARCHAR(20), 
    position VARCHAR(100), 
    address_line1 VARCHAR(30), 
    address_line2 VARCHAR(30), 
    address_line3 VARCHAR(30), 
    country VARCHAR(30), 
    telephone VARCHAR(20), 
    PRIMARY KEY(lecturer_id) 
) ENGINE=InnoDB; 

INSERT INTO Lecturers (first_name, last_name, email, lecturer_title, position, address_line1, address_line2, address_line3, country, telephone)
VALUES 
    ('Luke', 'Manning', 'lmanning@gmail.com', '', 'Project Manager, Team 19', '2 A Street', 'B-Town', 'C-City', 'Ireland', '086-1234567'), 
    ('Eoghan', 'Healy', 'ehealy@gmail.com', 'Dr', 'Software Engineering Lecturer', '3 C Street', 'A-Town', 'B-City', 'Ireland', '420-5913');

DROP TABLE IF EXISTS Admins; 

CREATE TABLE Admins 
(
    admin_name VARCHAR(30),
    admin_password VARCHAR(30),
    email VARCHAR(30),
    PRIMARY KEY(admin_name) 
) ENGINE=InnoDB; 

INSERT INTO Admins (admin_name, admin_password, email)
VALUES 
    ('mindaugas', 'rakauskas', 'mrakauskas@gmail.com') 
;

DROP TABLE IF EXISTS Venues; 

CREATE TABLE Venues 
(
    venue_id INT NOT NULL AUTO_INCREMENT,
    venue_room VARCHAR(30),
    venue_building VARCHAR(30),
    address_line1 VARCHAR(30), 
    address_line2 VARCHAR(30), 
    address_line3 VARCHAR(30),
    capacity INT,
    on_campus BOOLEAN,
    PRIMARY KEY(venue_id) 
) ENGINE=InnoDB;

INSERT INTO Venues (venue_room, venue_building, address_line1, address_line2, address_line3, capacity, on_campus)
VALUES 
    ('G01', 'Western Gateway Building', 'UCC', 'Western Road', 'Cork', 60, false),
    ('Aula Maxima', 'North Wing', 'UCC', 'Western Road', 'Cork', 100, true)
;

DROP TABLE IF EXISTS Courses; 

CREATE TABLE Courses 
(
    course_id INT NOT NULL AUTO_INCREMENT,
    course_title VARCHAR(100),
    fee DOUBLE,
    spaces INT,
    course_category VARCHAR(30),
    content TEXT,
    enroll_start_date DATE,
    enroll_end_date DATE,
    url_timetable VARCHAR(30),
    PRIMARY KEY(course_id) 
) ENGINE=InnoDB;

INSERT INTO Courses (course_title, fee, spaces, course_category, content, enroll_start_date, enroll_end_date, url_timetable)
VALUES 
    ('Introduction to Irish Folklore', 190.00, 25, 'culture', 
'<h3>Course Content</h3>
<ol>
<li>Introduction – Defining "Folklore"</li>
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
'2013-01-01', '2013-05-01', '/timetables/') 
;

DROP TABLE IF EXISTS Teaches; 

CREATE TABLE Teaches 
(
    course_id INT NOT NULL,
    lecturer_id INT NOT NULL,
    PRIMARY KEY (course_id, lecturer_id),
    FOREIGN KEY(course_id) REFERENCES Courses(course_id),
    FOREIGN KEY(lecturer_id) REFERENCES Lecturers(lecturer_id)
) ENGINE=InnoDB;

INSERT INTO Teaches  
VALUES 
    (1,1),
    (1,2);

DROP TABLE IF EXISTS Enrollments; 

CREATE TABLE Enrollments 
(
    course_id INT REFERENCES Courses(course_id),
    student_id INT REFERENCES Students(student_id),
    pending BOOLEAN,
    deposit_paid BOOLEAN,
    fee_paid BOOLEAN,
    refund_issued BOOLEAN,
    PRIMARY KEY (course_id, student_id)
) ENGINE=InnoDB;

INSERT INTO Enrollments (course_id, student_id, pending, deposit_paid, fee_paid, refund_issued) 
VALUES 
    (1, 1, false, true, false, false); 

DROP TABLE IF EXISTS Course_locations; 

CREATE TABLE Course_locations 
(
    course_id INT NOT NULL,
    venue_id INT NOT NULL,
    PRIMARY KEY (course_id, venue_id),
    FOREIGN KEY(course_id) REFERENCES Courses(course_id),
    FOREIGN KEY(venue_id) REFERENCES Venues(venue_id)
) ENGINE=InnoDB;

INSERT INTO Course_locations  
VALUES 
    (1,1),
    (1,2);
