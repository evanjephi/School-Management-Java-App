package sample;

import java.util.Collections;
import java.util.Set;

public class loginSession {
    private static String username;


    loginSession(){}

    public loginSession(String username) {
        this.username = username;

    }

    public static String getUserName() {
        return username;
    }

   // System.out.println("un: ", username);

}
