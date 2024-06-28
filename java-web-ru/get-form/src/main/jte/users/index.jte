@import exercise.dto.users.UsersPage
@param UsersPage users

@template.layout.page(
content = @`
    <div>
        <form action="/users" method="get">
            <input type="search" name="term" value="${users.getTerm()}" />
            <input type="submit" value="Search" />
        </form>
        <h1>Users:</h1>
        <table class="table table-striped">
            @for(var user : users.getUsers())
                <tr>
                    <td>
                        ${user.getId()}
                    </td>
                    <td>
                        <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
                    </td>
                </tr>
            @endfor
        </table>
    </div>
`
)
