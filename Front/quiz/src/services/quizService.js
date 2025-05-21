import axios from "axios";

export const fetchQuiz = () => axios.get("/api/quiz");

export const validateAnswers = (answers) =>
  axios.post("/api/validate", answers);
