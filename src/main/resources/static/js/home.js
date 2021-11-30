// // const containerNews = document.getElementById("newsContainer");
// // const allNews = [];
// //
// // displayAllNews();
// //
// //
// // function displayAllNews(){
// //
// //     $("#newsContainer").empty();
// //
// //     fetch(`http://localhost:8080/api/allnews`).
// //     then(response => response.json()).
// //     then(json => json.forEach(news => {
// //
// //         let oneNews = '<div>' +
// //             '<h2>' + news.topic +'</h2>' +
// //             '<h5>' +'от ' + news.user.username + ',' + news.localDate.toString()  +'</h5>'
// //
// //         $("#newsContainer").append(oneNews);
// //     }))
// //
// // }
// const containerNews = document.getElementById("newsContainer");
//
// const allNews = [];
//
// const displayComments = (news) => {
//     containerNews.innerHTML= news.map(
//         (n)=> {
//             return oneNews(n)
//         }
//
//     )
// }
//
// function oneNews(n) {
//     let newsHtml = `
//                   <div>
//                   <h2>${n.topic}</h2>
//                   <h5>'от' + ${n.user.username} + ${n.localDate.toString()}</h5>
//                   <div class="fakeimg">
//                       <img src="${n.urlPictureNews}" alt="" height="300px">
//                   </div>
//
//                   <button class="button" style="vertical-align:middle"><a th:href="@{/news/details/{id}(id=*{m.id})}">read more </a></button>
//
//                       <button sec:authorize="hasAnyRole('ADMIN') or hasAnyRole('MODERATOR')"
//                               class="button" style="vertical-align:middle"><a>Edit </a></button>
//
//      <hr class="line">
//               </div>
//
//
// `
//     return newsHtml;
// }
//
//
//
// fetch(`http://localhost:8080/api/allnews`).
// then(response => response.json()).
// then(data => {
//     for (let news of data) {
//         allNews.push(news)
//     }
//     displayComments(allNews)
// })