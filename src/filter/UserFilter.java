package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {
	private FilterConfig fc;
	
    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("In userFiler doFilter method");
		HttpServletRequest httpRequest = (HttpServletRequest)(request);
		HttpServletResponse httpResponse = (HttpServletResponse)(response);
		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			System.out.println("Send redirect");
			httpResponse.sendRedirect("/jweb/Login");
		}
		else {
			if (session.getAttribute("user") != null) {
				chain.doFilter(request, response);
			}
			else {
				session.invalidate();
				httpResponse.sendRedirect("/jweb/Login");
			}
		}
		/* HttpServletRequest httpReq = (HttpServletRequest)(request);
		String name = httpReq.getRemoteUser();
		if (name != null) {
			fc.getServletContext().log("User: " + name);
		}
		else {
			fc.getServletContext().log("User unknown");
		}*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fc = fConfig;
	}

}