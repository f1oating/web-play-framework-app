package controllers;

import play.mvc.*;

public class HomeController extends Controller {
    public Result home(){
        return ok(views.html.home.render());
    }
    public Result poems(){
        return ok(views.html.poems.render());
    }

    public Result contacts(){
        return ok(views.html.contacts.render());
    }
}