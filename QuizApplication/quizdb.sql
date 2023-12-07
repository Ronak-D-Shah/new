Drop database if exists quizDB;
CREATE DATABASE IF NOT EXISTS quizDB;
USE quizDB;

CREATE TABLE IF NOT EXISTS quiz_questions (
    statement VARCHAR(100),
    option1 VARCHAR(40),
    option2 VARCHAR(40),
    option3 VARCHAR(40),
    option4 VARCHAR(40),
    answer VARCHAR(40),
    quiz_type int
);

CREATE TABLE IF NOT EXISTS app_admin(
	email VARCHAR(25),
    password VARCHAR(25),
    PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS student (
    student_id INT AUTO_INCREMENT,
    email VARCHAR(30),
    first_name VARCHAR(15),
    last_name VARCHAR(15),
    password VARCHAR(15),
    PRIMARY KEY (student_id)
);


CREATE TABLE IF NOT EXISTS quiz (
    quiz_id INT AUTO_INCREMENT,
    quiz_date VARCHAR(25),
    student_id INT,
    score INT,
    quiz_type VARCHAR(10),
    PRIMARY KEY (quiz_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);

INSERT INTO app_admin(email, password) VALUES
('admin@gmail.com', 'admin123');

INSERT INTO student (email, first_name, last_name, password) VALUES
('john.doe@gmail.com', 'John', 'Doe', 'password123'),
('jane.smithgemail.com', 'Jane', 'Smith', 'securepass'),
('bob.johnson@yahoo.com', 'Bob', 'Johnson', 'pass456'),
('alice.wonder@outlook.com', 'Alice', 'Wonder', 'p@ssw0rd'),
('charlie.brown@outlook.com', 'Charlie', 'Brown', 'brownie1'),
('emma.white@gmail.com', 'Emma', 'White', 'password789'),
('david.jones@outlook.com', 'David', 'Jones', 'davidpass'),
('olivia.davis@yahoo.com', 'Olivia', 'Davis', 'olivia123'),
('michael.martin@gmail.com', 'Michael', 'Martin', 'mikepass'),
('sophia.hall@yahoo.com', 'Sophia', 'Hall', 'sophie123'),
('jacob.smith@yahoo.com', 'Jacob', 'Smith', 'jacobpass'),
('isabella.jones@gmail.com', 'Isabella', 'Jones', 'isabellapass'),
('logan.williams@gmail.com', 'Logan', 'Williams', 'loganpass'),
('amelia.moore@gmail.com', 'Amelia', 'Moore', 'amelia123'),
('benjamin.king@yahoo.com', 'Benjamin', 'King', 'benjipass'),
('grace.baker@gmail.com', 'Grace', 'Baker', 'gracepass'),
('lucas.cook@gmail.com', 'Lucas', 'Cook', 'lucas123'),
('mia.rogers@outlook.com', 'Mia', 'Rogers', 'miapass'),
('jack.morris@yahoo.com', 'Jack', 'Morris', 'jackpass'),
('aubrey.scott@gmail.com', 'Aubrey', 'Scott', 'aubreypass');

-- Sports (quiz_type 0)
INSERT INTO quiz_questions (statement, option1, option2, option3, option4, answer, quiz_type)
VALUES
    ('Who won the FIFA World Cup in 2018?', 'France', 'Brazil', 'Germany', 'Spain', 'France', 0),
    ('In which sport would you perform a slam dunk?', 'Tennis', 'Basketball', 'Soccer', 'Golf', 'Basketball', 0),
    ('Which country is known for hosting the Wimbledon Championships?', 'France', 'United States', 'England', 'Australia', 'England', 0),
    ('In Olympic swimming, what is the maximum distance for the freestyle event?', '100 meters', '200 meters', '400 meters', '800 meters', '1500 meters', 0),
    ('Who is often referred to as "The Rocket" in snooker?', 'Ronnie O\'Sullivan', 'Mark Selby', 'Stephen Hendry', 'John Higgins', 'Ronnie O\'Sullivan', 0),
    ('Which country has won the most Olympic gold medals?', 'United States', 'China', 'Russia', 'Germany', 'United States', 0),
    ('What is the nickname of the Australian national rugby union team?', 'All Blacks', 'Kangaroos', 'Springboks', 'Wallabies', 'Wallabies', 0),
    ('Who holds the record for the most goals scored in a single football season in Europe?', 'Lionel Messi', 'Cristiano Ronaldo', 'Neymar', 'Robert Lewandowski', 'Lionel Messi', 0),
    ('In which year did Usain Bolt set the world record for the 100 meters?', '2008', '2012', '2016', '2020', '2009', 0),
    ('Which city hosted the Summer Olympics in 2016?', 'Tokyo', 'Rio de Janeiro', 'London', 'Beijing', 'Rio de Janeiro', 0);

-- Movies (quiz_type 1)
INSERT INTO quiz_questions (statement, option1, option2, option3, option4, answer, quiz_type)
VALUES
    ('Who directed the movie "Inception"?', 'Christopher Nolan', 'Quentin Tarantino', 'Steven Spielberg', 'Martin Scorsese', 'Christopher Nolan', 1),
    ('Which actor played the character Jack Dawson in the movie "Titanic"?', 'Leonardo DiCaprio', 'Brad Pitt', 'Tom Hanks', 'Johnny Depp', 'Leonardo DiCaprio', 1),
    ('What is the highest-grossing animated film of all time?', 'The Lion King', 'Frozen', 'Toy Story 4', 'Finding Nemo', 'Frozen', 1),
    ('In the movie "The Matrix," what color pill does Neo take?', 'Red', 'Blue', 'Green', 'Yellow', 'Red', 1),
    ('Who won the Academy Award for Best Actor for his role in the movie "The Revenant"?', 'Joaquin Phoenix', 'Matthew McConaughey', 'Leonardo DiCaprio', 'Christian Bale', 'Leonardo DiCaprio', 1),
    ('Which film won the Best Picture Oscar at the 2020 Academy Awards?', 'Parasite', '1917', 'Joker', 'Once Upon a Time in Hollywood', 'Parasite', 1),
    ('Who played the character Hermione Granger in the "Harry Potter" film series?', 'Emma Watson', 'Emma Stone', 'Keira Knightley', 'Natalie Portman', 'Emma Watson', 1),
    ('Which movie features a character named Tyler Durden?', 'Fight Club', 'The Shawshank Redemption', 'Pulp Fiction', 'The Dark Knight', 'Fight Club', 1),
    ('In the movie "Forrest Gump," what does Forrests mother say life is like?', 'A box of chocolates', 'A puzzle', 'A marathon', 'A journey', 'A box of chocolates', 1),
    ('Who directed the film "The Shawshank Redemption"?', 'Frank Darabont', 'David Fincher', 'Quentin Tarantino', 'Christopher Nolan', 'Frank Darabont', 1);

-- Animals (quiz_type 2)
INSERT INTO quiz_questions (statement, option1, option2, option3, option4, answer, quiz_type)
VALUES
    ('Which mammal can fly?', 'Bat', 'Dolphin', 'Cheetah', 'Elephant', 'Bat', 2),
    ('What is the largest species of big cat?', 'Lion', 'Tiger', 'Leopard', 'Cheetah', 'Tiger', 2),
    ("Which animal is known as the 'king of the jungle'?", 'Lion', 'Tiger', 'Giraffe', 'Elephant', 'Lion', 2),
    ('What is the largest living bird?', 'Penguin', 'Ostrich', 'Eagle', 'Condor', 'Ostrich', 2),
    ("Which species of bear is native to North America and is known for its hump on its back?", 'Polar Bear', 'Grizzly Bear', 'Black Bear', 'Kodiak Bear', 'Grizzly Bear', 2),
    ('What is the only continent where giraffes live in the wild?', 'Africa', 'South America', 'Asia', 'Australia', 'Africa', 2),
    ('Which marine mammal is known for its playful behavior and intelligence?', 'Dolphin', 'Whale', 'Seal', 'Manatee', 'Dolphin', 2),
    ("What is the world's largest reptile?", 'Alligator', 'Iguana', 'Komodo Dragon', 'Saltwater Crocodile', 'Saltwater Crocodile', 2),
    ('Which big cat is known for its black fur and is native to parts of Asia?', 'Leopard', 'Jaguar', 'Cheetah', 'Black Panther', 'Black Panther', 2),
    ('What is the collective name for a group of wolves?', 'Pack', 'Herd', 'Flock', 'School', 'Pack', 2);

-- Create a trigger that deletes related quiz records when a student is deleted
DELIMITER //

CREATE TRIGGER delete_student_and_related_quizzes
BEFORE DELETE ON student
FOR EACH ROW
BEGIN
    DELETE FROM quiz WHERE student_id = OLD.student_id;
END;
//
DELIMITER ;
