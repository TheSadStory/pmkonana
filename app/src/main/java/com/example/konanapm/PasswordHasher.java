package com.example.konanapm;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    // hash password
    public static String hashPassword(String PasswordText) {
        return BCrypt.hashpw(PasswordText, BCrypt.gensalt());
    }

    // check password
    public static boolean checkPassword(String PasswordText, String hashedPassword) {
        return BCrypt.checkpw(PasswordText, hashedPassword);
    }
}
