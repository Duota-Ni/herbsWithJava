package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dbtool.herbDB;
import model.Herb;

@WebServlet("/showListServlet")
public class showListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public showListServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter outPrintWriter = response.getWriter();
		
		//使用会话
		//HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		
		
		int pageCount =15;
		int currentPage = 1;
		
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			if(currentPage<1)
				currentPage=1;
		}
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs =null;
		List<Herb> herbs = new ArrayList<Herb>();
		int count =0;
		try {
			conn = herbDB.getConnection();
			String countSql = "select count(*) from herb";
			
			//选择当前页面的数据
			String sql = "select * from herb limit "+(currentPage-1)*pageCount+","+pageCount;
			stmt =conn.createStatement();
			rs = stmt.executeQuery(countSql);
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Herb herb = new Herb();
				int id = rs.getInt("id");
				herb.setId(id);
				String title = rs.getString("title");
				herb.setTitle(title);
				float price = rs.getFloat("price");
				herb.setPrice(price);
				int stock = rs.getInt("stock");
				herb.setStock(stock);
				String province = rs.getString("province");
				herb.setProvince(province);
				herbs.add(herb);
			}
			context.setAttribute("herbs", herbs);
			//session.setAttribute("herbs", herbs);
			//request.setAttribute("herbs",herbs);
			request.setAttribute("current", currentPage);
			request.setAttribute("max", (int)Math.ceil(((float)count)/pageCount));
			request.setAttribute("pageCount", pageCount);
			
			request.getRequestDispatcher("/showHerb.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			herbDB.close(conn,stmt,rs);
		}
		
	}

}
