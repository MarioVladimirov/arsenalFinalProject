const newsId = document.getElementById('newsId').value

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const commentForm = document.getElementById('commentForm')

const commentsCtnr = document.getElementById('commentCtnr')
commentForm.addEventListener("submit",handleCommentSubmit)

const allComments = []

const displayComments = (comments) => {
    commentsCtnr.innerHTML = comments.map(
        (c) => {
            return asComment(c)
        }
    ).join('')
}

async function handleCommentSubmit(event) {
    event.preventDefault();

    const form = event.currentTarget;

    const url = form.action;

    const formData = new FormData(form);

    try {
        const responseData = await postFormDataAsJson({url, formData});

        commentsCtnr.insertAdjacentHTML("afterbegin", asComment(responseData));

        form.reset();

    }catch (error) {

        let errorObj = JSON.parse(error.message);

        if (errorObj.fieldWithErrors) {
            errorObj.fieldWithErrors.forEach(
                e => {
                    let elementWithError = document.getElementById(e);
                    if (elementWithError) {
                        elementWithError.classList.add("is-invalid");
                    }
                }

            )
        }

    }
    console.log('Commm')
}


async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries());
    const formDataAsJSONString = JSON.stringify(plainFormData);

    const fetchOptions = {
        method: "POST",
        headers: {
            [csrfHeaderName] : csrfHeaderValue,
            "Content-Type" : "application/json",
            "Accept" :"application/json"
        },
        body: formDataAsJSONString
    }

    const response = await fetch(url, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response.json();
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
