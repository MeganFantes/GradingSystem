package Model;

public class Dummy {
    private Object realObject;
    private String display;

    public Dummy(String str, Object realObject){
        display = str;
        this.realObject = realObject;
    }

    public Object getRealObject(){return realObject;}

    @Override
    public String toString(){
        if (display==null){
            return realObject.toString();
        }
        return display;
    }
}
