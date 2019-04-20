package Model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

public class LeafNode extends TreeNode {
    private HashMap<String, Leaf> allLeaf;
    private CellInputType inputType;
    private Float totalScore;

    public LeafNode(){
        studentPool = null;
        totalScore = Float.NaN;
        allLeaf = new HashMap<>();
        inputType = CellInputType.RAW;
    }

    private void generateLeafs(String primaryKeyName){
        ArrayList<String> allPrimaryKeys = studentPool.getPrimaryKeyAndSortBy(studentPool.getPrimaryKey());
        for (String key : allPrimaryKeys){
            allLeaf.put(key, new Leaf() );
        }
    }


    // ====== required methods from Model.TreeNode =======
    public void traverse(int depth){
        String padsym = "  ";
        String padding = "";
        for (int i=0; i<4*depth; i++)
            padding += padsym;
        System.out.println(padding + this);
        viewAllLeaf(padding);
    }

    public TreeNode copyStructure(){
        if (studentPool != null)
            studentPool = null;
        LeafNode retnode = new LeafNode();
        retnode.setTotalScore(totalScore);
        retnode.setInputType(inputType);
        return retnode;
    }

    public void connectStudentPool(StudentPool pool){
        assert(allLeaf.size()==0 && studentPool==null); // if not zero, meaning student already exist or pool connected
        studentPool = pool;
        generateLeafs(studentPool.getPrimaryKey());
    }

    public ArrayList<String> treeValidation(ArrayList<String> errorSofar, boolean checkRootChildrenOnly){
        assert(errorSofar.size() >= 1);

        // last element in errorSofar is the criteria of previous level
        int lastIdx = errorSofar.size() - 1;
        String lastCriteria = errorSofar.get(lastIdx);
        errorSofar.remove(lastIdx);

        // check totalScore is set, if unset, no need to check leaf percentage 100
        if (totalScore.isNaN()){
            errorSofar.add("total score not set in [" + lastCriteria+"]");
            return errorSofar;
        }

        // check input type is set
        //if (inputType == Model.CellInputType.SELECT){
        //    errorSofar.add("input type not set in " + lastCriteria);
        //}

        // check every leaf has valid score
        // NaN means unset (user intentionally)
        for (Map.Entry<String, Leaf> entry : allLeaf.entrySet()){
            Float currScore = entry.getValue().getValue();
            if (currScore==Float.NaN){
                // intentailly empty score from user
                continue;
            }
            if (currScore<0 ||
                (currScore >= totalScore && inputType != CellInputType.PERCENTAGE) ||
                (inputType==CellInputType.PERCENTAGE && currScore > 100)) {
                String studenInfo = studentPool.getStudentByKey(entry.getKey()).getAttribute("first name");
                studenInfo += " ," + studentPool.getStudentByKey(entry.getKey()).getAttribute("last name");
                errorSofar.add( "invalid score for [" + studenInfo + "] in " + lastCriteria);
            }
        }

        return errorSofar;
    }

    public HashMap<String, Float> computeFinalScore(){
        HashMap<String, Float> retmap = new HashMap<>();

        for (HashMap.Entry<String, Leaf> entry : allLeaf.entrySet()) {
            float currScore = entry.getValue().getValue();

            // compute percentage of currScore according to inputType & totalScore
            float currPercentage = 0;
            switch (inputType){
                case RAW:
                    currPercentage = currScore / totalScore * 100f;
                    break;
                case DEDUCTION:
                    currPercentage = (totalScore - currScore) / totalScore * 100f;
                    break;
                case PERCENTAGE:
                    currPercentage = currScore;
                    break;
                default:
                    assert(false);
            }

            retmap.put(entry.getKey(), new Float(currPercentage));
        }

        return retmap;
    }

    public Object[][] genTableArray(){ return null; }

    public TreeNode getChild(int childIndex){return null;}

    public boolean isLeaf(){return true;}
    // ========================================

    // ========== getters ==========
    public Leaf getLeafByKey(String key){return allLeaf.get(key);}

    public HashMap<String, Leaf> getAllLeaf(){return allLeaf;}

    public Float getWeight(){return 100f;} // one Model.ParentNode has at most 1 Model.LeafNode

    public Float getTotalScore(){return totalScore;}

    public CellInputType getInputType(){return inputType;}
    // ========================================

    // ========== setters =========
    public void setInputType(CellInputType type){inputType = type;}

    public void setTotalScore(float totalScore){this.totalScore = totalScore;}

    public void viewAllLeaf(String padding){
        for (Map.Entry<String, Leaf> entry : allLeaf.entrySet()){
            System.out.println(padding+entry.getKey()+"->"+entry.getValue());
        }
    }
    // ========================================

    @Override
    public String toString() {
        return totalScore.toString()+","+inputType.toString();
    }

    public static void main(String [] args){
        // prepare necessary data to simulate
        StudentPool studentPool = null;
        try {
            FileInputStream fis = new FileInputStream("./testclass.ser");
            ObjectInputStream objis = new ObjectInputStream(fis);
            studentPool = (StudentPool) objis.readObject();
            System.out.println("read successfully");
        } catch (Exception e){
            e.printStackTrace();
        }

        // main part
        LeafNode leafNode = new LeafNode();
        leafNode.viewAllLeaf("");
    }
}
