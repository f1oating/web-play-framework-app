package controllers;

import play.mvc.*;
public class LoginController extends Controller{

    public Result login(){
        return ok(views.html.login.render(""));
    }

    public Result logout(Http.Request req) {
        return redirect("/").removingFromSession(req, "connected");
    }

    public Result auth(Http.Request req){
        Http.RequestBody body = req.body();
        String username = body.asFormUrlEncoded().get("username")[0];
        String password = body.asFormUrlEncoded().get("password")[0];

        boolean validate = username.equals("f1oating") && password.equals("admin") ? true : false;

        if(validate){
            return redirect("/").addingToSession(req, "connected", "f1oating");
        }else{
            return ok(views.html.login.render("Неверный логин или пароль!"));
        }
    }
}
