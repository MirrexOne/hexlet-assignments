package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;

import exercise.util.Security;
import io.javalin.http.Context;


public class SessionsController {

    // BEGIN
    public static void createUser(Context context) {

        String name = context.formParamAsClass("name", String.class).get();
        String password = context.formParamAsClass("password", String.class).get();
        String encryptedPassword = Security.encrypt(password);

        var user = UsersRepository.findByName(name);

        if (!user.getPassword().equals(encryptedPassword) || !user.getName().equals(name)) {
            var loginPage = new LoginPage(user.getName(), "Wrong username or password");
            context.render("build.jte", model("login", loginPage));
            return;
        }

        context.sessionAttribute("currentUser", name);

    }

    public static void destroySession(Context context) {
        context.sessionAttribute("currentUser", null);
        context.redirect("");
    }

    public static void buildUserPage(Context context) {
        context.render("build.jte");
    }
    // END
}
