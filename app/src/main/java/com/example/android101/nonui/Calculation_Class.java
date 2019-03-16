package com.example.android101.nonui;

public class Calculation_Class {



    private int theUserData; // چیزی که کاربر نوشته یا از فرم اول میاد تا پردازش بشه عدد است


    private Calculation_Class(){
        // یه Private تا از دسترسی بقیه به کلاس جلوگیری کنیم
        // فکر کنم ضروری نباشه
    }

    private static Calculation_Class instance; // یه نسخه از کلاس رو تعریف میکنیم
    public static Calculation_Class getInstance(){ // یه اینستنس به هر کی میخواد بده و به صورت پابلیک هست
        if (instance == null){
            instance = new Calculation_Class();
        }
        return instance;
    }



    // Getter & Setter
    public int getTheUserData() {
        return theUserData;
    }

    public void setTheUserData(int theshit) {
       this.theUserData += theshit * 2;
    }

}
