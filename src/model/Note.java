package model;

import java.io.Serializable;

public class Note implements Serializable {
    private String content;

    public Note(){
        content = "";
    }

    public boolean hasNote(){
        return content.length() > 0;
    }

    public String getContent(){return content;}

    public void writeNote(String str){ content = str;}

    public static void main(String[] args){
        Note note = new Note();
        if (note.hasNote()){
            System.out.println("???");
        }
        note.writeNote("hihi jerry");
        if (note.hasNote()){
            System.out.println(note.getContent());
        }
    }
}
