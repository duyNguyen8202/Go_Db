package Controller.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import DAO.PlaceDAO;
import Model.Place;
import DBConnect.DBConnection;

/**
 * Servlet implementation class PlaceController
 */
@WebServlet(urlPatterns = {"/api-admin/place"})
public class AdminPlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPlaceController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private final Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = request.getContextPath() + request.getServletPath();
		Connection conn = null;
		
		List<Place> places=null;
		Place place = null;
		String id = request.getParameter("id");
		
		try{
			conn=DBConnection.getConnection();
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		PlaceDAO dao = new PlaceDAO(conn);
	
		// lấy thông tin một place
		if (id !="") {
			try {
				places = dao.getPlaces();
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(response.getOutputStream(), places);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// lấy list các place
		else {
			try {
				place = dao.getPlaceById(id);
				ObjectMapper obj = new ObjectMapper();
				conn.close();
				obj.writeValue(response.getOutputStream(), place);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		String path = request.getContextPath() + request.getServletPath();
		Connection conn = null;
		
		try{
			conn=DBConnection.getConnection();
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String placeId = request.getParameter("place_id");
		PlaceDAO placeDAO = new PlaceDAO(conn);
	
		String placeJson = request.getReader().readLine();
        Place place = gson.fromJson(placeJson, Place.class);
        try {
			Boolean checkPlace = placeDAO.CheckPlace(placeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			placeDAO.addPlace(place);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		String path = request.getContextPath() + request.getServletPath();
		Connection conn = null;
		
		try{
			conn=DBConnection.getConnection();
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String placeId = request.getParameter("place_id");
		PlaceDAO placeDAO = new PlaceDAO(conn);
	
        try {
        	//kiểm tra place tồn tại chưa
			Boolean checkPlace = placeDAO.CheckPlace(placeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Sửa địa điểm
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Place place = gson.fromJson(request.getReader(), Place.class);
            place.setPlaceId(placeId);
            try {
				placeDAO.updatePlace(place);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
        
        /**
    	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
    	 */
	
	// chưa có xóa địa điểm
	
//    	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    		// TODO Auto-generated method stub
//    		response.setContentType("application/json;charset=UTF-8");
//    		String path = request.getContextPath() + request.getServletPath();
//    		Connection conn = null;
//    		
//    		String placeId = request.getParameter("place_id");
//    		PlaceDAO placeDAO = new PlaceDAO(conn);
//    	
//            try {
//            	//kiểm tra place tồn tại chưa
//    			Boolean checkPlace = placeDAO.CheckPlace(placeId);
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
//    		
//    		// Xóa địa điểm
//            String pathInfo = request.getPathInfo();
//            if (pathInfo == null || pathInfo.equals("/")) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            } else {
//                try {
//					placeDAO.deletePlace(placeId);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}		
//       }
		

}
