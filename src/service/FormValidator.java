package service;

public class FormValidator {
    public String validatelogin(String username,String password){
        if(username.isEmpty()||password.isEmpty()){
            return "Fill the forms!";
        } else if(username.contains(" ")||password.contains(" ")){
            return "Username or Password can't contain space.";
        } else{
            return "";
        }
    }

    public String validateSignup(String username,String password){
        if(username.isEmpty()||password.isEmpty()){
            return "Fill the forms!";
        } else if(username.length()<5||password.length()<5){
            return "Username or Password is too small.";
        } else if(username.contains(" ")||password.contains(" ")){
            return "Username or Password can't contain space.";
        }else{
            return "";
        }
    }
}
