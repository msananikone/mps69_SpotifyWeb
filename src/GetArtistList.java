

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Servlet implementation class GetArtistList
 */
@WebServlet("/GetArtistList")
public class GetArtistList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetArtistList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM artist ORDER BY first_name ASC;";
//			if(! title.equals("")){
//				sql += " WHERE title LIKE '%" + title + "%' "; 
//			}
			//sql +=" ORDER BY band_name ASC;";
			
			ResultSet rs = db.getResultSet(sql);
			JSONArray artistList = new JSONArray();
			while(rs.next()){
				JSONObject artist = new JSONObject();
				artist.put("artist_id", rs.getString("artist_id"));
				artist.put("first_name", rs.getString("first_name"));
				artist.put("last_name", rs.getString("last_name"));
				artist.put("band_name", rs.getString("band_name"));
				artist.put("bio", rs.getString("bio"));
				artistList.put(artist);
			}
			
			response.getWriter().write(artistList.toString());
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