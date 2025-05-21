import React, { useState, useContext } from 'react';
import { ImageContext } from '../context/ImageContext';
import { TextField, Button } from '@mui/material';

function ImageUpload() {
  const [url, setUrl] = useState('');
  const { addImage } = useContext(ImageContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!url) {
      alert('Kérlek, adj meg egy URL-t!');
      return;
    }
    addImage(url);
    setUrl('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <TextField
        label="Kép URL"
        variant="outlined"
        value={url}
        onChange={(e) => setUrl(e.target.value)}
        fullWidth
      />
      <Button type="submit" variant="contained" color="primary">Kép feltöltése</Button>
    </form>
  );
}

export default ImageUpload;