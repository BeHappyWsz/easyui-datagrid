package dataGrid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 删除操作servlet
 * @author wsz
 * @date 2017年12月16日
 */
public class DeleteGridData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteGridData() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids= request.getParameter("ids");
		Connection conn = ConnUtil.getConnection();
		PreparedStatement pst = null;
		int rs =0;
		try {
			String sql = "update t_user set deleted = 1 where id in ("+ids+")";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			rs = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnUtil.clearUpdateConn(conn, pst);
		}
		response.getWriter().write((rs > 0) ? "success" : "false");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
