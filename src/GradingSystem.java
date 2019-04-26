public class GradingSystem {
	public static Controller controller;

	public static void main(String[] args) {
		controller = new Controller();
		//ClassHome classHome = new ClassHome();
		LoginPage loginPage = new LoginPage(controller.getRoot());
	}
}
