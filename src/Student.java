import com.sun.xml.internal.xsom.impl.scd.Step;

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
        displayField.add("FIRST NAME");
        displayField.add("STUDENT ID");
    }

    public void addAttribute(String key, String value){attributes.put(key, value);}
    public String getAttribute(String attributeName){ return attributes.getOrDefault(attributeName.toUpperCase(), null);}

    public void setDisplayField(ArrayList<String> fieldToShow){
        if (displayField.size()>0)
            displayField.clear();

        for (String field : fieldToShow){
            String currField = field.toUpperCase();
            if (attributes.containsKey(currField)){
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
            ret += attributes.get(attribute) + "/";
        }
        return ret;
    }
}
