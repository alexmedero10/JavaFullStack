-- Creates a new database named SocialMediaPlatform
CREATE DATABASE SocialMediaPlatform;

-- Sets the newly created database as the active database
USE SocialMediaPlatform;

-- Creates the Users table to store all user account information
CREATE TABLE Users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    profile_picture VARCHAR(255)
);

-- Creates the Posts table to store all user-generated content
CREATE TABLE Posts (
	post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL, 
    post_text TEXT NOT NULL,
    post_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    media_url VARCHAR(255),
    CONSTRAINT fk_post_user FOREIGN KEY (user_id) 
    REFERENCES Users(user_id)
);

-- Creates the Comments table to store comments on posts
CREATE TABLE Comments (
	comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_text TEXT NOT NULL,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id)
    REFERENCES Posts(post_id),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id)
    REFERENCES Users(user_id)
);

-- Creates the Likes table to track post likes
CREATE TABLE Likes (
	like_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    like_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_like_post FOREIGN KEY (post_id)
    REFERENCES Posts(post_id),
    CONSTRAINT fk_like_user FOREIGN KEY (user_id)
    REFERENCES Users(user_id)
);

-- Creates the Follows table to track user following relationships
CREATE TABLE Follows (
	follower_id INT NOT NULL,
    following_id INT NOT NULL,
    follow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_follow_follower FOREIGN KEY (follower_id)
    REFERENCES Users(user_id),
    CONSTRAINT fk_follow_following FOREIGN KEY (following_id)
    REFERENCES Users(user_id)
);

-- Creates the Messages table for private user communications
CREATE TABLE Messages (
	message_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    message_text TEXT NOT NULL,
    message_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_message_sender FOREIGN KEY (sender_id)
    REFERENCES Users(user_id),
    CONSTRAINT fk_message_receiver FOREIGN KEY (receiver_id)
    REFERENCES Users(user_id)
);

-- Creates the Notifications table for system notifications to users
CREATE TABLE Notifications (
	notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    notification_text TEXT NOT NULL,
    notification_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_read BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_norification_user FOREIGN KEY (user_id)
    REFERENCES Users(user_id)
);


-- Inserts sample user data into the Users table
 INSERT INTO Users (username, email, password, date_of_birth, profile_picture) VALUES
('john_doe', 'john.doe@example.com', 'securepass123', '1990-05-15', NULL),
('jane_smith', 'jane.smith@example.com', 'smith456', '1995-08-22', NULL),
('travel_guru', 'traveler@explorer.com', 'wanderlust', '1988-03-10', NULL),
('tech_wizard', 'coder@dev.io', 'algorithm99', '1993-11-30', NULL),
('foodie_queen', 'chef@food.net', 'yummy123', '1992-07-18', NULL),
('fitness_coach', 'trainer@fit.com', 'workout22', '1985-09-25', NULL),
('book_lover', 'reader@library.org', 'pageTurner', '1991-12-05', NULL),
('music_maniac', 'listener@tunes.com', 'melody88', '1994-04-20', NULL),
('art_enthusiast', 'painter@canvas.com', 'brushstroke', '1989-06-12', NULL),
('movie_buff', 'cinephile@films.com', 'popcorn1', '1996-02-28', NULL);

-- Inserts sample post data into the Posts table
INSERT INTO Posts (user_id, post_text, post_date, media_url) VALUES
(1, 'Just finished my morning hike! The view was breathtaking. #nature #outdoors', '2023-05-10 08:15:00', 'https://example.com/media/hike.jpg'),
(2, 'Working on a new web development project. So excited about the possibilities! #coding #webdev', '2023-05-11 14:30:00', NULL),
(3, 'Visited Paris for the first time. The Eiffel Tower is even more impressive in person! #travel #paris', '2023-05-12 19:45:00', 'https://example.com/media/eiffel.jpg'),
(4, 'Just learned a new machine learning algorithm. The future is here! #ai #tech', '2023-05-13 10:20:00', NULL),
(5, 'Made homemade pasta from scratch today. Nothing beats fresh pasta! #cooking #foodie', '2023-05-14 18:00:00', 'https://example.com/media/pasta.jpg'),
(6, 'New personal record at the gym today! Consistency pays off. #fitness #workout', '2023-05-15 07:30:00', NULL),
(7, 'Just finished "The Midnight Library". What an amazing book! Any recommendations for what to read next? #books #reading', '2023-05-16 21:15:00', NULL),
(8, 'Discovered this amazing new band. Their music is life-changing! #music #newartist', '2023-05-17 16:45:00', 'https://example.com/media/concert.jpg'),
(9, 'Working on my latest painting. The colors are coming together beautifully. #art #painting', '2023-05-18 13:20:00', 'https://example.com/media/painting.jpg'),
(10, 'Rewatching the Lord of the Rings trilogy. Still the best movies ever made! #movies #lotr', '2023-05-19 22:00:00', NULL),
(1, 'Weekend coding session with friends. Great way to spend a Saturday! #programming #friends', '2023-05-20 15:30:00', 'https://example.com/media/coding.jpg'),
(2, 'Beautiful sunset at the beach today. Nature is the best artist. #sunset #nature', '2023-05-21 19:00:00', 'https://example.com/media/sunset.jpg'),
(3, 'Planning my next adventure. Where should I go next? #travel #wanderlust', '2023-05-22 09:45:00', NULL),
(4, 'Just deployed a new feature to production. The team did an amazing job! #devops #teamwork', '2023-05-23 17:10:00', NULL),
(5, 'Baked chocolate chip cookies today. The secret is in the brown butter! #baking #dessert', '2023-05-24 14:20:00', 'https://example.com/media/cookies.jpg');

-- Inserts sample comment data into the Comments table
INSERT INTO Comments (post_id, user_id, comment_text, comment_date) VALUES
(1, 2, 'Looks amazing! Which trail did you take?', '2023-05-10 08:30:00'),
(1, 3, 'I love hiking too! We should go together sometime.', '2023-05-10 09:15:00'),
(2, 4, 'What technologies are you using for your project?', '2023-05-11 15:00:00'),
(3, 5, 'Paris is my dream destination! Any restaurant recommendations?', '2023-05-12 20:30:00'),
(4, 6, 'Which algorithm did you learn? I\'m trying to get into ML too.', '2023-05-13 11:45:00'),
(5, 7, 'Homemade pasta is the best! What sauce did you pair it with?', '2023-05-14 18:30:00'),
(6, 8, 'Congrats on the PR! What was your routine?', '2023-05-15 08:00:00'),
(7, 9, 'I loved that book too! Try "The Invisible Life of Addie LaRue" next.', '2023-05-16 22:00:00'),
(8, 10, 'What\'s the band called? Always looking for new music!', '2023-05-17 17:30:00'),
(9, 1, 'The color palette is stunning! What medium are you using?', '2023-05-18 14:00:00'),
(10, 2, 'Absolute classics! Extended editions or theatrical?', '2023-05-19 22:30:00'),
(11, 3, 'What were you working on? Sounds like fun!', '2023-05-20 16:15:00'),
(12, 4, 'Gorgeous photo! Where was this taken?', '2023-05-21 19:30:00'),
(13, 5, 'Have you considered Japan? The food alone is worth the trip!', '2023-05-22 10:30:00'),
(14, 6, 'Congrats on the deployment! CI/CD pipeline?', '2023-05-23 17:45:00'),
(15, 7, 'Brown butter makes everything better! Recipe please?', '2023-05-24 15:00:00'),
(1, 8, 'The colors in that photo are incredible!', '2023-05-10 10:00:00'),
(3, 9, 'Did you go up the tower? The view is spectacular!', '2023-05-12 21:00:00'),
(5, 10, 'I can almost smell them through the screen!', '2023-05-14 19:00:00'),
(7, 1, 'That book changed my perspective on so many things!', '2023-05-16 23:00:00');

-- Inserts sample like data into the Likes table
INSERT INTO Likes (post_id, user_id, like_date) VALUES
(1, 2, '2023-05-10 08:20:00'),
(1, 3, '2023-05-10 08:25:00'),
(1, 4, '2023-05-10 09:00:00'),
(2, 1, '2023-05-11 14:35:00'),
(2, 5, '2023-05-11 15:30:00'),
(3, 6, '2023-05-12 19:50:00'),
(3, 7, '2023-05-12 20:15:00'),
(4, 8, '2023-05-13 10:25:00'),
(4, 9, '2023-05-13 11:00:00'),
(5, 10, '2023-05-14 18:05:00'),
(5, 1, '2023-05-14 18:30:00'),
(6, 2, '2023-05-15 07:35:00'),
(6, 3, '2023-05-15 08:00:00'),
(7, 4, '2023-05-16 21:20:00'),
(7, 5, '2023-05-16 21:45:00'),
(8, 6, '2023-05-17 16:50:00'),
(8, 7, '2023-05-17 17:15:00'),
(9, 8, '2023-05-18 13:25:00'),
(9, 9, '2023-05-18 14:00:00'),
(10, 10, '2023-05-19 22:05:00'),
(10, 1, '2023-05-19 22:30:00'),
(11, 2, '2023-05-20 15:35:00'),
(11, 3, '2023-05-20 16:00:00'),
(12, 4, '2023-05-21 19:05:00'),
(12, 5, '2023-05-21 19:30:00'),
(13, 6, '2023-05-22 09:50:00'),
(13, 7, '2023-05-22 10:15:00'),
(14, 8, '2023-05-23 17:15:00'),
(14, 9, '2023-05-23 17:45:00'),
(15, 10, '2023-05-24 14:25:00');

-- Inserts sample follow data into the Follows table
INSERT INTO Follows (follower_id, following_id, follow_date) VALUES
(2, 1, '2023-04-01 10:00:00'),
(3, 1, '2023-04-05 11:30:00'),
(1, 2, '2023-04-10 09:15:00'),
(4, 2, '2023-04-15 14:20:00'),
(5, 3, '2023-04-20 16:45:00'),
(3, 5, '2023-04-25 08:30:00'),
(6, 4, '2023-05-01 07:00:00'),
(7, 6, '2023-05-05 12:15:00'),
(8, 7, '2023-05-10 18:30:00'),
(9, 8, '2023-05-15 20:45:00'),
(10, 9, '2023-05-20 22:00:00'),
(1, 10, '2023-05-25 09:30:00'),
(2, 3, '2023-06-01 11:45:00'),
(4, 5, '2023-06-05 13:00:00'),
(6, 7, '2023-06-10 15:15:00');

-- Inserts sample message data into the Messages table
INSERT INTO Messages (sender_id, receiver_id, message_text, message_date, is_read) VALUES
(1, 2, 'Hey Jane, want to grab coffee this weekend?', '2023-05-10 09:00:00', TRUE),
(2, 1, 'Sure John, Saturday at 10am at our usual place?', '2023-05-10 09:05:00', TRUE),
(3, 5, 'Your cooking posts always make me hungry! Any chance you could share your pasta recipe?', '2023-05-14 19:00:00', TRUE),
(5, 3, 'Of course! I\'ll send you the details later today.', '2023-05-14 19:15:00', FALSE),
(4, 6, 'I saw your fitness post - great progress! Do you have any tips for beginners?', '2023-05-15 08:30:00', TRUE),
(6, 4, 'Thanks! The key is consistency. I can recommend some beginner routines if you\'d like.', '2023-05-15 08:45:00', FALSE),
(7, 8, 'What kind of music are you into these days? Looking for new recommendations.', '2023-05-17 18:00:00', TRUE),
(8, 7, 'Lately I\'ve been exploring jazz fusion. Check out this playlist I made!', '2023-05-17 18:30:00', FALSE),
(9, 10, 'Your movie recommendations are always on point. What should I watch tonight?', '2023-05-19 20:00:00', TRUE),
(10, 9, 'If you haven\'t seen "Everything Everywhere All At Once", it\'s mind-blowing!', '2023-05-19 20:15:00', FALSE);

-- Inserts sample notification data into the Notifications table
INSERT INTO Notifications (user_id, notification_text, notification_date, is_read) VALUES
(1, 'jane_smith liked your post', '2023-05-10 08:20:00', TRUE),
(1, 'travel_guru commented on your post', '2023-05-10 09:15:00', TRUE),
(2, 'john_doe started following you', '2023-04-10 09:15:00', TRUE),
(3, 'foodie_queen sent you a message', '2023-05-14 19:00:00', TRUE),
(4, 'fitness_coach replied to your comment', '2023-05-15 08:45:00', FALSE),
(5, 'book_lover mentioned you in a comment', '2023-05-16 22:00:00', TRUE),
(6, 'music_maniac liked your post', '2023-05-17 16:50:00', FALSE),
(7, 'art_enthusiast replied to your comment', '2023-05-18 14:00:00', TRUE),
(8, 'movie_buff started following you', '2023-05-20 22:00:00', FALSE),
(10, 'john_doe mentioned you in a comment', '2023-05-19 22:30:00', TRUE);


-- Retrieves all posts made by user with ID 1
SELECT * FROM Posts
WHERE user_id = 1;

-- Counts likes for each comment on post with ID 1 and groups by comment text
SELECT c.comment_text, COUNT(l.user_id) FROM Comments c
JOIN Likes l ON c.post_id = l.post_id 
AND c.post_id = 1 
GROUP BY c.comment_text;

-- Finds all usernames of users who follow user with ID 2
SELECT u.username FROM Users u
JOIN Follows f ON u.user_id = f.follower_id
AND f.following_id = 2;

-- Retrieves all unread messages sent to user with ID 3
SELECT * FROM Messages 
WHERE receiver_id = 3 
AND is_read = FALSE;

-- Shows the top 5 most liked posts with their like counts
SELECT p.post_text, COUNT(l.post_id) FROM Posts p 
JOIN Likes l ON p.post_id = l.post_id
GROUP BY p.post_id LIMIT 5;

-- Gets the 5 most recent unread notifications for user with ID 4
SELECT n.notification_text, n.notification_date FROM Notifications n
WHERE user_id = 4 AND is_read = FALSE
ORDER BY notification_date DESC LIMIT 5;


-- Inserts a new post by user with ID 3 about Bali
INSERT INTO Posts (user_id, post_text, media_url) VALUES (
    3, 'Exploring the beautiful beaches of Bali! The culture and food here are amazing. #travel #bali', 'https://example.com/media/bali_beach.jpg'
);

-- Adds a comment by user with ID 5 on post with ID 3 about Paris food
INSERT INTO Comments (post_id, user_id, comment_text) VALUES (
    3, 5, 'The food in Paris is incredible! You must try the croissants at Du Pain et des Idées.'
);

-- Updates profile information for user with ID 2 (jane_smith)
UPDATE Users SET 
    username = 'jane_smith_updated',
    email = 'jane.smith.new@example.com',
    date_of_birth = '1996-09-22',
    profile_picture = 'https://example.com/profiles/jane_new.jpg'
WHERE user_id = 2;

-- Removes a like from post with ID 1 by user with ID 2
DELETE FROM Likes 
WHERE post_id = 1 
AND user_id = 2;

