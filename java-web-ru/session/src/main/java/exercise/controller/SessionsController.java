package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;


public class SessionsController {

    // BEGIN
    public static void buildUserPage(Context context) {
        var page = new LoginPage(null, null);
        context.render("build.jte", model("login", page));
    }

    public static void createUser(Context context) {

        String name = context.formParamAsClass("name", String.class).get();
        String password = context.formParamAsClass("password", String.class).get();
        String encryptedPassword = Security.encrypt(password);

        var user = UsersRepository.findByName(name);

        if (user == null || user.getPassword().equals(encryptedPassword)) {
            var loginPage = new LoginPage(name, "Wrong username or password");
            context.render("build.jte", model("login", loginPage));
            return;
        }

        context.sessionAttribute("currentUser", name);
        context.redirect(NamedRoutes.loginPath());

    }

    public static void index(Context context) {
        Object currentUser = context.sessionAttribute("currentUser");
        var mainPage = new MainPage(currentUser);
        context.render("index.jte", model("page", mainPage));
    }

    public static void destroySession(Context context) {
        context.sessionAttribute("currentUser", null);
        context.redirect(NamedRoutes.logoutPath());
    }
    // END
}
