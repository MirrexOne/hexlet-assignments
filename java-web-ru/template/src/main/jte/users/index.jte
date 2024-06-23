@import exercise.dto.users.UsersPage
@param UsersPage page

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
        <h1>Пользователи</h1>
        <table class="table table-striped">
            @for(var user : page.getUsers())
                <tr>
                    <td>
                        ${user.getId()}
                    </td>
                    <td>
                        <a href="/users/${user.getId()}"> ${user.getFirstName()} ${user.getLastName()}</a>
                    </td>
                </tr>
            @endfor
        </table>
    </main>
</div>
</body>
</html>