package controllers;

import play.mvc.*;

public class ContactsController extends Controller{

    public Result contacts(Http.Request req){
        return ok(views.html.contacts.render(req.session().get("connected").orElse(null)));
    }

}
