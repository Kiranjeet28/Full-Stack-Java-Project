package dao;

import db.Myconnection;
import model.User;
import model.UserScore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAO {
    public static boolean isExist(String email,String User)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from "+User);
        ResultSet rs =  ps.executeQuery();
       while (rs.next()){
           String e = rs.getString("email");
           if(e.equals(email)) {
               return true;
           }
       }
       return false;

    }

    public static boolean isPassword(String email,String Password,String User)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from "+User);
        ResultSet rs =  ps.executeQuery();
        while (rs.next()){
           String e = rs.getString("Password");
           String f = rs.getString("email");
           if(e.equals(Password)&&f.equals(email)) {
               return true;
           }
        }
      return false;

    }

    public static boolean isName(String email,String Lang,String Name)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from score");
        ResultSet rs =  ps.executeQuery();
        while (rs.next()) {
            String f = rs.getString("Email");
            String j = rs.getString("Lang");
            String k = rs.getString("Name");
            if (f.equals(email) && j.equals(Lang) && k.equals(Name)) {

                return false;
            }
        }

        return true;
    }


    public static int saveUser(User user) throws SQLException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("insert into users(name,email,password) values(?,?,?) ");
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
        ps.setString(3,user.getPassword());
        return ps.executeUpdate();

    }
    public static int saveScore(UserScore userScore) throws SQLException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("insert into score(Name,Email,Lang,Score) values(?,?,?,?) ");
        ps.setString(1, userScore.getName());
        ps.setString(2, userScore.getEmail());
        ps.setString(3, userScore.getLang());
        ps.setInt(4,userScore.getScore());
        return ps.executeUpdate();
    }

    public static void LanguageScore(String Email,String Lang)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from score");
        ResultSet rs =  ps.executeQuery();
        System.out.println("_____________________");
        System.out.printf("| %-10s | %-6s%n", "Name", "Score |");
        System.out.println("|--------------------|");

        while (rs.next()){
            String f = rs.getString("Email");
            String g = rs.getString("Lang");
            String i = rs.getString("Name");
            int h = rs.getInt("Score");

            if(f.equals(Email)&&g.equals(Lang)) {
                System.out.printf("| %-10s | %-6s",i,h);
                System.out.println("|");

            }

        }
        System.out.println("|--------------------|");

    }
    public static String TotalScore(String Email) throws SQLException {
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from score");
        ResultSet rs = ps.executeQuery();
        System.out.println("__________________________________");
        System.out.printf("|%-12s | %-8s|%-7s%n", "Name", "Score", "Lang    |");
        System.out.println("|--------------------------------|");

        while (rs.next()) {
            String f = rs.getString("Email");
            String g = rs.getString("Lang");
            String i = rs.getString("Name");
            int h = rs.getInt("Score");

            if (f.equals(Email)) {
                System.out.printf("|%-12s | %-8d| %-7s", i, h, g);
                System.out.println("|");
            }
        }
        System.out.println("----------------------------------");


        return Email;
    }
    public static void UserDetails(String table)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from "+table);
        ResultSet rs =  ps.executeQuery();
        System.out.println("________________________________________");
        System.out.printf(" %-20s  %-20s%n", "Name", "Email ");
        System.out.println("|---------------------------------------|");

        while (rs.next()){
            String f = rs.getString("Email");
            String i = rs.getString("Name");
                System.out.printf("| %-10s | %-21s",i,f);
                System.out.println("|");

        }
        System.out.println("|---------------------------------------|");

    }

}
