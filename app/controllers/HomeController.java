package controllers;

import play.mvc.*;

public class HomeController extends Controller {
    public Result home(Http.Request req){
        return ok(views.html.home.render(req.session().get("connected").orElse(null)));
    }

}