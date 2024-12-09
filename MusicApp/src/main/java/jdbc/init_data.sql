DELETE FROM Audios;
DELETE FROM Users;
DELETE FROM Playlists;
DELETE FROM PlaylistContents;

INSERT INTO Users (UserId, Username, Password, Email, PrefGenre) VALUES
(1, 'user1', 'pass1', 'email1@gmail.com', 'Rock'),
(2, 'user2', 'pass2', 'email2@gmail.com', 'EDM'),
(3, 'user3', 'pass3', 'email3@gmail.com', 'Rap');


INSERT INTO Audios (AudioId, Title, AuthorId, Category, Genre, FilePath) VALUES
(1, 'british-soldiers-monologue', 3, 'Podcast', 'Monologue', 'audios/british-soldiers-monologue.wav'),
(2, 'funny-country-loop', 1, 'Song', 'Country', 'audios/funny-country-loop.wav');

INSERT INTO Playlists (PlaylistId, AuthorId, Title) VALUES
(1,1,'My First Playlist');

INSERT INTO PlaylistContents (PlaylistContentsId, PlaylistId, AudioId) VALUES
(1,1,1),
(2,1,2);