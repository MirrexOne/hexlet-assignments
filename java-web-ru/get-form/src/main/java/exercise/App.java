package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import org.apache.commons.lang3.StringUtils;

public final class App {


    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });


        app.get("/users", context -> {
            String term = context.queryParam("term");
            List<User> filteredUsers;
            if (term != null) {
                filteredUsers = USERS.stream()
                        .filter(u -> StringUtils.startsWithIgnoreCase(u.getFirstName(), term))
                        .toList();
            } else {
                filteredUsers = USERS;
            }

            var users = new UsersPage(filteredUsers, term);
            context.render("users/index.jte", model("users", users));
        });


        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
