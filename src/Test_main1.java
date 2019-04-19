import model.LeafNode;
import model.ParentNode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test_main1 {
    /*
    test various genTable (don't forget to run Test_main to generate test_course_without_score.ser
     */
    public static void main(String[] args){
        // load course from test_main
        String inputfileName = "./test_course_without_score.ser";
        ParentNode root = null;
        try {
            FileInputStream fis = new FileInputStream(inputfileName);
            ObjectInputStream objis = new ObjectInputStream(fis);
            root = (ParentNode) objis.readObject();
            System.out.println("read successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // check loaded
        root.traverse(0);

        // manually setup some score
        ParentNode hws = (ParentNode)root.getChild(0);
        LeafNode hw1 = (LeafNode) hws.getChild(0).getChild(0);
        hw1.getLeafByKey("u1").value = 10f;
        hw1.getLeafByKey("u2").value = 20f;
        hw1.getLeafByKey("u3").value = 30f;
        hw1.getLeafByKey("u4").value = 40f;
        LeafNode hw2 = (LeafNode) hws.getChild(1).getChild(0);
        hw2.getLeafByKey("u1").value = 15f;
        hw2.getLeafByKey("u2").value = 25f;
        hw2.getLeafByKey("u3").value = 35f;
        hw2.getLeafByKey("u4").value = 45f;

        ParentNode midterm = (ParentNode)root.getChild(1);
        LeafNode paper = (LeafNode) midterm.getChild(0).getChild(0);
        paper.getLeafByKey("u1").value = 40f;
        paper.getLeafByKey("u2").value = 30f;
        paper.getLeafByKey("u3").value = 20f;
        paper.getLeafByKey("u4").value = 10f;
        LeafNode design = (LeafNode) midterm.getChild(1).getChild(0);
        design.getLeafByKey("u1").value = 45f;
        design.getLeafByKey("u2").value = 35f;
        design.getLeafByKey("u3").value = 25f;
        design.getLeafByKey("u4").value = 15f;

        // check score after setting
        System.out.println("===== tree after setting scores ======");
        root.traverse(0);


        // write out test course with score
        System.out.println("\n writing out current class =====");
        String outputfileName = "./test_course_with_score.ser";
        try {
            FileOutputStream fs = new FileOutputStream(outputfileName);
            ObjectOutputStream objs = new ObjectOutputStream(fs);
            objs.writeObject(root);
            objs.close();
            System.out.println("write successfully");
        } catch (Exception e){
            e.printStackTrace();
        }

        // test various 2d array generation
        // ===== test summarizatoin (only allowed in root ) ======
        System.out.println("\n ===== testing genSummary before computing last score ======");
        ArrayList<String> studentOrder = root.getStudentPool().getPrimaryKeyAndSortBy("student id");

        ArrayList<String> displayField = new ArrayList<>(); // show only student id
        displayField.add("student id");
        displayField.add("first name");
        root.getStudentPool().setStudentDisplayInfo(displayField);

        // field row
        Object[] fieldRow = root.genFieldRowArray();
        ezShow1d(fieldRow);
        // table array
        Object[][] tableArray = root.genSummaryTableArray(studentOrder);  // ** summary only allowed at root
        ezShow2d(tableArray);


        System.out.println("\n ===== testing genSummary after computing last score ======");
        root.computeFinalScore();
        fieldRow = root.genFieldRowArray();
        ezShow1d(fieldRow);
        tableArray = root.genSummaryTableArray(studentOrder);  // ** summary only allowed at root
        ezShow2d(tableArray);

        // ===== test genScore (only allowed in children of root, ie, ParentNodes of height 2 ) ======
        System.out.println("\n ==== HWs score Table ===== ");
        fieldRow = hws.genFieldRowArray();
        ezShow1d(fieldRow);
        tableArray = hws.genScoreTableArray(studentOrder);
        ezShow2d(tableArray);

        System.out.println("\n ==== Mid score Table ===== ");
        fieldRow = midterm.genFieldRowArray();
        ezShow1d(fieldRow);
        tableArray = midterm.genScoreTableArray(studentOrder);
        ezShow2d(tableArray);

    }

    private static void ezShow1d(Object[] toshow) {
        assert(toshow != null);
        String thisline = "";
        for (int i = 0; i < toshow.length; i++) {
            String tmp = String.format("%20s", toshow[i]);
            thisline += tmp + ",";
        }
        System.out.println(thisline);
    }

    private static void ezShow2d(Object[][] tableArray){
        assert(tableArray!=null);
        String thisLine;
        for (int i=0; i<tableArray.length; i++){
            thisLine = "";
            for (int j=0; j<tableArray[0].length; j++){
                String tmp = tableArray[i][j].toString();
                tmp = String.format("%20s", tmp);
                thisLine += tmp + ",";
            }
            System.out.println(thisLine);
        }
    }
}
