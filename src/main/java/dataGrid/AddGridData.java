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
 * ����/�޸�servlet
 * @author wsz
 * @date 2017��12��16��
 */
public class AddGridData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddGridData() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = ConnUtil.nullToEmpty(request.getParameter("id"));
		String username = ConnUtil.nullToEmpty(request.getParameter("username"));;
		String realname = ConnUtil.nullToEmpty(request.getParameter("realname"));
		//�жϲ������͡������ڣ�Ϊ�գ�id������������Ϊ�޸ĸ���
		boolean isOk = false;
		if(!"".equals(id)) {
			isOk = updateObj(id,username,realname);
		}else {
			isOk = saveObj(username,realname);
		}
		response.getWriter().write(isOk ? "success" : "false");
		doGet(request, response);
	}
	
	/**
	 * ���²���
	 * @param id
	 * @param username
	 * @param realname
	 * @return
	 */
	private boolean updateObj(String id, String username, String realname) {
		Connection conn = ConnUtil.getConnection();
		PreparedStatement pst = null;
		int count = 0;
		if(conn != null) {
			String sql = "update t_user set username = ? , real_name = ?  where id = ?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, realname);
				pst.setInt(3, Integer.parseInt(id));
				count = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count > 0 ? true : false;
	}

	/**
	 * �������
	 * @param username
	 * @param realname
	 * @return
	 */
	public boolean saveObj(String username,String realname) {
		Connection conn = ConnUtil.getConnection();
		PreparedStatement pst = null;
		int total = 0;
		if(conn != null) {
			String sql = "insert into t_user(username,real_name,password,deleted) "
					+ " values(?,?,'123456',0)";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, realname);
				total = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ConnUtil.clearUpdateConn(conn,pst);
			}
		}else {
			System.out.println("�������ݿ�ʧ��");
		}
		return total > 0 ? true : false ;
	}

}
