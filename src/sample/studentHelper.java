package sample;

import javafx.collections.ObservableList;

import java.sql.*;

public class studentHelper {

    public static boolean InsertRecord(Connection conn, String courseCode, String courseName, String profName, int stu_ID){

        try{

            Statement query = conn.createStatement();
            query.execute("INSERT INTO studentCourse(courseCode, courseName, profName, stu_ID) VALUES('"+courseCode+"','"+courseName+"','"+profName+"','"+stu_ID+"')");
            return true;

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
            return false;


        }

    }

  /*  public static boolean viewData(Connection conn, String uname) {

        try {

            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM studentInfo as a INNER JOIN studentCourse WHERE username LIKE '"+ uname +"' ");

           // query.execute("SELECT username, courseCode from studentInfo, studentCourse where studentInfo.stu_ID = studentCourse.track_ID");
            while(rs.next()) {
                String name = rs.getString("username");
               // int phone = rs.getInt("phone");
                String cCode = rs.getString("courseCode");
                String cName = rs.getString("courseName");
                System.out.format("%s, %s, %s\n",name, cCode, cName);
            }
            return true;

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
            return false;


        }


    }

            */

}
