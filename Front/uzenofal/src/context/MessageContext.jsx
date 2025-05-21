// src/context/MessageContext.jsx
import { createContext, useContext, useEffect, useState } from 'react';
import axios from 'axios';

const MessageContext = createContext();

export const MessageProvider = ({ children }) => {
  const [messages, setMessages] = useState([]);
  const [loading, setLoading] = useState(true);

  const getMessages = async () => {
    try {
      const res = await axios.get('/api/messages');
      setMessages(res.data);
    } catch (err) {
      console.error('Hiba az üzenetek lekérésekor:', err);
    } finally {
      setLoading(false);
    }
  };

  const postMessage = async (text) => {
    if (!text.trim()) return;
    try {
      const res = await axios.post('/api/messages', { text });
      setMessages(res.data);
    } catch (err) {
      console.error('Hiba az üzenet beküldésekor:', err);
    }
  };

  const likeMessage = async (id) => {
    try {
      const res = await axios.post(`/api/messages/${id}/like`);
      setMessages(res.data);
    } catch (err) {
      console.error('Hiba a like küldésekor:', err);
    }
  };

  useEffect(() => {
    getMessages();
  }, []);

  return (
    <MessageContext.Provider
      value={{ messages, loading, getMessages, postMessage, likeMessage }}
    >
      {children}
    </MessageContext.Provider>
  );
};

export const useMessages = () => useContext(MessageContext);
