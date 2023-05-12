"use strict";

const answer1 = prompt(`Q1 - Will there be anyone else at the meeting?
1. Yes, they will.
2. Yes, there will.
3. Yes, anyone will.
Enter the number of the correct answer.`);

const answer2 = prompt(`Q2 - Have there been any phonecalls for me?
1. Yes, it has.
2. Yes, there has.
3. Yes, there have.
Enter the number of the correct answer.`);

const answer3 = prompt(`Q3 - Would you like some?
1. Yes, I'd.
2. Yes, I'd like.
3. Yes, I would.
Enter the number of the correct answer.`);

const answer4 = prompt(`Q4 - Has she gone?
1. Yes, she's.
2. Yes, she gone.
3. Yes, she have.
4. Yes, she has.
Enter the number of the correct answer.`);

const answer5 = prompt(`Q5 - Will they be there?
1. No, they won't.
2. No, there won't.
3. Yes, they are.
Enter the number of the correct answer.`);

const userAnswers = [answer1, answer2, answer3, answer4, answer5];
const rightAnswers = [2, 3, 3, 4, 1];
const rightAnswersString = `Right answers are:
${rightAnswers.join(";   ")}.`
const userAnswersString = `Your answers:
${userAnswers.join(";   ")}.`

function getGrade(){
    let correctAnswersNumber = 0;
    for(let i = 0; i < 5; i++) {
        if(Number(userAnswers[i]) === rightAnswers[i]) correctAnswersNumber++;
    }
    const grade = correctAnswersNumber/5 * 100;
    if(grade === 100){
        return "Congratulations, you got 100% correct!"
    }
    return `Your Grade: ${grade}%`;
}

document.write(`<pre style="font: 500 16px Montserrat">
<h2>${rightAnswersString}</h2>
<h2>${userAnswersString}</h2></pre>
<h2>${getGrade()}</h2>`);

/*
alert(`${rightAnswersString}
${userAnswersString}

${getGrade()}`);
*/