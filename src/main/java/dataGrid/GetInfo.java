package dataGrid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class GetInfo
 */
public class GetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Connection conn = ConnUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs  = null;
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String sql = "select * from t_user where id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			rs = ps.executeQuery();
			while(rs.next()) {
				map.put("id", String.valueOf(rs.getInt("id")));
				map.put("username", rs.getString("username"));
				map.put("realname", rs.getString("real_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//最终form重加载的数据结构类型为{id:"1",username:"111",realname:"11111"}
		response.getWriter().write(JSONObject.toJSONString(map));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
