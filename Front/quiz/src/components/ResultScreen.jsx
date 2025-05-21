import { Typography } from "@mui/material";

export default function ResultScreen({ result }) {
  return (
    <div>
      <Typography variant="h4">
        Pontszám: {result.correct} / {result.total}
      </Typography>
    </div>
  );
}
