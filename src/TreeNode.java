import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class TreeNode implements Serializable, NoteInterface{
    protected Note note;
    protected StudentPool studentPool;

    public TreeNode(){
        note = new Note();
        studentPool = null;
    }

    public abstract void traverse(int depth);
    public abstract TreeNode copyStructure();
    public abstract void connectStudentPool(StudentPool studentPool);
    public abstract TreeNode getChild(int childIndex);
    public abstract ArrayList<String> treeValidation(ArrayList<String> errorSofar);
    public abstract HashMap<String, Float> computeFinalScore();
    public abstract Float getWeight();
    public abstract boolean isLeaf();

    // NoteInterface functions
    public boolean hasNote(){ return note.hasNote();}
    public String getContent(){return note.getContent();}
    public void writeNote(String str){note.writeNote(str);}
}
