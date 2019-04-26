package Model;

//import com.sun.xml.internal.xsom.impl.scd.Step;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Student implements Serializable {
    private HashMap<String, String> attributes;
    private ArrayList<String> displayField;

    public Student(){
        attributes = new HashMap<>();
        displayField = new ArrayList<>();
        //displayField.add("FIRST NAME");
        displayField.add("STUDENT ID");
    }

    public void addAttribute(String key, String value){attributes.put(key, value);}
    public String getAttribute(String attributeName){ return attributes.getOrDefault(attributeName.toUpperCase(), null);}
    public ArrayList<String> getAllAttribute(){
        ArrayList<String> ret = new ArrayList<>();
        ret.add(this.getAttribute("LAST NAME")+", "+this.getAttribute("FIRST NAME"));
        ret.add(this.getAttribute("STUDENT ID"));
        ret.add(this.getAttribute("EMAIL"));
        ret.add(this.getAttribute("CLASS YEAR"));
        return ret;
    }

    public void setDisplayField(ArrayList<String> fieldToShow){
        if (displayField.size()>0)
            displayField.clear();

        for (String field : fieldToShow){
            String currField = field.toUpperCase();
            if (attributes.containsKey(currField) || currField.equals("NAME")){
                displayField.add(currField);
            }
        }
    }

    @Override
    public String toString() {
        /*
        // print each (key, value) pair in attributes
        String ret = "";
        Iterator iter = attributes.entrySet().iterator();
        while (iter.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)iter.next();
            ret += entry.getKey()+"->"+entry.getValue()+"\n";
        }
        return ret;
        */
        String ret = "";
        for (String attribute : displayField){
            if (attribute.toUpperCase().equals("NAME")){
                ret += attributes.get("LAST NAME") + ", " + attributes.get("FIRST NAME");
            } else {
                ret += attributes.get(attribute);
            }
        }
        return ret;
    }
}
