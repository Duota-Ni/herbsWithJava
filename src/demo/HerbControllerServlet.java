package demo;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

@WebServlet(name="shoppingCartServlet",urlPatterns= {"/addToHerbCart","/showHerbDetail","/deleteHerbItem"})
public class HerbControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ServletContext context;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.endsWith("/showHerbDetail")) {
			showHerbDetail(request, response);
		}else if(uri.endsWith("deleteHerbItem")) {
			deleteHerbItem(request,response);
		}
	}

	

	//(1)addToCart
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int herbId =0;
		int quantity = 0;
		try {
			herbId = Integer.parseInt(request.getParameter("id"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}catch(NumberFormatException e) {
			System.out.println("addToCart的错误"+e);
		}
		Herb herb = getHerb(herbId);
		if(herb != null&& quantity >= 0) {
			HerbItem herbItem = new HerbItem(herb,quantity);
			HttpSession session = request.getSession();
			shoppingCart cart = (shoppingCart)session.getAttribute("cart");
			if(cart==null) {
				cart = new shoppingCart();
				session.setAttribute("cart",cart);;
			}
			cart.add(herbItem);
		}
		//显示购物车信息
		response.sendRedirect("showHerbCart.jsp");
		
	}

	//(2)showProduct
	private void showHerbDetail(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
		int herbId = 0;
		try {
			herbId = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e) {
			System.out.println("showHerbDetail的错误"+e);
			System.out.println(herbId);
		}
		Herb herb = getHerb(herbId);
		if(herb != null) {
			HttpSession session = request.getSession();
			session.setAttribute("herb",herb);
			response.sendRedirect("oneHerb.jsp");
	}else {
		System.out.println("No herb found");
	}
	}
	
	
	//(3)deleteItem
    private void deleteHerbItem(HttpServletRequest request, HttpServletResponse response)throws IOException {
    	HttpSession session = request.getSession();
		shoppingCart cart = (shoppingCart)session.getAttribute("cart");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			HerbItem item =null;
			for(HerbItem shopItem:cart.getItems()) {
				if(shopItem.getHerb().getId()==id) {
					item = shopItem;
					break;
				}
			}
			cart.remove(item.getHerb().getId());
		}catch(NumberFormatException e) {
			System.out.println("deleteHerbItem的错误"+e.getMessage());
			}
		session.setAttribute("cart",cart);
		response.sendRedirect("showHerbCart.jsp");
	}
	
	//(4)getProduct 根据给定的商品号获取商品信息   
	protected Herb getHerb(int herbId){
		//ArrayList<Herb> herbs =(ArrayList<Herb>)session.getAttribute("herbs");
		context=getServletContext();
		List<Herb> herbs = (List<Herb>)context.getAttribute("herbs");
		for(Herb herb:herbs) {
			if(herb.getId()==herbId) {
				return herb;
			}
		}
		return null;
	}

}
