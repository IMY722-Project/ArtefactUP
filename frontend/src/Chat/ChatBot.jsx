import React, { useState } from "react";
import { API } from "../utils/config";
import HamburgerMenu from "../HamburgerMenu/HamburgerMenu";
import "./ChatBot.css";

const ChatBot = () => {
    const [messages, setMessages] = useState([]);
    const [userInput, setUserInput] = useState("");

    const sendMessage = async (question) => {
        try {
            const res = await fetch(`${API}/api/museum/chat`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ question }),
            });

            if (res.ok) {
                const answer = await res.text();
                setMessages((prev) => [...prev, { text: answer, sender: "bot" }]);
            } else {
                setMessages((prev) => [...prev, {
                    text: "Error: Could not get response.", sender: "bot"
                }]);
            }
        } catch (error) {
            setMessages((prev) => [...prev, { text: "Error: Network issue.", sender: "bot" }]);
        }
    };

    const handleSend = () => {
        if (userInput.trim()) {
            setMessages([...messages, { text: userInput, sender: "user" }]);
            sendMessage(userInput);
            setUserInput("");
        }
    };

    return (
        <div className="chatbot-container">
            <div className="chat-page">
                <div className="top-circle-wrapper">
                    <div className="top-circle">
                        <div className="menu-button">
                            <HamburgerMenu />
                        </div>
                        <div className="scavenger-header">
                            <div className="museum-icon">
                                <img src="/images/museum_illustration.png" alt="Museum Illustration" />
                            </div>
                            <h1 className="scavenger-title">What do you want to know?</h1>
                        </div>
                    </div>
                </div>

                <div className="chat-messages">
                    {messages.map((msg, index) => (
                        <div key={index} className={`message ${msg.sender}`}>
                            <span className="message-text">{msg.text}</span>
                        </div>
                    ))}
                </div>

                <div className="input-container">
                    <input
                        type="text"
                        value={userInput}
                        onChange={(e) => setUserInput(e.target.value)}
                        onKeyDown={(e) => e.key === "Enter" && handleSend()}
                        placeholder="Type a message"
                        className="chat-input"
                    />
                    <button onClick={handleSend} className="send-button">
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 24 24"
                            strokeWidth={1.5}
                            stroke="#ff4412"
                            className="send-icon"
                        >
                            <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                d="M6 12 3.269 3.125A59.769 59.769 0 0 1 21.485 12 59.768 59.768 0 0 1 3.27 20.875L5.999 12Zm0 0h7.5"
                            />
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ChatBot;
