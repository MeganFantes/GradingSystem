public class GradingSystem {
	public static Controller controller;
	public static final String pastCourseFolder = "./Past_Courses/";
	public static final String currCourseFolder = "./InProgress_Courses/";

	public static void main(String[] args) {
		controller = new Controller();
		//ClassHome classHome = new ClassHome();
		LoginPage loginPage = new LoginPage(controller.getRoot());
	}
}
