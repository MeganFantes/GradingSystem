/*
   this file declares methods need to be implemented for every class which
   support writing a Note
 */

package Model;

public interface NoteInterface {
    public boolean hasNote();
    public String getContent();
    public void writeNote(String str);
}
