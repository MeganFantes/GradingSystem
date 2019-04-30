import Model.CellInputType;
import Model.LeafNode;
import Model.ParentNode;
import Model.StudentPool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test_main {

    /*
    test basic import, copystructure
     */

    public static void main(String [] args){
        StudentPool studentPool = new StudentPool("student id");
        studentPool.importFromCsv("./real_Field.csv");

        LeafNode hw1leaf, hw2leaf, paperleaf, designleaf;
        String primaryKey = "student id";
        hw1leaf = new LeafNode();
        hw1leaf.setTotalScore(100);
        hw1leaf.setInputType(CellInputType.Raw);
        hw2leaf = new LeafNode();
        hw2leaf.setTotalScore(130);
        hw2leaf.setInputType(CellInputType.Raw);
        paperleaf = new LeafNode();
        paperleaf.setTotalScore(150);
        paperleaf.setInputType(CellInputType.Deduction);
        designleaf = new LeafNode();
        designleaf.setTotalScore(100);
        designleaf.setInputType(CellInputType.Deduction);

        ParentNode root, hw,hw1,hw2, mid, paper, design;
        paper = new ParentNode("paper exam", 55);
        paper.addChild(paperleaf);
        design = new ParentNode("design", 45);
        design.addChild(designleaf);
        mid = new ParentNode("Mid", 40);
        mid.addChild(paper);
        mid.addChild(design);
        hw1 = new ParentNode("hw1", 50);
        hw1.addChild(hw1leaf);
        hw2 = new ParentNode("hw2", 50);
        hw2.addChild(hw2leaf);
        hw = new ParentNode("HWs", 60);
        hw.addChild(hw1);
        hw.addChild(hw2);
        root = new ParentNode("Class", 100);
        root.addChild(hw);
        root.addChild(mid);
        root.connectStudentPool(studentPool);

        // main part
        System.out.println("Traversing Class =====");
        root.traverse(0);


        String fileName = "./test_course_without_score.ser";
        // serialized current course
        System.out.println("\n writing out current class =====");
        try {
            FileOutputStream fs = new FileOutputStream(fileName);
            ObjectOutputStream objs = new ObjectOutputStream(fs);
            objs.writeObject(root);
            objs.close();
            System.out.println("write successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
        root = null;
        studentPool = null;

        // load previous course back
        ParentNode prev_root = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream objis = new ObjectInputStream(fis);
            prev_root = (ParentNode) objis.readObject();
            System.out.println("read successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert(studentPool==null);

        System.out.println(" =========  loaded tree (should be full)==========");
        prev_root.traverse(0);

        // copy structure
        root = (ParentNode)prev_root.copyStructure();
        System.out.println(" =========  copied tree (no info)==========");
        root.traverse(0);

        // use new student info
        studentPool = new StudentPool(primaryKey);
        studentPool.importFromCsv("./real_Field1.csv");
        root.connectStudentPool(studentPool);
        System.out.println("=========  after connect (should be same as above)========== ");
        root.traverse(0);
        // nothing change because we yet have LeafNdoe to show student info
        // but the Parentnode should have studentpool right now

    }
}
