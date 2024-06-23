@import exercise.dto.users.UserPage
@param UserPage page
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <h2>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
        <div>${page.getUser().getEmail()}</div>
      </main>
    </div>
  </body>
</html>