package webpage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.AudioDAO;
import jdbc.Playlist;
import jdbc.PlaylistContents;
import jdbc.PlaylistContentsDAO;
import jdbc.PlaylistDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet implementation class CreatePlaylist
 */
@WebServlet("/CreatePlaylist")
public class CreatePlaylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePlaylist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String title = request.getParameter("Playlist Title");
         String dirtyContents = request.getParameter("Comma Separated List of Audio Titles");
         if (title != null && !title.isEmpty()) {
             try {
             	PlaylistDAO playlist1DAO = new PlaylistDAO();
                 PlaylistContentsDAO playlist1ContentsDAO = new PlaylistContentsDAO();
                 AudioDAO ADAO = new AudioDAO();
                 int authorId =  (int) request.getSession().getAttribute("userId");
                 //make sure to handle exceptions and empty playlist content
                 Playlist newPlaylist = new Playlist(0, authorId, title); // Playlist ID will be auto-generated
                 int generatedId = playlist1DAO.addPlaylist(newPlaylist);
                 if (generatedId > 0) {
                	//separate dirtycontents into list of audio names
                     if(dirtyContents != null && !dirtyContents.isEmpty()){
                     	List<String> songTitles = Arrays.asList(dirtyContents.split("\\s*,\\s*"));
                     	for(String songtitle: songTitles){
                     		int audioid = ADAO.getAudioIdByTitle(songtitle);
                     		if(audioid == -1){
                     			 String message = "Invalid title.";
                 				request.setAttribute("message", message);
                 				RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
                 				rd.forward(request, response);
                     		}
                     		PlaylistContents songti = new PlaylistContents(0,newPlaylist.getPlaylistId(),audioid);
                     		playlist1ContentsDAO.addPlaylistContents(songti);
                     	}
                 } else {
                	 String message = "Something went wrong";
       				request.setAttribute("message", message);
       				RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
       				rd.forward(request, response);
                 }
                 
                 }
             } catch (SQLException e) {
            	String message = "Something went wrong";
  				request.setAttribute("message", message);
  				RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
  				rd.forward(request, response);
             }
         } else {
             response.sendRedirect("homepage.jsp");
         }
	}

}
