// src/App.jsx
import React from 'react';
import { MessageProvider } from './context/MessageContext';
import MessageForm from './components/MessageForm/MessageForm';
import MessageList from './components/MessageList/MessageList';
import styles from './App.module.css';

const App = () => {
  return (
    <MessageProvider>
      <div className={styles.container}>
        <h1 className={styles.title}>Üzenőfal</h1>
        <MessageForm />
        <MessageList />
      </div>
    </MessageProvider>
  );
};

export default App;
