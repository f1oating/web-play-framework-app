package controllers;

import play.mvc.*;

public class PoemsController extends Controller {

    public Result poems(Http.Request req){
        return ok(views.html.poems.render(req.session().get("connected").orElse(null)));
    }

}
