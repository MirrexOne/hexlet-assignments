@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <table>
        @for(var page : page.getPosts())
            <tr>
                <td>
                    ${page.getId()}
                </td>
            </tr>
            <tr>
                <td>
                    ${page.getName()}
                </td>
            </tr>
            <tr>
                <td>
                    ${page.getBody()}
                </td>
            </tr>
        @endfor
    </table>
`
)
