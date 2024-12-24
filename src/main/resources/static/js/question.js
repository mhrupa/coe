var topicList= new Array();
var candidateName;
var experienceLevel;
function fetchRandomQuestion(noOfQuestions, topicList, level) {
    $.ajax({
        url: "/random-question", // Endpoint with /api prefix as handled by your interceptor
        method: "POST",
        contentType: "application/json", // Send as JSON
        data: JSON.stringify({
            noOfQuestions: noOfQuestions,
            topicList: topicList,
            level: level,
        }),
        success: function (data) {
            // Assuming the backend returns a JSON array of questions and answers
            if (data && data.length > 0) {
                const question = data[0].questionString; // Assuming first question is used
                const answer = data[0].answer;

                // Update the question and answer sections
                $("#question-text").text(question);
                $("#answer-text").text(answer);
            } else {
                console.log("No questions available.");
            }
        },
        error: function (error) {
            console.error("Error fetching random question:", error);
        },
    });
}