package com.example.mengyangchen.assignment1;

/**
 * Created by me7 on 1/19/17.
 */
/**
 * This class is one of the most important class that is used to store the added information
 * There are nine attributes here also nine operations
 * each one of them correspond to the real SizeBook's data
 * use get_... to use this class
*/

public class Sizebook {


    private String _name,_date,_comment;
    private String _neck,_bust,_chest,_waist,_hip,_inseam;

    public Sizebook(String name, String date,
                    String neck, String bust, String chest, String waist, String hip, String inseam,
                    String comment){
        _name = name;
        _date = date;
        _neck = neck;
        _bust = bust;
        _chest = chest;
        _waist = waist;
        _hip = hip;
        _inseam=inseam;
        _comment= comment;
    }


    public String get_name(){
        return _name;
    }
    public String get_date(){return _date;}
    public String get_neck(){return _neck;}
    public String get_bust(){return _bust;}
    public String get_chest(){return _chest;}
    public String get_waist(){return _waist;}
    public String get_hip(){return _hip;}
    public String get_inseam(){return _inseam;}
    public String get_comment(){return _comment;}

}
