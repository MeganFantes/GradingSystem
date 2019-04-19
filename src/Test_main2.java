import javafx.scene.Parent;
import javafx.scene.control.Cell;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Test_main2 {
    /*
    test computeFinalScore, treeValidation
    (don't forget to run Test_main first to gen test course file
     */
    public static void main(String[] args){
        // load course from test_main
        String inputfileName = "./test_course_with_score.ser";
        ParentNode root = null;
        try {
            FileInputStream fis = new FileInputStream(inputfileName);
            ObjectInputStream objis = new ObjectInputStream(fis);
            root = (ParentNode) objis.readObject();
            System.out.println("read successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" =====  read tree =====");
        root.traverse(0);

        root.computeFinalScore();

        // check accumulate score at each level
        ParentNode hw = (ParentNode)root.getChild(0);
        ParentNode hw1 = (ParentNode)hw.getChild(0);
        ParentNode hw2 = (ParentNode)hw.getChild(1);
        ParentNode mid = (ParentNode)root.getChild(1);
        ParentNode paper = (ParentNode)mid.getChild(0);
        ParentNode design = (ParentNode)mid.getChild(1);

        hw1.printAggregateResult();
        hw2.printAggregateResult();
        hw.printAggregateResult();

        paper.printAggregateResult();
        design.printAggregateResult();
        mid.printAggregateResult();

        root.printAggregateResult();

        // ruin tree in some setup, test every kind of error
        // 1. u3(=megan) paper exam score not entered
        // 2. design totalScore not set
        // 3. hw2 input type not set
        // 4. hw1 + hw2 weight != 100
        ((LeafNode)paper.getChild(0)).getLeafByKey("u3").setScore(Float.NaN);
        ((LeafNode)design.getChild(0)).setTotalScore(-1f);
        ((LeafNode)hw2.getChild(0)).setInputType(CellInputType.SELECT);
        hw2.setWeight(40f); // original 50

        ArrayList<String> errors = root.treeValidation(null);
        for (String error : errors){
            System.out.println(error);
        }

        /*
        expected output order :
        hw2 input type not set
        sub-category weight sum not equal to 100 in HW
        invalid score [megan] at design
        design total score not set
        (all error in leaf)
         */
    }
}
