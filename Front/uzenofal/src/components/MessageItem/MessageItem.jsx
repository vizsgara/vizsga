// src/components/MessageItem.jsx
import React from 'react';
import styles from './MessageItem.module.css';
import { FaHeart } from 'react-icons/fa';
import { useMessages } from '../../context/MessageContext';

const MessageItem = ({ message }) => {
  const { likeMessage } = useMessages();

  const handleLike = () => {
    likeMessage(message.id);
  };

  return (
    <div className={styles.message}>
      <p className={styles.text}>{message.text}</p>
      <button className={styles.likeBtn} onClick={handleLike}>
        <FaHeart className={styles.icon} />
        <span>{message.likes}</span>
      </button>
    </div>
  );
};

export default MessageItem;
