import javafx.scene.Parent;
import sun.plugin2.os.windows.FLASHWINFO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ParentNode extends TreeNode {
    private ArrayList<TreeNode> children;
    private String criteria;
    private Float weight;
    private HashMap<String, Float> aggregateScore; // used to record aggregation of all children

    // ========== contructors ==========
    public ParentNode(){
        studentPool = null;
        children = new ArrayList<>();
        criteria = "DEFAULT CRITERIA";
        weight = new Float(0);
        aggregateScore = new HashMap<>();
    }

    public ParentNode(String criteria, float weight){
        studentPool = null;
        children = new ArrayList<>();
        this.criteria = criteria;
        this.weight =  new Float(weight);
        aggregateScore = new HashMap<>();
    }

    // ====== required methods from TreeNode
    public void traverse(int depth){
        String padsym = "  ";
        String padding = "";
        for (int i=0; i<4*depth; i++)
            padding += padsym;

        System.out.println(padding+criteria+",weight="+weight);
        for (TreeNode child : children){
            child.traverse(depth+1);
        }
    }

    public TreeNode copyStructure(){
        ParentNode retnode = new ParentNode();
        retnode.setCriteria(criteria);
        retnode.setWeight(weight);
        for (TreeNode child : children){
            TreeNode childroot = child.copyStructure();
            retnode.addChild(childroot);
        }
        return retnode;
    }

    public void connectStudentPool(StudentPool pool) {
        if (studentPool==null){
            studentPool = pool;
        }

        for (TreeNode child : children)
            child.connectStudentPool(pool);
    }

    public ArrayList<String> treeValidation(ArrayList<String> errorSofar){
        if (errorSofar == null) {
            errorSofar = new ArrayList<>();
        } else {
            int lastIdx = errorSofar.size()-1;
            if (lastIdx>=0)
                errorSofar.remove(lastIdx);
        }

        // check children weight sum to 100
        Float totalWeight = 0f;
        for (TreeNode child : children){
            errorSofar.add(criteria); // tricky way to pass previous level criteria (to locate leaf position)
            errorSofar = child.treeValidation(errorSofar);
            totalWeight += child.getWeight();
        }
        if (!totalWeight.equals(100f))
            errorSofar.add("sub-categories weight sum in " + criteria + " not equal to 100%");

        return errorSofar;
    }

    public HashMap<String, Float> computeFinalScore(){
        // aggregate all weighted scores from children & accumulate them in aggregationScore

        // not first time of calling this function, set all score to zero & re-accumulate
        if (aggregateScore.size() > 0){
            for (HashMap.Entry<String, Float> entry : aggregateScore.entrySet()) {
                Float currScore = entry.getValue();
                currScore = 0f;
            }
        }

        HashMap<String, Float> childrenScore = null;
        for (TreeNode child : children){
            childrenScore = child.computeFinalScore();

            for (HashMap.Entry<String, Float> entry : childrenScore.entrySet()) {
                if (aggregateScore.containsKey(entry.getKey())){
                    Float newScore = aggregateScore.get(entry.getKey()) + entry.getValue();
                    aggregateScore.replace(entry.getKey(), newScore);
                } else {
                    aggregateScore.put(entry.getKey(), entry.getValue());
                }
            }
        }

        // return a new HashMap obj that multiply each score with weight in this node
        HashMap<String, Float> ret = new HashMap<>();
        for (HashMap.Entry<String, Float> entry : aggregateScore.entrySet()) {
            ret.put(entry.getKey(), new Float(entry.getValue()*weight/100));
        }

        return ret;
    }

    public Object[] genFieldRowArray(){
        Object[] fieldRow = new Object[children.size()+2];

        if (studentPool==null)
            fieldRow[0] = new String("Import student");
        else
            fieldRow[0] = new String("Student");
        int currIdx = 1;
        for (TreeNode child : children){
            fieldRow[currIdx++] = child;
        }
        fieldRow[currIdx] = "Final Percentage";

        return fieldRow;
    }

    public Object[][] genScoreTableArray(ArrayList<String> studentOrder){
        // this function can only be called when its children is LeafNode
        assert(children.size()>0 && children.get(0).isLeaf());

        // row # = # student + 4
        // col # = # children + 1
        Object[][] retTable = null;

            int numRow = 4 + studentOrder.size();
            int numCol = 1 + children.size();
            retTable = new Object[numRow][numCol];

            // info row
            retTable[0][0] = new String("studentInfo");
            for (int i=1; i<numCol; i++){
                retTable[0][i] = children.get(i-1);
            }

            // weight row
            retTable[1][0] = new String("weight");
            for (int i=1; i<numCol; i++){
                retTable[1][i] = ((ParentNode)(children.get(i-1))).getWeight();
            }

            // total row
            retTable[2][0] = new String("Total");
            for (int i=1; i<numCol; i++){
                retTable[2][i] = ((ParentNode)(children.get(i-1))).getChild(0);
            }

            // statistic row
            retTable[3][0] = new String("Average");
            for (int i=1; i<numCol; i++){
                retTable[3][i] = new String("average" + i);
            }

            // row by row student score
            int rowStart = 4;
            for (int row = rowStart; row<numRow; row++){
                String studentkey = studentOrder.get(row-rowStart);
                for (int col = 0; col<numCol; col++){
                    if (col==0) {
                        retTable[row][col] = studentPool.getStudentByKey(studentkey);
                    }
                    else{
                        retTable[row][col] = ((LeafNode)(children.get(col-1).getChild(0))).getLeafByKey(studentkey);
                    }
                }
            }

        return retTable;
    }

    public Object[][] genSummaryTableArray(ArrayList<String> studentOrder){
        // this function can only be called when its children is NOT LeafNode
        // TODO: implicit assumption : called @ root
        assert(children.size()>0 && !children.get(0).isLeaf());

        int numCol = children.size()+2; // 2 extra row : idx0 : student info / idx last : final score
        int numRow = studentOrder.size();
        Object[][] retTable = new Object[numRow][numCol];
        if (aggregateScore.size()==0){
            // yet click calc final score, fill in every score with buf
            String buf = "#";
            for(int i=0; i<numRow; i++){
                Student currStudent = studentPool.getStudentByKey(studentOrder.get(i));
                for (int j=0; j<numCol; j++){
                    if (j==0)
                        retTable[i][j] = currStudent;
                    else
                        retTable[i][j] = buf;
                }
            }
        } else {
            for(int i=0; i<numRow; i++){
                String currStudentPKey =  studentOrder.get(i);
                for (int j=0; j<numCol; j++){
                    if (j==0) { // first column is student information
                        Student currStudent = studentPool.getStudentByKey(currStudentPKey);
                        retTable[i][j] = currStudent;
                    } else if (j>0 && j<numCol-1){ // other columns are score break down
                        ParentNode currNode = (ParentNode)children.get(j-1);
                        retTable[i][j] = currNode.getAggregateScore().get(currStudentPKey);
                    } else { // last column is overall final score
                        retTable[i][j] = this.aggregateScore.get(currStudentPKey);
                    }
                }
            }
        }

        return retTable;
    }

    public boolean isLeaf(){return false;}
    // ========================================

    // ========== getters =========
    public Float getWeight(){return weight;}
    public TreeNode getChild(int childIndex){ return children.get(childIndex);}
    public ArrayList<ParentNode> getAllChildren(){
        // this function is only called when its children not leaf (either no children or parentNode)
        assert(children.size()==0 || !children.get(0).isLeaf());
        ArrayList<ParentNode> ret = new ArrayList<>();
        for (TreeNode child : children){
            ret.add((ParentNode)child);
        }
        return ret;
    }
    public StudentPool getStudentPool(){return studentPool;}
    public HashMap<String, Float> getAggregateScore(){return aggregateScore;}

    // ========================================

    // ========== setters ==========
    public void setCriteria(String criteria){this.criteria = criteria;}
    public void setWeight(float weight){this.weight = weight;}
    public void addChild(TreeNode child){
        children.add(child);
    }
    public void removeChild(int childIdx){
        children.remove(childIdx);
    }
    // ========================================

    @Override
    public String toString(){
        return criteria;
    }

    // temporay test functions
    public void printAggregateResult(){
        System.out.println("Score aggregate to -> " + criteria);
        for (HashMap.Entry<String, Float> entry : aggregateScore.entrySet()) {
            System.out.println("    " + entry.getKey() + " get " + entry.getValue() + "%");
        }
        System.out.println("\n\n");
    }
}
