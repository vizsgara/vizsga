import React, { useContext } from 'react';
import { ImageContext } from '../context/ImageContext';
import Rating from './Rating';
import './Gallery.css';

function Gallery() {
  const { images } = useContext(ImageContext);

  return (
    <div className="gallery">
      {images.map(image => (
        <div key={image.id} className="gallery-item">
          <img src={image.url} alt={image.description} />
          <Rating id={image.id} />
          <div className="rating">{image.rating} ⭐</div>
        </div>
      ))}
    </div>
  );
}

export default Gallery;