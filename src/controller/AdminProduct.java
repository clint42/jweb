package controller;

import helper.FlashMessenger;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.User;

/**
 * Servlet implementation class AdminProduct
 */
public class AdminProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void addProductGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HashMap<String, String> formValues = new HashMap<String, String>();
    	formValues.put("name", "");
    	formValues.put("price", "");
    	formValues.put("quantity", "");
    	formValues.put("description", "");
    	request.setAttribute("formValues", formValues);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product.jsp");
    	dispatcher.forward(request, response);
    	return ;
    }
    
    private void addProductPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if (session != null && request.getParameter("name") != null && request.getParameter("description") != null && request.getParameter("price") != null && request.getParameter("quantity") != null) {
    		User user = (User)session.getAttribute("user");
    		try {
    			double price = Double.parseDouble(request.getParameter("price"));
    			int quantity = Integer.parseInt(request.getParameter("quantity"));
    			Product product = new Product(request.getParameter("name"), request.getParameter("description"), price, quantity, user.getId());
    	    	if (product.saveToDb()) {
    	    		FlashMessenger.getMessenger(session).addSuccessMessage("Product correctly added to database");
    	    	}
    	    	else {
    	    		FlashMessenger.getMessenger(session).addErrorMessage("An error occurred while saving product to database");
    	    	}
    	    	response.sendRedirect("/jweb/Admin/Products/Add");
    	    	return ;
    		} catch (NumberFormatException e) {
    			FlashMessenger.getMessenger(session).addErrorMessage("Price or quantity are not valid numbers");
    			response.sendRedirect("/jweb/Admin/Products/Add/");
    			return ;
    		}
    	}
    	FlashMessenger.getMessenger(request.getSession()).addErrorMessage("Request parameters are invalid");
    	response.sendRedirect("/jweb/Admin/Products/Add");
    	return ;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] segments = uri.split("/");
		String routeSegment = segments[4];
		if (routeSegment == null || (routeSegment != null && (routeSegment.equals("") || routeSegment.toLowerCase().equals("add")))) {
			this.addProductGet(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] segments = uri.split("/");
		String routeSegment = segments[4];
		if (routeSegment == null || (routeSegment != null && (routeSegment.equals("") || routeSegment.toLowerCase().equals("add")))) {
			this.addProductPost(request, response);
		}
	}

}
