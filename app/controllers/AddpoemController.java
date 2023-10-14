package controllers;

import models.DBConnect;
import models.Poem;
import play.mvc.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddpoemController extends Controller {

    public Result addpoem(Http.Request req){

        String session = req.session().get("connected").orElse(null);

        if(session != null){
            return ok(views.html.addpoem.render());
        }else{
            return badRequest();
        }
    }

    public Result addPoemToDB(Http.Request req){
        String session = req.session().get("connected").orElse(null);

        if(session == null){
            return badRequest();
        }

        Http.RequestBody body = req.body();
        String poem_name = body.asFormUrlEncoded().get("poem_name")[0];
        String poem_text = body.asFormUrlEncoded().get("poem_text")[0];

        if(poem_name != null && poem_text != null){
            String date = LocalDate.now().toString();
            Poem poem = new Poem(poem_name, poem_text, date);
            DBConnect db = DBConnect.getInstance();
            Connection connection = db.getConnection();

            String sql = "INSERT INTO poems(poem_name, poem_text, poem_date) VALUES (?, ?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, poem.getPoem_name());
                statement.setString(2, poem.getPoem_text());
                statement.setString(3, poem.getPoem_date());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    return redirect("/");
                } else {
                    return badRequest();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return badRequest();
            } finally {
                db.closeConnection();
            }
        }else{
            return badRequest();
        }
    }
}
