@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage articles

@if(articles.getErrors() != null)
    <ul>
        @for(var validator : articles.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <div>
        <label>
            title
            <input type="text" required name="title" value="${articles.getTitle()}" />
        </label>
    </div>
    <div>
        <label>
            content
        </label>
        <label>
            <textarea required name="content">${articles.getContent()}</textarea>
        </label>
    </div>
    <input type="submit" value="Send">
</form>
