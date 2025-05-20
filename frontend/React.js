import React, { useState } from 'react';

function Chatbot() {
  const [input, setInput] = useState('');
  const [response, setResponse] = useState('');

  const sendMessage = async () => {
    const res = await fetch('/api/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'text/plain' },
      body: input,
    });
    const text = await res.text();
    setResponse(text);
  };

  return (
    <div>
      <textarea value={input} onChange={(e) => setInput(e.target.value)} />
      <button onClick={sendMessage}>Send</button>
      <div>{response}</div>
    </div>
  );
}

export default Chatbot;

