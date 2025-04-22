// src/components/Chatbot.js
import React, { useState } from 'react';
import './Chatbot.css'; // You'll style this as per your design

const Chatbot = () => {
  const [messages, setMessages] = useState([{ role: 'assistant', content: 'Hi! How can I help you with the scavenger hunt?' }]);
  const [input, setInput] = useState('');

  const sendMessage = async () => {
    if (!input) return;

    const userMessage = { role: 'user', content: input };
    setMessages([...messages, userMessage]);

    const response = await fetch('/api/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message: input })
    });

    const data = await response.json();
    const botMessage = { role: 'assistant', content: data.reply };

    setMessages(prev => [...prev, botMessage]);
    setInput('');
  };

  return (
    <div className="chatbot-container">
      <div className="chat-messages">
        {messages.map((msg, index) => (
          <div key={index} className={`chat-message ${msg.role}`}>
            {msg.content}
          </div>
        ))}
      </div>
      <div className="chat-input">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ask me something..."
        />
        <button onClick={sendMessage}>Send</button>
      </div>
    </div>
  );
};

export default Chatbot;
