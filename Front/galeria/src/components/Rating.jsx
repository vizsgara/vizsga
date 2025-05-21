import React, { useContext, useState } from 'react';
import { ImageContext } from '../context/ImageContext';
import { FaStar } from 'react-icons/fa';

function Rating({ id }) {
  const { rateImage } = useContext(ImageContext);
  const [selectedRating, setSelectedRating] = useState(0);

  const handleRating = (rating) => {
    setSelectedRating(rating);
    rateImage(id, rating);
  };

  return (
    <div className="rating-stars">
      {[1, 2, 3, 4, 5].map((star) => (
        <FaStar
          key={star}
          className={`star ${star => selectedRating ? 'selected' : ''}`}
          onClick={() => handleRating(star)}
        />
      ))}
    </div>
  );
}

export default Rating;