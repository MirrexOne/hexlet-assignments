package exercise.controller;

import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;


import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context context) {
        try {
            String name = context.formParamAsClass("name", String.class)
                    .check(title -> title.length() >= 2, "Title should be no shorter than 2 characters")
                    .get();
            String body = context.formParamAsClass("body", String.class).get();

            var newPost = new Post(name, body);
            PostRepository.save(newPost);

            context.sessionAttribute("flash", "Post successfully added");
            context.redirect(NamedRoutes.postsPath());
        } catch (ValidationException exception) {
            String name = context.formParam("name");
            String body = context.formParam("body");

            var page = new BuildPostPage(name, body, exception.getErrors());
            context.render("posts/build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context context) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts, "");
        postPage.setFlash(context.consumeSessionAttribute("flash"));
        context.render("posts/index.jte", model("page", postPage));
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
