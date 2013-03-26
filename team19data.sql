INSERT INTO students (studentID, firstName, lastName, email, passwordHash, addressLine1, addressLine2, addressLine3, country,  telephone, dateOfBirth, gender, authenticated, authString, dateRegistered, cookieToken, emailOPtIn)
    VALUES
    (NULL, 'Cormac','Lynch','celynch@gmail.com','123','1 A Street','B-Town','C-City','Ireland','086-1234567','1981-01-01','M',true,'072d-31bbd0e-d3f582ae-340f0e39-f73e8','2013-02-24 16:30:00', '072d-31bbd0e-d3f582ae-340f0e39-f73e8', true),
    (NULL, 'Aaron','Aaronson','101664280@umail.ucc.ie','123','1 A Street','B-Town','C-City','Ireland','086-1234567','1981-01-01','M',true,'072d-31bbd0e-d3f582ae-340f0e39-f73e8','2013-02-24 16:30:00', '072d-31bbd0e-d3f582ae-340f0e39-f73e8', true)
;
    
INSERT INTO lecturers (firstName, lastName, email, lecturerTitle, position, addressLine1, addressLine2, addressLine3, country, telephone)
    VALUES 
    ('Luke', 'Manning', 'lmanning@gmail.com', '', 'Project Manager, Team 19', '2 A Street', 'B-Town', 'C-City', 'Ireland', '086-1234567'), 
    ('Eoghan', 'Healy', 'ehealy@gmail.com', 'Dr', 'Software Engineering Lecturer', '3 C Street', 'A-Town', 'B-City', 'Ireland', '420-5913')
;

INSERT INTO admins (adminName, adminPassword, email)
    VALUES 
    ('mindaugas', 'rakauskas', 'mrakauskas@gmail.com')
;

INSERT INTO venues (venueRoom, venueBuilding, addressLine1, addressLine2, addressLine3, capacity, onCampus)
    VALUES 
    ('G01', 'Western Gateway Building', 'UCC', 'Western Road', 'Cork', 60, false),
    ('Aula Maxima', 'North Wing', 'UCC', 'Western Road', 'Cork', 100, true)
;

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

INSERT INTO teaches (courseId, lecturerId) 
    VALUES 
    (1,1),
    (1,2);
    
INSERT INTO enrollments (courseId, studentId, pending, paidDeposit, paidFee, issuedRefund, enrollDate) 
VALUES 
    (1, 1, false, true, false, false, '2013-03-23 19:58:27'); 

INSERT INTO courseLocations (courseId, venueId) 
    VALUES 
    (1,1),
    (1,2);

INSERT INTO comments (commentId, studentId, subject, messageText, reviewed) 
    VALUES 
    (NULL, 1, 'help', 'am trapped in website', false),
    (NULL, 1, 'nvrmnd', 'is only project', false)
;
