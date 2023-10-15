package controllers;

import models.DBConnect;
import play.mvc.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletepoemController extends Controller {

    public Result deletepoem(Http.Request req, int id){
        if(req.session().get("connected").orElse(null) == null){
            return badRequest();
        }

        DBConnect db = DBConnect.getInstance();
        Connection connection = db.getConnection();

        String sql = "DELETE FROM poems WHERE id=" + id;

        try(PreparedStatement statement = connection.prepareStatement(sql)){

            int rowsAffected = statement.executeUpdate();

            return redirect("/");

        }catch(SQLException e){
            e.printStackTrace();
            return badRequest();
        }finally {
            db.closeConnection();
        }
    }
}
