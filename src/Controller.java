import Model.LeafNode;
import Model.ParentNode;
import Model.StudentPool;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class Controller {
	private static ParentNode root;
	private Object studentNode;
	private ParentNode currentState;

	public Controller() {
		root = setRoot();
		studentNode = root.genFieldRowArray()[0];
		currentState = root;
	}

	private ParentNode setRoot() {
		String inputfileName = "./test_course_with_score.ser";
		ParentNode newRoot = null;
		try {
			FileInputStream fis = new FileInputStream(inputfileName);
			ObjectInputStream objis = new ObjectInputStream(fis);
			newRoot = (ParentNode) objis.readObject();
			System.out.println("read successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ParentNode copiedNode = (ParentNode)(newRoot.copyStructure());
		copiedNode.traverse(0);
		newRoot = copiedNode;
//		System.out.println(" =====  read tree =====");
//		root.traverse(0);
		return newRoot;
	}

//	public ParentNode getRoot() {
//		return root;
//	}

	public Object[] getClassSummaryViewHeader() {
		return root.genFieldRowArray();
	}

	public Object[][] getClassSummaryViewRows() {
		return root.genSummaryTableArray(root.getStudentPool().getPrimaryKeyAndSortBy("last name"));
	}

	public Object[] getAssignmentViewHeader(Object category) {
		return getAssignmentChild(category).genFieldRowArray();
	}

	public Object[][] getAssignmentViewRows(Object category) {
		return getAssignmentChild(category).genScoreTableArray(root.getStudentPool().getPrimaryKeyAndSortBy("last name"));
	}

	public StudentPool getStudentPool() {
		return root.getStudentPool();
	}

	public ParentNode getAssignmentChild(Object category) {
		return (ParentNode) root.getChild(getCategoryIndex(category));
	}

	public int getCategoryIndex(Object category) {
		Object[] categoriesWithoutStudent = Arrays.copyOfRange(getClassSummaryViewHeader(), 1, getClassSummaryViewHeader().length + 1);
		return Arrays.asList(categoriesWithoutStudent).indexOf(category);
	}

	public void computeFinalScore() {
		root.computeFinalScore();
	}

	public ParentNode getCurrentState() {
		return currentState;
	}

	public void setCurrentState(ParentNode newCurrentState){
		currentState = newCurrentState;
	}

	public void createChild(String criteria){
		LeafNode newLeaf = new LeafNode();
		newLeaf.connectStudentPool(root.getStudentPool());
		newLeaf.generateLeafs(GradingSystem.controller.getRoot().getStudentPool().getPrimaryKey());
		ParentNode newParent = new ParentNode();
		newParent.setCriteria(criteria);
		newParent.addChild(newLeaf);
		currentState.addChild(newParent);
	}

	public ParentNode getRoot(){
		return root;
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		ClassHome classHome = new ClassHome();
	}
}
