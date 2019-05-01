import Model.CellInputType;
import Model.LeafNode;
import Model.ParentNode;
import Model.StudentPool;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Test_main3 {
    // used to generate past course and course in progress
    public static final String relativePastFolder = "./Past_Courses/";
    public static final String relativeCurrFolder = "./InProgress_Courses/";

    public static void main(String[] args){
        // ====== prepare course 1 ======
        StudentPool studentPool = new StudentPool();
        studentPool.importFromCsv("./real_Field.csv");
        LeafNode hw1leaf, hw2leaf, hw3leaf, paperleaf, designleaf;
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

        ParentNode root, hw,hw1,hw2,hw3, mid, paper, design;
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
        root = new ParentNode("1987-CS007", 100);
        root.addChild(hw);
        root.addChild(mid);
        root.connectStudentPool(studentPool);

        hw1leaf.getLeafByKey("u1").value = 10f;
        hw1leaf.getLeafByKey("u2").value = 20f;
        hw1leaf.getLeafByKey("u3").value = 30f;
        hw1leaf.getLeafByKey("u4").value = 40f;

        hw2leaf.getLeafByKey("u1").value = 15f;
        hw2leaf.getLeafByKey("u2").value = 25f;
        hw2leaf.getLeafByKey("u3").value = 35f;
        hw2leaf.getLeafByKey("u4").value = 45f;

        paperleaf.getLeafByKey("u1").value = 40f;
        paperleaf.getLeafByKey("u2").value = 30f;
        paperleaf.getLeafByKey("u3").value = 20f;
        paperleaf.getLeafByKey("u4").value = 10f;

        designleaf.getLeafByKey("u1").value = 45f;
        designleaf.getLeafByKey("u2").value = 35f;
        designleaf.getLeafByKey("u3").value = 25f;
        designleaf.getLeafByKey("u4").value = 15f;

        System.out.println("===Traversing Class1===");
        root.traverse(0);

        String fileName = relativePastFolder + root.getCriteria() + ".ser";
        try {
            FileOutputStream fs = new FileOutputStream(fileName);
            ObjectOutputStream objs = new ObjectOutputStream(fs);
            objs.writeObject(root);
            objs.close();
            System.out.println("write successfully");
        } catch (Exception e){
            e.printStackTrace();
        }

        // ====== prepare course 2 (in-progress) ======
        // some features :
        // 1. hw3 total score not set
        // 2. weight sum of HWs > 100
        // 3. only score in hw1 set already
        studentPool = new StudentPool();
        studentPool.importFromCsv("./real_Field1.csv");
        hw1leaf = new LeafNode();
        hw1leaf.setTotalScore(100);
        hw1leaf.setInputType(CellInputType.Deduction);
        hw2leaf = new LeafNode();
        hw2leaf.setTotalScore(100);
        hw2leaf.setInputType(CellInputType.Deduction);
        hw3leaf = new LeafNode();
        hw3leaf.setInputType(CellInputType.Deduction);
        paperleaf = new LeafNode();
        paperleaf.setTotalScore(100);
        paperleaf.setInputType(CellInputType.Raw);
        designleaf = new LeafNode();
        designleaf.setTotalScore(50);
        designleaf.setInputType(CellInputType.Raw);

        paper = new ParentNode("", 55);
        paper.addChild(paperleaf);
        design = new ParentNode("design", 45);
        design.addChild(designleaf);
        mid = new ParentNode("Mid", 30);
        mid.addChild(paper);
        mid.addChild(design);
        hw1 = new ParentNode("hw1", 40);
        hw1.addChild(hw1leaf);
        hw2 = new ParentNode("hw2", 40);
        hw2.addChild(hw2leaf);
        hw3 = new ParentNode("hw3", 40);
        hw3.addChild(hw3leaf);
        hw = new ParentNode("HWs", 70);
        hw.addChild(hw1);
        hw.addChild(hw2);
        hw.addChild(hw3);
        root = new ParentNode("2019S_CS591oop", 100);
        root.addChild(hw);
        root.addChild(mid);

        root.connectStudentPool(studentPool);

        hw1leaf.getLeafByKey("u111").value = 11f;
        hw1leaf.getLeafByKey("u222").value = 22f;
        hw1leaf.getLeafByKey("u333").value = 33f;
        hw1leaf.getLeafByKey("u444").value = 44f;

        System.out.println("===Traversing Class2===");
        root.traverse(0);

        fileName = relativeCurrFolder + root.getCriteria() + ".ser";
        try {
            FileOutputStream fs = new FileOutputStream(fileName);
            ObjectOutputStream objs = new ObjectOutputStream(fs);
            objs.writeObject(root);
            objs.close();
            System.out.println("write successfully");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
