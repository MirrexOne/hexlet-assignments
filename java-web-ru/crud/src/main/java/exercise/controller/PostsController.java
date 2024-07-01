package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context context) {
        var page = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var pageSize = 5;

        var amountOfPosts = PostRepository.findAll(page, pageSize);

        var postsPage = new PostsPage(amountOfPosts, page);
        context.render("posts/index.jte", model("posts", postsPage));
    }

    public static void show(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));

        var postPage = new PostPage(post);
        context.render("posts/show.jte", model("post", postPage));
    }
    // END
}
