// src/components/MessageList.jsx
import React from 'react';
import styles from './MessageList.module.css';
import { useMessages } from '../../context/MessageContext';
import MessageItem from '../MessageItem/MessageItem';

const MessageList = () => {
  const { messages, loading } = useMessages();

  if (loading) {
    return <p className={styles.center}>Üzenetek betöltése...</p>;
  }

  if (messages.length === 0) {
    return <p className={styles.center}>Nincs még egyetlen üzenet sem.</p>;
  }

  return (
    <div className={styles.list}>
      {messages.map((message) => (
        <MessageItem key={message.id} message={message} />
      ))}
    </div>
  );
};

export default MessageList;
