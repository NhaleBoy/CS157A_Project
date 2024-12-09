DELETE FROM Audios;
DELETE FROM Users;
DELETE FROM Playlists;
DELETE FROM PlaylistContents;

INSERT INTO Users (UserId, Username, Password, Email, PrefGenre) VALUES
(1, 'user1', 'pass1', 'email1@gmail.com', 'Rock'),
(2, 'user2', 'pass2', 'email2@gmail.com', 'EDM'),
(3, 'user3', 'pass3', 'email3@gmail.com', 'Rap'),
(4, 'user4', 'pass4', 'email4@gmail.com', 'Techno'),
(5, 'user5', 'pass5', 'email5@gmail.com', 'R&B'),
(6, 'user6', 'pass6', 'email6@gmail.com', 'Ambient'),
(7, 'user7', 'pass7', 'email7@gmail.com', 'Country'),
(8, 'user8', 'pass8', 'email8@gmail.com', 'House'),
(9, 'user9', 'pass9', 'email9@gmail.com', 'UK Garage'),
(10, 'user10', 'pass10', 'email10@gmail.com', 'K-Pop'),
(11, 'coolguy123', 'password', 'coolgu7@yahoo.com', 'Soundtrack'),
(12, 'NathanJ', 'passw0rd', 'realemail@gmail.com', 'Rap'),
(13, 'Benny1e', 'ilovekpop', 'Benny@gmail.com', 'K-Pop'),
(14, 'testuser', 'test', 'test@gmail.com', 'EDM'),
(15, 'user15', 'pass15', 'email15@gmail.com', 'Rap');


INSERT INTO Audios (AudioId, Title, AuthorId, Category, Genre, FilePath) VALUES
(1, 'british-soldiers-monologue', 11, 'Podcast', 'Monologue', 'audios/british-soldiers-monologue.wav'),
(2, 'funny-country-loop', 15, 'Song', 'Country', 'audios/funny-country-loop.wav'),
(3, 'baby_crying', 12, 'Podcast', 'Acting', 'baby_crying.wav'),
(4, 'baby_cry', 12, 'Podcast', 'Monologue', 'baby_cry.wav'),
(5, 'yama-rock1', 10, 'Song', 'Rock', 'yama-rock1.wav'),
(6, 'rock_guitar1', 10, 'Song', 'Rock', 'rock_guitar1.wav'),
(7, 'funny-country-loop', 9, 'Song', 'Country', 'funny-country-loop.wav'),
(8, 'construction', 11, 'Song', 'Metal', 'construction.wav'),
(9, 'chime_big_ben', 9, 'Song', 'UK Garage', 'chime_big_ben.wav'),
(10, 'cheer_long', 10, 'Song', 'K-Pop', 'cheer_long.wav'),
(11, 'chainsaw', 10, 'Song', 'Rap', 'chainsaw.wav'),
(12, 'ahem_x', 13, 'Podcast', 'Monologue', 'ahem_x.wav'),
(13, 'cash_register2', 10, 'Song', 'Rap', 'cash_register2.wav'),
(14, 'car_crash', 14, 'Podcast', 'True Crime', 'car_crash.wav'),
(15, 'car_crash2', 14, 'Song', 'True Crime', 'car_crash2.wav');


INSERT INTO Playlists (PlaylistId, AuthorId, Title) VALUES
(1,1,'My First Playlist'),
(2,2,'EDM beats'),
(3,3,'hard music'),
(4,4,'podcaster'),
(5,5,'sleepy mix'),
(6,6,'liked music'),
(7,7,'fav songs'),
(8,8,'music'),
(9,9,'london is my city'),
(10,1,'My second Playlist'),
(11,11,'soundtrack of my life'),
(12,12,'playlist swag'),
(13,13,'I love k-pop'),
(14,14,'empty playlist'),
(15,115,'superlist'),

INSERT INTO PlaylistContents (PlaylistContentsId, PlaylistId, AudioId) VALUES
(1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),
(7,2,11),(8,2,12),(9,3,11),(10,3,11),(11,4,2),(12,5,3),
(13,5,1),(14,5,14),(15,6,5),
(16,7,1),(17,7,2),
(18,8,10),(19,8,2),(20,8,13)
(21,9,11),(22,9,2),(23,9,3),(24,10,4),(25,10,5),(26,10,7),
(27,11,12),(28,11,8),(29,11,9),(30,12,7),
(31,12,14),(32,12,15),(33,13,7),(34,13,5),(35,13,13),(36,13,4),
(37,15,11),(38,15,12),(39,15,3),(40,15,11),