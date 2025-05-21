import React from 'react';
import { ImageProvider } from './context/ImageContext';
import Gallery from './components/Gallery';
import ImageUpload from './components/ImageUpload';
import './App.css';

function App() {
  return (
    <ImageProvider>
      <div className="App">
        <h1>Képgaléria</h1>
        <ImageUpload />
        <Gallery />
      </div>
    </ImageProvider>
  );
}

export default App;