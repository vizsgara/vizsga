// src/components/MessageForm.jsx
import React, { useState } from 'react';
import styles from './MessageForm.module.css';
import { useMessages } from '../../context/MessageContext';

const MessageForm = () => {
  const { postMessage } = useMessages();
  const [text, setText] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (text.trim()) {
      postMessage(text);
      setText('');
    }
  };

  return (
    <form className={styles.form} onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Írj egy üzenetet..."
        value={text}
        onChange={(e) => setText(e.target.value)}
        className={styles.input}
      />
      <button type="submit" className={styles.button}>
        Küldés
      </button>
    </form>
  );
};

export default MessageForm;
