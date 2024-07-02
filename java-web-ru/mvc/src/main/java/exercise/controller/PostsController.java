package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.EditPostPage;
import exercise.util.NamedRoutes;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                .check(value -> value.length() >= 2, "The name should not be shorter than two characters")
                .get();

            var body = ctx.formParamAsClass("body", String.class)
                .check(value -> value.length() >= 10, "The post should be no shorter than 10 characters")
                .get();

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", model("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    // BEGIN
    public static void edit(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found or deleted"));

        String title = post.getName();
        String body = post.getBody();
        var editingPost = new EditPostPage(title, body, null);
        context.render("posts/edit.jte", model("post", editingPost));
    }

    public static void update(Context context) {
        try {
            var id = context.pathParamAsClass("id", Long.class).get();
            var title = context.formParamAsClass("title", String.class)
                    .check(value -> value.length() >= 2, "The name should not be shorter than two characters")
                    .get();
            var body = context.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "The post should be no shorter than 10 characters")
                    .get();

            var post = PostRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Post not found or deleted"));

            post.setId(id);
            post.setName(title);
            post.setBody(body);

            context.redirect(NamedRoutes.postPath(id));
        } catch (ValidationException exception) {
            var title = context.formParam("title");
            var body = context.formParam("body");
            var post = new EditPostPage(title, body, exception.getErrors());
            context.render("posts/edit.jte", model("post", post)).status(422);
        }
    }
    // END
}
