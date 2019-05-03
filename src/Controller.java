import Model.LeafNode;
import Model.ParentNode;
import Model.Student;
import Model.StudentPool;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

/**
 * This class holds the CONTROLLER for the system, or the connector between the
 * model/backend and the view/frontend. The view uses the functions here to
 * access and update the model. Functions here include getters and setters,
 * as well as function calls to the model to update information or
 * update the view.
 */

public class Controller {
	private static ParentNode root;
	private ParentNode currentState;
	private boolean isCurrentClass;

	/**
	 * functions that are part of the CONSTRUCTOR
	 */

	public Controller() {
		root = new ParentNode();
		currentState = root;
		isCurrentClass = false;
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

		// simulate a new class from past course
		System.out.println(" =====  original class tree =====");
		newRoot.traverse(0);

		ParentNode copiedNode = (ParentNode)(newRoot.copyStructure());
		copiedNode.traverse(0);
		newRoot = copiedNode;
		System.out.println(" =====  new class tree =====");
		newRoot.traverse(0);
		return newRoot;
	}

	/**
	 * functions to GET components for views
	 */

	public Object[] getClassSummaryViewHeader() {
		return root.genFieldRowArray();
	}

	public Object[][] getClassSummaryViewRows() {
		StudentPool studentPool = root.getStudentPool();
		if (studentPool==null){
			return root.genSummaryTableArray(null);
		}
		return root.genSummaryTableArray(root.getStudentPool().getPrimaryKeyAndSortBy("last name"));
	}

	public Object[] getAssignmentViewHeader(Object category) {
		return getAssignmentChild(category).genFieldRowArray();
	}

	public Object[][] getAssignmentViewRows(Object category) {
		StudentPool studentPool = root.getStudentPool();
		if (studentPool==null){
			return root.genSummaryTableArray(null);
		}
		return getAssignmentChild(category).genScoreTableArray(root.getStudentPool().getPrimaryKeyAndSortBy("last name"));
	}

	/**
	 * functions to GET part of the root/model
	 */

	public ParentNode getRoot(){
		return root;
	}

	public ParentNode getCurrentState() {
		return currentState;
	}

	public StudentPool getStudentPool() {
		return root.getStudentPool();
	}

	public ParentNode getAssignmentChild(Object category) {
		//return (ParentNode) root.getChild(getCategoryIndex(category));
		return (ParentNode) category;
	}

	public boolean getIsCurrentClass() {
		return isCurrentClass;
	}

	public int getCategoryIndex(Object category) {
		Object[] categoriesWithoutStudent = Arrays.copyOfRange(getClassSummaryViewHeader(), 1, getClassSummaryViewHeader().length + 1);
		return Arrays.asList(categoriesWithoutStudent).indexOf(category);
	}

	/**
	 * functions to UPDATE the view or model
	 */

	public void computeFinalScore() {
		root.computeFinalScore();
	}

	public void createChild(String criteria){
		LeafNode newLeaf = new LeafNode();
		StudentPool studentPool = root.getStudentPool();

		if (studentPool != null) {
			newLeaf.connectStudentPool(root.getStudentPool());
			newLeaf.generateLeafs(studentPool.getPrimaryKey());
		}

		ParentNode newParent = new ParentNode();
		newParent.setCriteria(criteria);
		newParent.addChild(newLeaf);
		currentState.addChild(newParent);
	}

	public void openCourse(String pathToCourse){
		ParentNode newRoot = null;
		try {
			FileInputStream fis = new FileInputStream(pathToCourse);
			ObjectInputStream objis = new ObjectInputStream(fis);
			newRoot = (ParentNode) objis.readObject();
			System.out.println("read successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//newRoot.traverse(0);
		root = newRoot;
		currentState = newRoot;
	}

	/**
	 * functions to SET part of the root/model
	 */

	public void setRoot(ParentNode root){this.root = root;}

	public void setCurrentState(ParentNode newCurrentState){
		currentState = newCurrentState;
	}

	public void setCurrentClass(boolean currentClass) {
		isCurrentClass = currentClass;
	}
}
