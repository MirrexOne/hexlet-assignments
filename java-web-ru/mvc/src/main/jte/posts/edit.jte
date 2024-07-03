@import exercise.dto.posts.EditPostPage
@param EditPostPage post

@template.layout.page(
content = @`
    @if(post.getErrors() != null)
        <div class="mb-3">
            <ul>
                @for(var validator : post.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        </div>
    @endif
    <h2>Editing post</h2>
    <div class="mx-auto p-4 py-md-5">
        <form action="/posts/${post.getId()}" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Title
                    <input type="text" class="form-control" name="name" value="${post.getTitle()}" />
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">Content</label>
                <textarea class="form-control" rows="20" cols="70" type="text" name="body">${post.getBody()}</textarea>
            </div>
            <input type="submit" class="btn btn-primary" value="Save changes" />
        </form>
    </div>
`
)
