package edu.pitt.spotify.rest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.pitt.spotify.utils.DbUtilities;

/**
 * Servlet implementation class get_albums
 */
@WebServlet("/api/get_albums")
public class get_albums extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_albums() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		// Title
		// Artist
		// Song
		// Year
		String title = "", artist = "", song = "", albumYear = "";
		String sql = "";
		if(request.getParameter("title") != null){
			title = request.getParameter("title");
			if(!title.equals("")){
				sql = "SELECT * FROM album WHERE title LIKE '" + title + "%';";
			}
		}
		else if(request.getParameter("artist") != null){
			artist = request.getParameter("artist");
			if(! artist.equals("")){
				sql = "SELECT * FROM song JOIN song_artist ON song_id = fk_song_id JOIN artist ON fk_artist_id = artist_id ";
				sql += "WHERE artist.band_name LIKE '" + artist + "%' ";
				sql += "OR artist.first_name LIKE '" + artist + "%' ";
				sql += "OR artist.last_name LIKE '" + artist + "%'; ";
			}
		}
		else if(request.getParameter("albumYear")!=null){
			albumYear = request.getParameter("albumYear");
			if(!albumYear.equals("")){
				sql = "SELECT * FROM album WHERE YEAR(release_date) = " + albumYear + ";";
			}
		}
		else if(request.getParameter("song")!=null){
			song = request.getParameter("song");
			if(!song.equals("")){
				sql = "SELECT * FROM song JOIN album_song ON song_id = fk_song_id ";
				sql += "JOIN album ON fk_album_id = album_id WHERE song.title LIKE '" + song + "%';";
			}
		}
		
		if(sql.equals("")){
			sql = "SELECT * FROM album;";
		}
		// response.getWriter().write(sql);
		
		JSONArray albumList = new JSONArray();
		
		try {
			DbUtilities db = new DbUtilities();
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				JSONObject album = new JSONObject();
				album.put("album_id", rs.getString("song_id"));
				album.put("title", rs.getString("title"));
				album.put("release_date", rs.getString("release_date"));
				album.put("recording_company_name", rs.getString("record_date"));
				album.put("number _of_tracks", rs.getInt("record_date"));
				album.put("PMRC_rating", rs.getString("record_date"));
				album.put("length", rs.getInt("length"));
				albumList.put(album);
			}
			response.getWriter().write(albumList.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
