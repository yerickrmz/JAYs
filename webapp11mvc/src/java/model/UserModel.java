package model;

import beans.User;

//Acá iría acceso a base de datos o archivos
public class UserModel {

    public User validate(String email, String password) {
        if (email.equals("wendy.ramirez@ucrso.info") && password.equals("123")) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            return user;
        } 
        return null;        
    }
}
