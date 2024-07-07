@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage posts

@template.layout.page(
content = @`
    <div class="container">
        <h1 class="my-4">List of posts</h1>
        <a href="${NamedRoutes.buildPostPath()}" class="btn btn-primary mb-3">New post</a>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th>Id</th>
                    <th>Post name</th>
                </tr>
                </thead>
                <tbody>
                @if(!posts.getPosts().isEmpty())
                    @for(var post : posts.getPosts())
                        <tr>
                            <td>${post.getId()}</td>
                            <td><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></td>
                        </tr>
                    @endfor
                @else
                    <tr>
                        <td colspan="2" class="text-center">No posts available</td>
                    </tr>
                @endif
                </tbody>
            </table>
        </div>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item">
                    <a class="page-link" href="?page=${posts.getPage() < 2 ? 1 : posts.getPage() - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo; Back</span>
                    </a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="${NamedRoutes.postsPath()}?page=${posts.getPage()}">${posts.getPage()}</a></li>
                <li class="page-item">
                    <a class="page-link" href="?page=${posts.getPage() + 1}" aria-label="Next">
                        <span aria-hidden="true">Next &raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
`
)
