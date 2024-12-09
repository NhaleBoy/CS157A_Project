CREATE TABLE IF NOT EXISTS Users ( 
                UserId INT AUTO_INCREMENT PRIMARY KEY,  
                Username VARCHAR(100) NOT NULL UNIQUE,    
                PrefGenre VARCHAR(100), 
                Email VARCHAR(255) NOT NULL,  
                Password VARCHAR(255) NOT NULL
                );
                
CREATE TABLE IF NOT EXISTS Playlists ( 
                PlaylistId INT AUTO_INCREMENT PRIMARY KEY,  
                AuthorId INT NOT NULL,  
                Title VARCHAR(100) NOT NULL,  
                FOREIGN KEY (AuthorId) REFERENCES Users(UserId)  
                );

CREATE TABLE IF NOT EXISTS Audios ( 
                AudioId INT AUTO_INCREMENT PRIMARY KEY,  
                Title VARCHAR(100) NOT NULL,  
                Genre VARCHAR(50) NOT NULL ,
                Category VARCHAR(50) NOT NULL ,
                FilePath VARCHAR(100) NOT NULL,
                AuthorId INT NOT NULL,  
                FOREIGN KEY (AuthorId) REFERENCES Users(UserId)  
                );

CREATE TABLE IF NOT EXISTS PlaylistContents ( 
                PlaylistContentsId INT AUTO_INCREMENT PRIMARY KEY,  
                PlaylistId INT NOT NULL,  
                AudioId INT NOT NULL,  
                FOREIGN KEY (PlaylistId) REFERENCES Playlist(PlaylistId),  
                FOREIGN KEY (AudioId) REFERENCES Audio(AudioId) 
                );