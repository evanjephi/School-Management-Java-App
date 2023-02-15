package sample;

import java.sql.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static Connection ConnectToDatabase(){
        try{

            Connection conn =
                    DriverManager.getConnection("jdbc:sqlite:D:\\DataBase\\StudentDatabase.db");
            return conn;

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
            return null;

        }

    }

    public static boolean CreateTable(Connection conn){
        try{

            Statement query = conn.createStatement();

            query.execute("CREATE TABLE IF NOT EXISTS studentInfo (stu_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT, email TEXT, password TEXT, confirmPassword TEXT, streetNumber TEXT, streetName TEXT, postalCode TEXT, city TEXT)");
            query.execute("CREATE TABLE IF NOT EXISTS studentCourse (course_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, courseCode TEXT, courseName TEXT, profName TEXT, stu_ID INTEGER, FOREIGN KEY (stu_ID) REFERENCES studentInfo (stu_ID))");
           // query.execute("PRAGMA foreign keys = ON");
            return true;

        }catch(SQLException e){

            System.out.println("Error: " +  e.getMessage());

            return false;
        }
    }

    public static boolean InsertRecord(Connection conn, String username, String email, String password, String confirmPassword, String streetNumber, String streetName, String postalCode, String city){

        try{

            Statement query = conn.createStatement();
            query.execute("INSERT INTO studentInfo (username, email, password, confirmPassword, streetNumber, streetName, postalCode, city) VALUES('"+username+"','"+email+"','"+password+"','"+confirmPassword+"','"+streetNumber+"','"+streetName+"','"+postalCode+"','"+ city+"')");
            return true;

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
            return false;


        }
    }
   
    public static boolean validateLogin(Connection conn, String user, String pass) throws SQLException {
       
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query = "SELECT * FROM studentInfo WHERE username = ? and password = ?";
        try{

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return  true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }



    public static boolean validateUsername(String username){

        String regex = "^[A-Za-z]\\w{5,29}$";


        Pattern p = Pattern.compile(regex);

        if(username == null){

            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();

    }

    public static boolean validateEmail(String username){

        String regex = "^([\\w.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";


        Pattern p = Pattern.compile(regex);

        if(username == null){

            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();

    }

    public static boolean validatePassword(String password){


        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern p = Pattern.compile(regex);
        if(password == null){

            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();


    }

    public static boolean validateConfirm(String a, String b){

        if(a.equals(b)){

            return true;
        }else{
            return false;
        }

    }

    public static boolean validateStNumber(String stNumber){

        String regex = "^[\\d+]+$";


        Pattern p = Pattern.compile(regex);

        if(stNumber == null){

            return false;
        }

        Matcher m = p.matcher(stNumber);

        return m.matches();

    }

    public static boolean validateStName(String stName){

        String regex = "^((?:(?:\\b[A-Za-z0-9]+[ -])+)\\w+)$";


        Pattern p = Pattern.compile(regex);

        if(stName == null){

            return false;
        }

        Matcher m = p.matcher(stName);

        return m.matches();

    }

    public static boolean validatePostalCode(String postalCode){

        String regex = "^[\\w\\s]{6,}+$";


        Pattern p = Pattern.compile(regex);

        if(postalCode == null){

            return false;
        }

        Matcher m = p.matcher(postalCode);

        return m.matches();

    }

    public static boolean validateCity(String city){

        String regex = "^[a-zA-Z\\s]+$";


        Pattern p = Pattern.compile(regex);

        if(city == null){

            return false;
        }

        Matcher m = p.matcher(city);

        return m.matches();

    }



}
