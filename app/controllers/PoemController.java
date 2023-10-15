package controllers;

import models.DBConnect;
import play.mvc.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PoemController extends Controller {

    public Result poem(Http.Request req, int id){
        DBConnect db = DBConnect.getInstance();
        Connection connection = db.getConnection();

        String sql = "SELECT poem_name, poem_text, poem_date FROM poems WHERE id=" + id;

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            String poem_name = "";
            String poem_text = "";
            String poem_date = "";

            while(resultSet.next()){
                poem_name = resultSet.getString("poem_name");
                poem_text = resultSet.getString("poem_text");
                poem_date = resultSet.getString("poem_date");
            }

            resultSet.close();
            return ok(views.html.poem.render(req.session().get("connected").orElse(null), poem_name, poem_text, poem_date, id));

        }catch(SQLException e){
            e.printStackTrace();
            return badRequest();
        }
    }
}
