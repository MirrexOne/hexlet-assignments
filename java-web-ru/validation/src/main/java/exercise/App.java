package exercise;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;

import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.post("/articles", context -> {
            try {
                String content = context.formParamAsClass("content", String.class)
                        .check(c -> c.length() >= 10, "The article should be no shorter than 10 characters")
                        .get().trim();

                String title = context.formParamAsClass("title", String.class)
                        .check(title1 -> !ArticleRepository.existsByTitle(title1), "An article with that title already exists")
                        .check(name -> name.length() >= 2, "The name should not be shorter than two characters")
                        .get();


                var article = new Article(title, content);
                ArticleRepository.save(article);
                context.redirect("/articles");
            } catch (ValidationException exception) {
                String title = context.formParam("title");
                String content = context.formParam("content");
                var articlePage = new BuildArticlePage(title, content, exception.getErrors());
                context.status(422);
                context.render("articles/build.jte", model("articles", articlePage));
            }
        });



        app.get("/articles/build", context -> {
            var page = new BuildArticlePage();
            context.render("articles/build.jte", model("articles", page));
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
