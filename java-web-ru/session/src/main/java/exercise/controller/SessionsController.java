package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context context) {
        var userAttribute = context.sessionAttribute("user");
        var mainPage = new MainPage(userAttribute);
        context.render("index.jte", model("page", mainPage));
    }

    public static void build(Context context) {
        var loginPage = new LoginPage("", "");
        context.render("build.jte", model("login", loginPage));
    }

    public static void destroy(Context context) {
        context.sessionAttribute("user", null);
        context.status(302);
        context.redirect("/");
    }

    public static void create(Context context) {
        String name = context.formParamAsClass("name", String.class).get();
        String password = context.formParamAsClass("password", String.class).get();
        String encryptedPassword = encrypt(password);

        var user = UsersRepository.findByName(name);

        if (user != null && encryptedPassword.equals(user.getPassword())) {
            context.sessionAttribute("user", name);
            context.status(302);
            context.redirect("/");
        } else {
            String error = "Wrong username or password";
            var loginPage = new LoginPage(name, error);
            context.render("build.jte", model("login", loginPage));
        }
    }
    // END
}
