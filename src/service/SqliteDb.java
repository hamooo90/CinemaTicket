package service;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class SqliteDb {
    public static Connection connection = null;
    public static Statement stmt = null;
    public static UsersModel usersModel = new UsersModel();

    Util util = new Util();
    public SqliteDb(){
        if(connection == null){
            connection = connector();
            if(connection == null) {
                System.exit(1);
            }
        }
    }

    ////////// implements DB connection
    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:CinemaTicketDB.sqlite");
            conn.setAutoCommit(false);
            return conn;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    ///////// implements user login
    public boolean login(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from users where username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                usersModel.UsersModel(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    ///////// implements user register
    public boolean signup(String user, String pass) throws SQLException {
        String query = "INSERT INTO users (username,password) VALUES ('"+user+"', '" + pass + "');";
        String createTableUser = "CREATE TABLE tbl_"+user+" ( id INTEGER NOT NULL, seat INTEGER,"+
                "movie TEXT, cinema TEXT, time TEXT, hall TEXT, PRIMARY KEY( id AUTOINCREMENT ));";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.commit();

            stmt = connection.createStatement();
            /////create a table for user ticket bought history
            /////when there is a new register
            stmt.executeUpdate(createTableUser);
            stmt.close();
            connection.commit();
            ///////login after registering
            login(user,pass);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /////////get name of cinemas
    public ArrayList<CinemaModel> getCinemaNames() {
        Statement stmt = null;
        ArrayList<CinemaModel> c = new ArrayList<CinemaModel>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cinema;");
            while (rs.next()) {
                c.add(new CinemaModel(rs.getInt("id"),rs.getString("name"),
                        rs.getString("address"),rs.getString("img")));
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    ///////get list of movie in selected cinema
    public ArrayList<MovieModel> getMovies() {
        Statement stmt = null;
        ArrayList<MovieModel> c = new ArrayList<MovieModel>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movies where cinema='"+
                    util.getCurrentCinema()+"';");
            while (rs.next()) {
                c.add(new MovieModel(rs.getInt("id"),rs.getString("name"),
                        rs.getString("cinema"),rs.getString("hall"),
                        rs.getString("time"),rs.getString("about"),
                        rs.getString("director"),rs.getString("actor1"),
                        rs.getString("actor2"),rs.getString("actor3"),
                        rs.getString("genre"),rs.getString("img")));
                int a = rs.getInt("id");
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    ///////get list of searched movie
    public ArrayList<MovieModel> searchMovie(String search) {
        Statement stmt = null;
        ArrayList<MovieModel> c = new ArrayList<MovieModel>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movies where name like '%"+search+"%';");
            while (rs.next()) {
                c.add(new MovieModel(rs.getInt("id"),rs.getString("name"),
                        rs.getString("cinema"),rs.getString("hall"),
                        rs.getString("time"),rs.getString("about"),
                        rs.getString("director"),rs.getString("actor1"),
                        rs.getString("actor2"),rs.getString("actor3"),
                        rs.getString("genre"),rs.getString("img")));
                int a = rs.getInt("id");
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    ///////get list of bought ticket
    public ArrayList<UserHistoryModel> getUserHistory() {
        Statement stmt = null;
        ArrayList<UserHistoryModel> c = new ArrayList<UserHistoryModel>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_"+usersModel.getUsername()+";");
            while (rs.next()) {
                c.add(new UserHistoryModel(rs.getInt("id"),rs.getString("movie"),rs.getString("time"),
                        rs.getString("cinema"),rs.getString("hall"),
                        rs.getString("seat")));
                int a = rs.getInt("id");
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    ///////get single movie info
    public MovieModel getMovieInfo() {
        Statement stmt = null;
        MovieModel movieModel = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movies where cinema='"+
                    util.getCurrentCinema()+"' and name='"+ util.getCurrentMovie()+"';");

            if(rs.next()){
                movieModel=  new MovieModel(rs.getInt("id"),rs.getString("name"),
                        rs.getString("cinema"),rs.getString("hall"),
                        rs.getString("time"),rs.getString("about"),
                        rs.getString("director"),rs.getString("actor1"),
                        rs.getString("actor2"),rs.getString("actor3"),
                        rs.getString("genre"),rs.getString("img"));
            }
            rs.close();
            stmt.close();
            return movieModel;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<MovieModel> getMovieTime() {
        Statement stmt = null;
        ArrayList<MovieModel> c = new ArrayList<MovieModel>();
        MovieModel movieModel = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movies where cinema='"+
                    util.getCurrentCinema()+"' and name='"+ util.getCurrentMovie()+"';");
            while (rs.next()) {
                c.add(new MovieModel(rs.getInt("id"),rs.getString("name"),
                        rs.getString("cinema"),rs.getString("hall"),
                        rs.getString("time"),rs.getString("about"),
                        rs.getString("director"),rs.getString("actor1"),
                        rs.getString("actor2"),rs.getString("actor3"),
                        rs.getString("genre"),rs.getString("img")));
                int a = rs.getInt("id");
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //////insert bought ticket
    public boolean insertToUserHistory(String seats) throws SQLException {
        String query = "INSERT INTO tbl_"+usersModel.getUsername()+" (seat,movie,cinema,time,hall) VALUES ('"+seats+
                "', '" + util.getCurrentSelectedHall().getMovie() +
                "', '" + util.getCurrentSelectedHall().getCinema() +
                "', '" + util.getCurrentSelectedHall().getTime() +
                "', '" + util.getCurrentSelectedHall().getHall() + "');";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.commit();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
//        System.out.println("user Signed up");
    }

    //////update seat table
    public boolean updateReservedTime(String movieTimeTable,int index, int value) throws SQLException {
//        if(!isDbConnected()){
//            connector();
//        }
//        String query = "INSERT INTO " + movieTimeTable + " (s" + index +
//                ") VALUES ('"+value+"');";
        String query = "UPDATE " + movieTimeTable + " SET s" + index +
                " = " + value + " WHERE s1 =(SELECT s1 FROM " + movieTimeTable + " ORDER BY s1 LIMIT 1);";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.commit();
//            login(user,pass);//////to get usersmodel
//            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
//        System.out.println("user Signed up");
    }

    public SeatModel getSeats() {
        Statement stmt = null;
//        ArrayList<MovieModel> c = new ArrayList<MovieModel>();
        SeatModel seatModel = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from t" + util.getCurrentSelectedHall().getId() +
                    ";");
//            while (rs.next()) {
//                c.add(new MovieModel(rs.getInt("id"),rs.getString("name"),
//                        rs.getString("cinema"),rs.getString("hall"),
//                        rs.getString("time"),rs.getString("about"),
//                        rs.getString("director"),rs.getString("actor1"),
//                        rs.getString("actor2"),rs.getString("actor3"),
//                        rs.getString("genre"),rs.getString("img")));
//                int a = rs.getInt("id");
//                System.out.println(rs.getInt("id"));
////                System.out.println(rs.getString("name"));
//            }

            if(rs.next()){
                System.out.println(rs.getInt("s1"));
                seatModel =  new SeatModel(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),
                        rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),
                        rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),
                        rs.getInt(15),rs.getInt(16),rs.getInt(17),rs.getInt(18),rs.getInt(19),
                        rs.getInt(20),rs.getInt(21),rs.getInt(22),rs.getInt(23),rs.getInt(24),
                        rs.getInt(25),rs.getInt(26),rs.getInt(27),rs.getInt(28),rs.getInt(29),
                        rs.getInt(30),rs.getInt(31),rs.getInt(32),rs.getInt(33),rs.getInt(34),
                        rs.getInt(35),rs.getInt(36),rs.getInt(37),rs.getInt(38),rs.getInt(39),
                        rs.getInt(40));
            }

            rs.close();
            stmt.close();
            return seatModel;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void logout(){
        usersModel.clear();
    }


}
