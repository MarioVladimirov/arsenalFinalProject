// // Slier

// var slideIndex = 1;
// showSlides(slideIndex);

// // Next/previous controls
// function plusSlides(n) {
//   showSlides(slideIndex += n);
// }

// // Thumbnail image controls
// function currentSlide(n) {
//   showSlides(slideIndex = n);
// }

// function showSlides(n) {
//   var i;
//   var slides = document.getElementsByClassName("mySlides");
//   var dots = document.getElementsByClassName("dot");
//   if (n > slides.length) { slideIndex = 1 }
//   if (n < 1) { slideIndex = slides.length }
//   for (i = 0; i < slides.length; i++) {
//     slides[i].style.display = "none";
//   }
//   for (i = 0; i < dots.length; i++) {
//     dots[i].className = dots[i].className.replace(" active", "");
//   }
//   slides[slideIndex - 1].style.display = "block";
//   dots[slideIndex - 1].className += " active";
// }


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

const quiz = document.getElementById('quiz');
const answerEls = document.querySelectorAll('.answer');
const qusetionEl = document.getElementById('question');
const a_text = document.getElementById('a_text');
const b_text = document.getElementById('b_text');
const c_text = document.getElementById('c_text');
const d_text = document.getElementById('d_text');
const submitBtn = document.getElementById('submit');



let currentQuiz = 0;
let score = 0;


loadQuiz();


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


        if(answerEl.checked) {
          answer = answerEl.id;
        }
  })

  return answer;
}

submitBtn.addEventListener('click' , () => { 

  const answer = getSelected();
  if(answer) { 
    if (answer === quizData[currentQuiz].correct){
      score++;
      
    }
    currentQuiz++;
    
    
  }
  if(currentQuiz < quizData.length) { 
    loadQuiz();
  }else {
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
  
  console.log('answer',answer)
  

}

)
