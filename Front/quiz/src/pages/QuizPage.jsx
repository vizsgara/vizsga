import { useEffect, useState } from "react";
import QuestionCard from "../components/QuestionCard";
import ResultScreen from "../components/ResultScreen";
import { fetchQuiz, validateAnswers } from "../services/quizService";

export default function QuizPage() {
  const [questions, setQuestions] = useState([]);
  const [index, setIndex] = useState(0);
  const [answers, setAnswers] = useState([]);
  const [result, setResult] = useState(null);

  useEffect(() => {
    fetchQuiz().then((res) => {
      const firstTen = res.data.slice(0, 10);
      setQuestions(firstTen);
    });
  }, []);

  const handleAnswer = (questionId, answerId) => {
    const newAnswers = [...answers, { questionId, answerId }];
    setAnswers(newAnswers);

    validateAnswers([{ questionId, answerId }]).then((res) => {
      alert(res.data.details[0].correct ? "Helyes!" : "Helytelen!");
    });

    if (index + 1 < questions.length) {
      setIndex(index + 1);
    } else {
      validateAnswers(newAnswers).then((res) => setResult(res.data));
    }
  };

  if (result) return <ResultScreen result={result} />;

  return (
    <div>
      {questions.length > 0 && (
        <QuestionCard
          question={questions[index]}
          onAnswer={handleAnswer}
        />
      )}
    </div>
  );
}
