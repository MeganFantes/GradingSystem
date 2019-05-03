package PreviousVersions;

import Model.LeafNode;
import Model.ParentNode;
import Model.Statistics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test_main2 {
    /*
    test computeFinalScore, treeValidation
    (don't forget to run PreviousVersions.Test_main first to gen test course grading criteria file
                  and then PreviousVersions.Test_main1 to gen complete tree (including scores)
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

        // check statistics for aggregation
        System.out.println(" =====  HW statistics =====");
        Statistics stobj = new Statistics(hw.getAggregateScore());
        ezPrintHashmap(stobj.computeStatistics());
        // check statistics for original column
        System.out.println(" =====  hw1 (raw) statistics =====");
        stobj = new Statistics((LeafNode)hw1.getChild(0));
        ezPrintHashmap(stobj.computeStatistics());
        System.out.println(" =====  written exam (deduct)  statistics =====");
        stobj = new Statistics((LeafNode)paper.getChild(0));
        ezPrintHashmap(stobj.computeStatistics());

        // ruin tree in some setup, test every kind of error
        // 1. u3(=megan) paper exam score not entered
        // 2. design totalScore not set
        // 4. hw1 + hw2 weight != 100
        ((LeafNode)paper.getChild(0)).getLeafByKey("u3").setScore(Float.NaN); // no longer a error
        ((LeafNode)design.getChild(0)).setTotalScore(Float.NaN);
        hw2.setWeight(40f); // original 50
        ((LeafNode)(hw1.getChild(0))).getLeafByKey("u1").setScore(110f); // explosion percentage
        System.out.println(" \n=====  error msg test (root only )=====");
        ArrayList<String> errors = root.treeValidation(null, true);
        for (String error : errors){
            System.out.println(error);
            // this shouldn't show anything, cuz we only check ParentNode(ie, sum to 100)
        }

        System.out.println(" \n=====  error msg test (whole tree )=====");
        /*
        expected output order :
        invalid score for [yuehying, lee] in hw1
        sub-category weight sum not equal to 100 in HW
        design total score not set
         */
        errors = root.treeValidation(null, false);
        for (String error : errors){
            System.out.println(error);
        }

    }

    private static void ezPrintHashmap(HashMap<String, Float> ip){
        for (Map.Entry<String, Float> entry : ip.entrySet()) {
            System.out.println(entry.getKey() +" -> " + entry.getValue());
        }
    }
}
