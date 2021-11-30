

// Quiz
const quizData = [
    {
        question: "Кога е основан ФК Арсенал ?",
        a: "1886",
        b: "1885",
        c: "1892",
        d: "1884",
        correct: "a"
    },
    {
        question: "Кой отбеляза победителя във финалния мач на Венгер",
        a: "Аарон Рамзи",
        b: "Александър Лаказет",
        c: "Дани Уелбек",
        d: "Пиер-Емерик Обамеянг",
        correct: "d"

    },

    {
        question: "Колко титли има Арсенал",
        a: "13",
        b: "12",
        c: "15",
        d: "9",
        correct: "a"
    },
    {
        question: "Коя от тези компании НЕ е спонсорирала фланелка на Арсенал?",
        a: "Sega",
        b: "Sony",
        c: "JVC",
        d: "O2",
        correct: "b"
    },
    {
        question: "Какво е официалното мото на клуба?",
        a: "Nil Satis,Nisi Optimum",
        b: "Arte et labore",
        c: "E Pluribus Unum",
        d: "Victoria Concordia Crescit",
        correct: "d"
    },
    {
        question: "Кой отбеляза драматичния победен гол, който осигури титлата в лигата през 1989 г.?",
        a: "Алън Смит",
        b: "Пол Мърсън",
        c: "Стив Боулд",
        d: "Майкъл Томас",
        correct: "d"
    },
    {
        question: "Кой отбеляза драматичния победен гол, който осигури титлата в лигата през 1989 г.?",
        a: "Парма",
        b: "Галатасарай",
        c: "Геноа",
        d: "Борусия дортмунд",
        correct: "a"
    },
    {
        question: "Колко мача във Висшата лига беше дълга серия на „Непобедимите“?",
        a: "39",
        b: "51",
        c: "49",
        d: "45",
        correct: "c"
    },
    {
        question: "Кой вкара гол за Арсенал на финала на Шампионската лига през 2006 г.?",
        a: "Робер Пирес",
        b: "Коло Туре",
        c: "Сол Кембъл",
        d: "Тиери Анри",
        correct: "c"
    },
    {
        question: "Кой вкарва първият гол за Арсенал на новият стадион Емирейтс Стейдиъм",
        a: "Еманюел Адебайор",
        b: "Жилберто",
        c: "Фабрегас",
        d: "Робин ван Перси",
        correct: "b"
    }
];
const quiz = document.getElementById('rowQuiz');
let answerEls;
let qusetionEl;
let a_text;
let b_text;
let c_text;
let d_text;
let submitBtn;

const startBtn = document.getElementById("submitStart");


let currentQuiz = 0;
let score = 0;
let allScore = 0;


startBtn.addEventListener('click', () => {

fetch(`http://localhost:8080/api/games/quest`)
    .then(response => console.log(response));

    quiz.innerHTML = `  
  <div class="quiz-container" id="quiz" style="width: 550px;">
<div class="quiz-header">
<h2 id="question">Question Text</h2>
<ul>
    <li>
        <input type="radio" name="answer" id="a" class="answer">
        <label for="a" id="a_text" >Answer</label>

    </li>
    <li>
        <input type="radio" name="answer" id="b" class="answer">
        <label for="b" id="b_text">Answer</label>

    </li>
    <li>
        <input type="radio" name="answer" id="c" class="answer">
        <label for="c" id="c_text" >Answer</label>

    </li>
    <li>
        <input type="radio" name="answer" id="d" class="answer">
        <label for="d" id="d_text" >Answer</label>

    </li>

</ul>
<button id="submit">следващ</button>
</div>
</div>`

    qusetionEl = document.getElementById('question');
    loadQuestion()
    submitBtn = document.getElementById('submit');
    answerEls = document.querySelectorAll('.answer');
    loadQuiz()

    function loadQuestion() {
        a_text = document.getElementById('a_text');
        b_text = document.getElementById('b_text');
        c_text = document.getElementById('c_text');
        d_text = document.getElementById('d_text');
    }


    function loadQuiz() {

        const currentQuizData = quizData[currentQuiz];

        qusetionEl.innerText = currentQuizData.question;
        a_text.innerText = currentQuizData.a;
        b_text.innerText = currentQuizData.b;

        c_text.innerText = currentQuizData.c;
        d_text.innerText = currentQuizData.d;

        deselectAnswers();


    }

    function deselectAnswers() {

        answerEls.forEach(answerEl => answerEl.checked = false);

    }

    function getSelected() {

        let answer;
        answerEls.forEach(answerEl => {


            if (answerEl.checked) {
                answer = answerEl.id;
            }
        })

        return answer;
    }


    submitBtn.addEventListener('click', () => {


        const answer = getSelected();
        if (answer) {
            if (answer === quizData[currentQuiz].correct) {
                score++;

            }
            currentQuiz++;


        }
        if (currentQuiz < quizData.length) {
            loadQuiz();
        } else {
            let txt1 = "";

            if (score < 4) {
                txt1 = "Да не сте фен на Тотнъм :)"
            } else if (score <= 9) {
                txt1 = "Може и по-добре"
            } else {
                txt1 = "Истински артилерист !!!!"
            }


            quiz.innerHTML = `
      <h2>Вашият резултат ${score}/${quizData.length} </h2>
      <h2>${txt1}</h2>
      <button onClick="location.reload()">Повтори</button>
      `
        }

        console.log('answer', answer)
    })

});


// const quiz = document.getElementById('quiz');
// const answerEls = document.querySelectorAll('.answer');
// const qusetionEl = document.getElementById('question');
// const a_text = document.getElementById('a_text');
// const b_text = document.getElementById('b_text');
// const c_text = document.getElementById('c_text');
// const d_text = document.getElementById('d_text');
// const submitBtn = document.getElementById('submit');
//
//
//
// let currentQuiz = 0;
// let score = 0;
//
//
// loadQuiz();
//
//
// function loadQuiz() {
//
//
//
//     const currentQuizData = quizData[currentQuiz];
//
//     qusetionEl.innerText = currentQuizData.question;
//     a_text.innerText = currentQuizData.a;
//     b_text.innerText = currentQuizData.b;
//
//     c_text.innerText = currentQuizData.c;
//     d_text.innerText = currentQuizData.d;
//
//     deselectAnswers();
// }
//
// function deselectAnswers() {
//
//     answerEls.forEach(answerEl => answerEl.checked = false);
//
// }
//
// function getSelected() {
//
//     let answer;
//     answerEls.forEach(answerEl => {
//
//
//         if (answerEl.checked) {
//             answer = answerEl.id;
//         }
//     })
//
//     return answer;
// }
//
//
//
// submitBtn.addEventListener('click', () => {
//
//         const answer = getSelected();
//         if (answer) {
//             if (answer === quizData[currentQuiz].correct) {
//                 score++;
//
//             }
//             currentQuiz++;
//
//
//         }
//         if (currentQuiz < quizData.length) {
//             loadQuiz();
//         } else {
//             let txt1 = "";
//
//             if (score < 4) {
//                 txt1 = "Да не сте фен на Тотнъм :)"
//             } else if (score <= 9) {
//                 txt1 = "Може и по-добре"
//             } else {
//                 txt1 = "Истински артилерист !!!!"
//             }
//
//
//             quiz.innerHTML = `
//     <h2>Вашият резултат ${score}/${quizData.length} </h2>
//     <h2>${txt1}</h2>
//     <button onClick="location.reload()">Повтори</button>
//     `
//         }
//
//         console.log('answer', answer)
//
//     }
// )

