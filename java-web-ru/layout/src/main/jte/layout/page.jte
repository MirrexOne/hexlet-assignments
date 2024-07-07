@import gg.jte.Content

@param Content content
@param Content footer = null


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<header>
    <p>
        <a href="/">Main page</a>
        <a href="/users"> All users</a>
    </p>
</header>
<main>
    <div class="mx-auto p-4 py-md-5">
        ${content}
    </div>
</main>
@if (footer != null)
    <footer class="text-center text-lg-start bg-light text-muted">
        <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
            ${footer}
            <a href="https://github.com/MirrexOne">Github profile</a>
        </div>
    </footer>
@endif
</body>
</html>

