import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios';

export const ImageContext = createContext();

export const ImageProvider = ({ children }) => {
  const [images, setImages] = useState([]);

  useEffect(() => {
    const fetchImages = async () => {
      const response = await axios.get('/api/images');
      setImages(response.data);
    };
    fetchImages();
  }, []);

  const addImage = async (url) => {
    const response = await axios.post('/api/image', { url });
    setImages(response.data);
  };

  const rateImage = async (id, rating) => {
    const response = await axios.post('/api/rate', { id, rating });
    setImages(response.data);
  };

  return (
    <ImageContext.Provider value={{ images, addImage, rateImage }}>
      {children}
    </ImageContext.Provider>
  );
};