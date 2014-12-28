package helper;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * This class provide a way to notify user (via views), using session stored message.
 * On each views, a loop display all errors and success message present in the ArrayList and empty them.
 * @author prieur_b
 *
 */
public class FlashMessenger {
	private static FlashMessenger instance = null;
	private ArrayList<String> errors = null;
	private ArrayList<String> success = null;
	private HttpSession session = null;
	
	private FlashMessenger(HttpSession session) {
		this.session = session;
		this.errors = new ArrayList<String>();
		this.success = new ArrayList<String>();
	}
	
	/**
	 * Get the instance (as this class fllow the singleton pattern)
	 * @param session
	 * @return
	 */
	static public FlashMessenger getMessenger(HttpSession session) {
		if (instance == null || instance.session != session) {
			instance = new FlashMessenger(session);
		}
		return instance;
	}
	
	/**
	 * Add a message in the error message ArrayList (errors)
	 * @param msg
	 */
	public void addErrorMessage(String msg) {
		errors.add(msg);
		session.setAttribute("errorsMsg", errors);
	}
	
	/**
	 * Add a message in the success message ArrayList (errors)
	 * @param msg
	 */
	public void addSuccessMessage(String msg) {
		success.add(msg);
		session.setAttribute("successMsg", success);
	}
}
