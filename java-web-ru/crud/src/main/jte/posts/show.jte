@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage post

@template.layout.page(
content = @`
    <div class="container">
        <div class="d-flex justify-content-end">
            <a href="${NamedRoutes.postsPath()}" class="btn btn-secondary mb-4">Back to all posts list</a>
        </div>
        <div class="text-center">
            <h2 class="my-4">${post.getPost().getName()}</h2>
            <div class="mb-4">
                ${post.getPost().getBody()}
            </div>
        </div>
    </div>
`
)
