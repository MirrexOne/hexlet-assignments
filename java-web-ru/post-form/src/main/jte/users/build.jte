@import exercise.dto.users.UsersPage
@param UsersPage user

@template.layout.page(
    content = @`
    <form action="/users" method="post">
        <div>
            <label>
            firstName
            <input type="text" name="firstName" />
            </label>
        </div>
        <div>
            <label>
                lastName
                <input type="text" name="lastName" />
            </label>
        </div>
        <div>
            <label>
                email
                <input type="email" required name="email" />
            </label>
        </div>
        <div>
            <label>
                password
                <input type="password" required name="password" />
            </label>
        </div>
        <div>
            <label>
                password confirmation
                <input type="password" required name="passwordConfirmation" />
            </label>
        </div>
        <input type="submit" value="Registration">
    </form>
`
)