package dataGrid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;


/**
 * 获取datagrid数据列表并分页
 * @author wsz
 * @date 2017年12月16日
 */
public class GetDataGrid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetDataGrid() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt("".equals(ConnUtil.nullToEmpty(request.getParameter("page"))) ? "0" : request.getParameter("page"));
		int rows = Integer.parseInt("".equals(ConnUtil.nullToEmpty(request.getParameter("rows"))) ? "0" : request.getParameter("rows"));
		String uname = ConnUtil.nullToEmpty(request.getParameter("username"));
		String rname = ConnUtil.nullToEmpty(request.getParameter("realname"));
		String conditions = "";
		if(!"".equals(uname)) {
			conditions += " and username = '"+uname+"' ";
		}
		if(!"".equals(rname)) {
			conditions += " and real_name like '%"+rname+"%' ";
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = ConnUtil.getConnection();
		page = (page < 0) ? 1 : page;
		rows = (rows < 0) ? 0 : rows;
		int start = (page-1)*rows;
		int end   = page*rows-1; 
		List<Student> list = new ArrayList<Student>();
		if(conn != null) {
			try {
				String sql = "select * from t_user where deleted=0 "+conditions+" limit ?,?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				rs = ps.executeQuery();
				int count=0;
				while(rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String realName = rs.getString("real_name");
					Student s = new Student();
					s.setId(id);
					s.setName(username);
					s.setIdcard(realName);
					list.add(s);
					count++;
				}
				DataGrid grid = new DataGrid();
				grid.setTotal(count);
				grid.setRows(list);
				response.getWriter().write(JSONObject.toJSONString(grid));
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ConnUtil.clearSelectConn(conn,ps,rs);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
