package exercise.controller;

import exercise.dto.users.UserPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.apache.commons.lang3.StringUtils;

import static io.javalin.rendering.template.TemplateUtil.model;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context context) {
        String token = Security.generateToken();

        String firstName = context.formParam("firstName");
        String lastName = context.formParam("lastName");
        String email = context.formParam("email");
        String password = context.formParam("password");

        var user = new User(firstName, lastName, email, password, token);
        context.cookie("token", token);
        UserRepository.save(user);

        context.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        String tokenCookie = context.cookie("token");

        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found or deleted"));

        String userToken = user.getToken();

        if (!StringUtils.equals(tokenCookie, userToken)) {
            context.redirect(NamedRoutes.buildUserPath());
        } else {
            var userPage = new UserPage(user);
            context.render("users/show.jte", model("page", userPage));
        }
    }

    public static void index(Context context) {
        var users = UserRepository.getEntities();
        context.render("users/index.jte", model("users", users));
    }
    // END
}
