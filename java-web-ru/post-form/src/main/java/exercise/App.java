package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.Security;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/users/build", context -> {
            context.render("users/build.jte");
        });

        app.post("/users", context -> {
            String firstName = context.formParam("firstName");
            String capitalizedFirstName = StringUtils.capitalize(firstName);
            String lastName = context.formParam("lastName");
            String capitalizedLastName = StringUtils.capitalize(lastName);
            String email = context.formParam("email").trim().toLowerCase();
            String password = context.formParam("password");
            String encryptedPassword = Security.encrypt(password);

            var user = new User(capitalizedFirstName, capitalizedLastName, email, encryptedPassword);
            UserRepository.save(user);
            context.redirect("/users");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
