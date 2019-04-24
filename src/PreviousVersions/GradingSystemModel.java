package PreviousVersions;

public class GradingSystemModel {
    public static void main(String[] args){
        Float weight1 = new Float(10f);
        Float weight2 = weight1;

        weight2 = 30f;
        System.out.println(weight1);
    }
}
