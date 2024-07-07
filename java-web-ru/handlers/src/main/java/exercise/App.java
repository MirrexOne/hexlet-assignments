package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/phones", context -> context.json(Data.getPhones()));
        app.get("/domains", context -> context.json(Data.getDomains()));

        return app;
    }


    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
