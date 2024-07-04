@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    <div>
        @if(page != null)
            <div>
            ${page.getName().toString()}
            <a href="${NamedRoutes.logoutPath()}">Logout</a>
            </div>
        @else
            <div>
                <a href="${NamedRoutes.loginPath()}">Login</a>
            </div>
        @endif
    </div>
`
)
