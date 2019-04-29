/*
   this class is the smallest unit in the Tree structure
   (score per student per assignment)
 */

package Model;

import java.io.Serializable;

public class Leaf implements Serializable, NoteInterface {
    public final float default_score = Float.NaN;

    private Note note;
    public Float value;

    public Leaf(){
        note = new Note();
        value = default_score;
    }

    // ======== required methods from NoteInterface =======
    public boolean hasNote(){ return note.hasNote();}

    public String getContent(){return note.getContent();}

    public void writeNote(String str){note.writeNote(str);}
    // ====================================================

    // ========== getters ===========
    public float getValue(){return value;}
    // ==============================

    // ========== setters ==========
    public void setScore(float score){value = score;}
    // ==============================

    public String toString(){
        if (value.isNaN())
            return "";
        return Float.toString(value);
    }
}