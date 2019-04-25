package Model;

import java.io.*;
import java.util.*;

public class StudentPool implements Serializable{
    // assume csv format : BUID, name, title
    private String primaryKeyName;
    private HashMap<String, Student> allStudent; // key: BUID, val: Student obj
    private HashMap<String, HashSet<String>> fieldSet; // key: field name, val: all distinct content in that field
    private String displayFieldName;

    public StudentPool(){
        primaryKeyName = "STUDENT ID";
        allStudent = new HashMap<>();
        fieldSet = new HashMap<>();
        displayFieldName = "STUDENT ID"; // default display order
    }

    public StudentPool(String primaryKeyName){
        // pass in primary key field name (need to be the same as student scv file)
        this.primaryKeyName = primaryKeyName.toUpperCase();
        allStudent = new HashMap<>();
        fieldSet = new HashMap<>();
        displayFieldName = "STUDENT ID"; // default display order
    }


    public boolean importFromCsv(String filepath){

        File file = new File(filepath);
        BufferedReader reader = null;
        boolean firstLine = true;
        ArrayList<String> fieldOrder = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                if (firstLine){
                    // first row, consists of field name
                    // all field name stored in upper case
                    firstLine = false;
                    String[] tokens = text.split(",");
                    for (String tok : tokens){
                        tok = tok.trim().toUpperCase();
                        fieldSet.put(tok, new HashSet<String>());
                        fieldOrder.add(tok);
                    }
                } else {
                    // consists of per student information
                    String[] tokens = text.split(",");
                    assert(tokens.length == fieldOrder.size());

                    // construct student object & put it inallStudent when pKey is met
                    Student currStudent = new Student();
                    for (int i=0; i<tokens.length; i++){
                        String fieldName = fieldOrder.get(i);
                        String tok = tokens[i].trim();

                        if (fieldName.equals(primaryKeyName)){
                            allStudent.put(tok, currStudent);
                        }

                        fieldSet.get(fieldName).add(tok);
                        currStudent.addAttribute(fieldName, tok);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        return true;
    }

    public void viewFieldSet(){
        System.out.println("Viewing all field -> values in this field");
        Iterator iter = fieldSet.entrySet().iterator();
        while (iter.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)iter.next();
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    public void viewAllStudent(){
        System.out.println("Viewing all student -> (student detail) ");
        Iterator iter = allStudent.entrySet().iterator();
        while (iter.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)iter.next();
            System.out.println("key="+entry.getKey()+"\n"+entry.getValue()+"\n==========");
        }
    }

    // ============ getters ============
    public String getPrimaryKey(){
        return primaryKeyName;
    }

    public ArrayList<String> getPrimaryKeyAndSortBy(String sortField){
        //final String desiredSortField = sortField.toUpperCase();
        String desiredSortField = this.displayFieldName;
        ArrayList<String> ret = new ArrayList<>();
        for (String key : fieldSet.getOrDefault(primaryKeyName, null)){
            ret.add(key);
        }

        final String defaultSortKey = new String("first name").toUpperCase();
        if (fieldSet.getOrDefault(desiredSortField, null)==null){
            System.out.println("desired sort attribute not exist, sort by " + defaultSortKey);
            Collections.sort(ret, (a,b)->{
                return getStudentByKey(a).getAttribute(defaultSortKey).compareTo(
                        getStudentByKey(b).getAttribute(defaultSortKey)
                );
            });
            return ret;
        }

        Collections.sort(ret, (a,b) -> {
            int cmpByDesired = getStudentByKey(a).getAttribute(desiredSortField).compareTo( getStudentByKey(b).getAttribute(desiredSortField) );
            if (cmpByDesired!=0)
                return cmpByDesired;
            else{
                // desired field value is the same, default then sort by last name
                return getStudentByKey(a).getAttribute(defaultSortKey).compareTo( getStudentByKey(b).getAttribute(defaultSortKey) );
            }
        } );

        return ret;
    }

    public Student getStudentByKey(String key){
        return allStudent.getOrDefault(key, null);
    }

    public ArrayList<String> getDisplayOption(){
        ArrayList<String> ret = new ArrayList<>();
        boolean nameAdded = false;
        for (HashMap.Entry<String, HashSet<String>> entry : fieldSet.entrySet()) {
            if (entry.getKey().contains("name".toUpperCase())){
                if (!nameAdded){
                    ret.add("NAME");
                    nameAdded = true;
                }
            } else {
                ret.add(entry.getKey());
            }
        }
        return ret;
    }
    // ==============================

    // ============ setters ============
    public void setStudentDisplayInfo(ArrayList<String> fieldToShow){
        assert (fieldToShow.size()==1); // only allow to show one field a time
        displayFieldName = fieldToShow.get(0);
        for (HashMap.Entry<String, Student> entry : allStudent.entrySet()) {
            entry.getValue().setDisplayField(fieldToShow);
        }
    }
    // =================================

    public static void main(String[] args){
        System.out.println("Test reading from csv");
        StudentPool studentPool = new StudentPool("student id");
        studentPool.importFromCsv("./real_Field.csv");
        studentPool.viewFieldSet();
        studentPool.viewAllStudent();

        //System.out.println("\n test sort by given field");
        //ArrayList<String> sortbyfield = studentPool.getPrimaryKeyAndSortBy("");
        //System.out.println("result:"+sortbyfield);

        /*
        try {
            FileOutputStream fs = new FileOutputStream("./testclass.ser");
            ObjectOutputStream objs = new ObjectOutputStream(fs);
            objs.writeObject(studentPool);
            objs.close();
            System.out.println("write successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
        */

    }
}
