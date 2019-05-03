/**
 * This is the main class for the system. This is where the main
 * function opens the entire system and instantiates a new session.
 */

public class GradingSystem {
	public static Controller controller;
	public static final String pastCourseFolder = "./Past_Courses/";
	public static final String currCourseFolder = "./InProgress_Courses/";

	public static void main(String[] args) {
		controller = new Controller();
		LoginPage loginPage = new LoginPage(controller.getRoot());
	}
}
