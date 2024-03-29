package controller;

import helper.FlashMessenger;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 * Servlet implementation class Product
 */
public class UserProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getPathInfo();
		String[] segments = uri.split("/");
		if (segments.length > 1) {
			try {
				int productId = Integer.parseInt(segments[1]);
				Product product = new Product();
				if (product.fetchById(productId)) {
					request.setAttribute("product", product);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
					dispatcher.forward(request, response);
					return ;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return ;
			}
			FlashMessenger.getMessenger(request.getSession()).addErrorMessage("Requested product doesn't exist");
			response.sendRedirect("/jweb/Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
