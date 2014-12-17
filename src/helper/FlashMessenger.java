package helper;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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
	
	static public FlashMessenger getMessenger(HttpSession session) {
		if (instance == null || instance.session != session) {
			instance = new FlashMessenger(session);
		}
		return instance;
	}
	
	public void addErrorMessage(String msg) {
		errors.add(msg);
		session.setAttribute("errorsMsg", errors);
	}
	
	public void addSuccessMessage(String msg) {
		success.add(msg);
		session.setAttribute("successMsg", success);
	}
}
