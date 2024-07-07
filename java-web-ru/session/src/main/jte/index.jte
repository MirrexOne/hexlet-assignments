@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
content = @`
    <div>
        @if(page.getName() == null)
            <a href="${NamedRoutes.buildSessionPath()}">Log in</a>
        @else
            ${String.valueOf(page.getName())}
            <a href="${NamedRoutes.logoutPath()}">Log out</a>
        @endif
    </div>
`
)