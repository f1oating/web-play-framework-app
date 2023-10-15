package controllers;

import models.DBConnect;
import play.mvc.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoemsController extends Controller {

    public Result poems(Http.Request req){

        List<String> poem_names = new ArrayList<String>();
        List<String> poem_dates = new ArrayList<String>();
        List<Integer> ids = new ArrayList<Integer>();

        DBConnect db = DBConnect.getInstance();
        Connection connection = db.getConnection();
        String sql = "SELECT poem_name, poem_date, id FROM poems;";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                String poem_name = resultSet.getString("poem_name");
                String poem_date = resultSet.getString("poem_date");
                int id = resultSet.getInt("id");

                poem_names.add(poem_name);
                poem_dates.add(poem_date);
                ids.add(id);
            }

            return ok(views.html.poems.render(req.session().get("connected").orElse(null), poem_names, poem_dates, ids));
        }catch(SQLException e){
            e.printStackTrace();
            return badRequest();
        }finally {
            db.closeConnection();
        }
    }

}
