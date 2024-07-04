@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage login

@template.layout.page(
content = @`
    @if(login.getName() != null)
        <div>${login.getError()}</div>
    @endif
    <form action="${NamedRoutes.loginPath()}" method="post">
        <input type="text" placeholder="First name" name="name"/>
        <input type="password" placeholder="Password" name="password">
        <input type="submit">
    </form>
`
)