import React, {useState} from "react";
import {API} from "../utils/config";

const ChatBot = () => {
    const [messages, setMessages] = useState([]);
    const [userInput, setUserInput] = useState("");
    const [isOpen, setIsOpen] = useState(false);

    const toggleChat = () => setIsOpen(!isOpen);

    const sendMessage = async (question) => {
        console.log(JSON.stringify({question}));
        console.log(`${API}`);
        try {
            const res = await fetch(`${API}/api/museum/chat`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({question})
            });

            if (res.ok) {
                const answer = await res.text();
                setMessages((prev) => [...prev, {text: answer, sender: "bot"}]);
            } else {
                console.log(res)
                setMessages((prev) => [...prev, {
                    text: "Error: Could not get response.",
                    sender: "bot"
                }]);
            }
            console.log(messages);
        } catch (error) {
            setMessages((prev) => [...prev, {text: "Error: Network issue.", sender: "bot"}]);
        }
    };

    const handleSend = () => {
        if (userInput.trim()) {
            setMessages([...messages, {text: userInput, sender: "user"}]);
            sendMessage(userInput);
            setUserInput("");
        }
    };

    return (
            <div className="fixed bottom-4 right-4 flex flex-col items-end">
                <div className="relative">
                    {isOpen && (
                            <div className="w-72 h-96 bg-white shadow-lg rounded-lg overflow-hidden flex flex-col">
                                <div className="p-4 bg-blue-600 text-white">Chatbot</div>
                                <div className="flex-1 p-2 overflow-y-auto">
                                    {messages.map((msg, index) => (
                                            <div key={index}
                                                 className={`my-2 ${msg.sender === "user" ? "text-right" : "text-left"}`}>
                                                <span className={`inline-block px-3 py-2 rounded-lg ${msg.sender === "user" ? "bg-blue-500 text-white" : "bg-gray-200 text-black"}`}>{msg.text}</span>
                                            </div>
                                    ))}
                                </div>
                                <div className="p-2 border-t flex gap-2">
                                    <input
                                            type="text"
                                            value={userInput}
                                            onChange={(e) => setUserInput(e.target.value)}
                                            placeholder="Type a message"
                                            className="w-full p-2 border rounded"
                                            onKeyDown={(e) => e.key === "Enter" && handleSend()}
                                    />
                                    <button
                                            onClick={handleSend}
                                    >
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                                             viewBox="0 0 24 24" strokeWidth={1.5}
                                             stroke="currentColor" className="size-6">
                                            <path strokeLinecap="round" strokeLinejoin="round"
                                                  d="M6 12 3.269 3.125A59.769 59.769 0 0 1 21.485 12 59.768 59.768 0 0 1 3.27 20.875L5.999 12Zm0 0h7.5"/>
                                        </svg>

                                    </button>


                                </div>
                            </div>
                    )}
                    <div className="flex justify-end">
                        <button
                                onClick={toggleChat}
                                className="bg-blue-600 text-white p-3 rounded-full shadow-lg focus:outline-none mt-2"
                        >
                            {isOpen ? "Close" : "Chat"}
                        </button>
                    </div>
                </div>
            </div>
    );
};

export default ChatBot;
