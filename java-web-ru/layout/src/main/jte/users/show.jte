@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <h2>${page.getUser().getId()} ${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h2>
    <div>${page.getUser().getEmail()}</div>
`,
footer = @`
    <p>One of the many, many users</p>
`
)