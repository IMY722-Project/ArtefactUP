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
                        <div className="wavy-div-chat">
                            <svg className="wavy-svg-chat" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
                                <path fill="#fff" d="M0,96L30,106.7C60,117,120,139,180,149.3C240,160,300,160,360,138.7C420,117,480,75,540,85.3C600,96,660,160,720,197.3C780,235,840,245,900,250.7C960,256,1020,256,1080,218.7C1140,181,1200,107,1260,85.3C1320,64,1380,96,1410,112L1440,128L1440,0L1410,0C1380,0,1320,0,1260,0C1200,0,1140,0,1080,0C1020,0,960,0,900,0C840,0,780,0,720,0C660,0,600,0,540,0C480,0,420,0,360,0C300,0,240,0,180,0C120,0,60,0,30,0L0,0Z"></path>
                            </svg>
                        </div>
                        <div className="scavenger-header">
                            <h1 className="chat-title">What do you want to know?</h1>
                            <div className="museum-icon-chat">
                                <img src="/images/museum_illustration.png" alt="Museum Illustration" />
                            </div>
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
                            strokeWidth={2}
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
