
import { Button, Typography } from "@mui/material";

export default function QuestionCard({ question, onAnswer }) {
  return (
    <div>
      <Typography variant="h6">{question.text}</Typography>
      {question.answers.map((ans) => (
        <Button
  key={ans.id}
  onClick={() => onAnswer(question.id, ans.id)}
  className="answer-button"
  variant="outlined"
>
  {ans.text}
</Button>
      ))}
    </div>
  );
}
