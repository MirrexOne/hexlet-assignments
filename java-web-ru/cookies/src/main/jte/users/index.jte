@import exercise.model.User
@import java.util.List
@param List<User> users

@template.layout.page(
content = @`
    <table class="table table-striped">
        @for(var user : users)
            <tr>
                <td>
                    ID: ${user.getId()}
                </td>
                <td>
                    TOKEN: ${String.valueOf(user.getToken())}
                </td>
                <td>
                    First name: ${user.getFirstName()}
                </td>
                <td>
                    Last name: ${user.getLastName()}
                </td>
                <td>
                    Email: ${user.getEmail()}
                </td>
                <td>
                    Password: ${user.getPassword()}
                </td>
            </tr>
         @endfor
    </table>
`
)
