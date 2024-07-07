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
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <button type="submit" class="btn btn-danger">Выйти</button>
            </form>
        @endif
    </div>
`
)