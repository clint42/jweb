package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminNews
 */
public class AdminNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, BiConsumer<HttpServletRequest, HttpServletResponse>> getActions = new HashMap<String, BiConsumer<HttpServletRequest, HttpServletResponse>>();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNews() {
        super();
        getActions.put("edit", (request, response) -> { this.editGetAction(request, response); });
        getActions.put("add", (request, response) -> { this.addGetAction(request, response); });
        getActions.put("delete", (request, response) -> { this.deleteGetAction(request, response); });
        // TODO Auto-generated constructor stub
    }

	private void addGetAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HashMap<String, String> formValues = new HashMap<String, String>();
		request.setAttribute("formValues", formValues);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editNews.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editGetAction(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String[] segments = uri.split("/");
		if (segments.length > 4) {
			try {
				int id = Integer.parseInt(segments[5]);
				//TODO: dispatch, handle post...
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteGetAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] segments = uri.split("/");
		String lastSegment = uri.substring(uri.lastIndexOf('/') + 1).toLowerCase();
		if (lastSegment != null && lastSegment.equals("News")) {
			
		}
		else if (lastSegment != null ){
			getActions.get(lastSegment).accept(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
