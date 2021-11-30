const newsId = document.getElementById('newsId').value

const commentsCtnr = document.getElementById('commentCtnr')

const allComments = []

const displayComments = (comments) => {
    commentsCtnr.innerHTML = comments.map(
        (c) => {
            return asComment(c)
        }
    ).join('')
}

function asComment(c) {
    let commentHtml = `<div class="bg-white p-2" id="commentCntr-${c.commentId}">`

    commentHtml += `
      <div class="d-flex flex-column justify-content-start ml-2"><span
                                    class="d-block font-weight-bold name">  <i class="fas fa-user"></i> ${c.user} </span><span
                                    class="date text-black-50"> ${c.created}</span>
                            </div> `

    commentHtml += `<div class="mt-2"><p class="comment-text">${c.message}</p></div>`;

    commentHtml += `</div>`

    return commentHtml
}

fetch(`http://localhost:8080/api/${newsId}/comments`).then(response => response.json()).then(data => {
    for (let comment of data) {
        allComments.push(comment)
    }
    displayComments(allComments)
})
