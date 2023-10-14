package controllers;

import models.DBConnect;
import models.Poem;
import play.mvc.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdatepoemController extends Controller {

    public Result updatepoem(Http.Request req, int id){
        String session = req.session().get("connected").orElse(null);

        if(session != null){
            DBConnect db = DBConnect.getInstance();
            Connection connection = db.getConnection();

            String sql = "SELECT poem_name, poem_text FROM poems WHERE id=" + id;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                String poem_name = "";
                String poem_text = "";

                while (resultSet.next()) {
                    poem_name = resultSet.getString("poem_name");
                    poem_text = resultSet.getString("poem_text");
                }

                resultSet.close();
                return ok(views.html.updatepoem.render(poem_text, poem_name, id));
            } catch(SQLException e){
                e.printStackTrace();
                return badRequest();
            } finally {
                db.closeConnection();
            }
        }else{
            return badRequest();
        }
    }

    public Result updatePoemToDB(Http.Request req, int id){
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

            String sql = "UPDATE poems SET poem_name = ?, poem_text = ?, poem_date = ? WHERE id = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, poem.getPoem_name());
                statement.setString(2, poem.getPoem_text());
                statement.setString(3, poem.getPoem_date());
                statement.setInt(4, id);

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
