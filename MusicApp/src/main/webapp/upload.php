<?php 
if(isset($_POST['save_audio']) && &_POST['save_audio']=="Upload Audio")
{
    $dir="uploads/";
    $audio_path=$dir.basename($_FILES['audioFile']['name']);
    
    if(move_uploaded_file($FILES['audioFile']["temp_name"],$audio_path))
    {
        echo 'uploaded successfully.';
        
        saveAudio($audio_path);
    }
}

function saveAudio($fileName)
{
    $conn=mysqli_connect('localhost','root','','MusicApp');
    if(!$conn)
    {
        die('server not connected');
    }
    
    $query="INSERT INTO Audios(Title) VALUES('{$fileName}')";
    
    mysqli_query($conn,$query);
    
    if(mysqli_affected_rows($conn)>0)
    {
        echo "audio file path saved in database.";
    }
    
    mysqli_close($conn);
}

?>