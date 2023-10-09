package controllers;

import play.mvc.*;

public class ProjectsController extends Controller{

    public Result projects(Http.Request req){
        return ok(views.html.projects.render(req.session().get("connected").orElse(null)));
    }

}
