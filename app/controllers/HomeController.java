package controllers;

import play.libs.Files;
import play.mvc.*;

import java.nio.file.Path;
import java.nio.file.Paths;

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

    public Result projects(){
        return ok(views.html.projects.render());
    }

    public Result upload(Http.Request request) {
        Http.MultipartFormData<Files.TemporaryFile> body = request.body().asMultipartFormData();
        Http.MultipartFormData.FilePart<Files.TemporaryFile> picture = body.getFile("picture");
        if (picture != null) {
            String fileName = picture.getFilename();
            long fileSize = picture.getFileSize();
            String contentType = picture.getContentType();
            Files.TemporaryFile file = picture.getRef();
            file.copyTo(Paths.get("upload/dest.jpg"), true);
            return ok("File uploaded");
        } else {
            return badRequest().flashing("error", "Missing file");
        }
    }
}