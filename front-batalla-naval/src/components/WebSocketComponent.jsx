import React, { useState, useEffect, useRef } from "react";
import { Client } from "@stomp/stompjs";

const WebSocketComponent = () => {
  const [connected, setConnected] = useState(false);
  const [messages, setMessages] = useState([]);
  const stompClient = useRef(null);

  const connect = () => {
    stompClient.current = new Client({
      brokerURL: "ws://localhost:8080/gs-guide-websocket", // URL del WebSocket
      onConnect: () => {
        setConnected(true);
        console.log("Conectado al servidor WebSocket");

        stompClient.current.subscribe("/topic/greetings", (message) => {
          const newMessage = JSON.parse(message.body).content;
          setMessages((prevMessages) => [...prevMessages, newMessage]);
        });
      },
      onStompError: (frame) => {
        console.error("Error en STOMP: ", frame.headers.message);
      },
      onWebSocketError: (error) => {
        console.error("Error en WebSocket: ", error);
      },
    });

    stompClient.current.activate(); // Activar la conexión
  };

  const disconnect = () => {
    if (stompClient.current) {
      stompClient.current.deactivate();
      setConnected(false);
      console.log("Desconectado del servidor WebSocket");
    }
  };

  const sendMessage = () => {
    if (stompClient.current && connected) {
      const message = { name: "DIego" };
      stompClient.current.publish({
        destination: "/app/hello",
        body: JSON.stringify(message),
      });
    }
  };

  useEffect(() => {
    connect();

    return () => {
      disconnect();
    };
  }, []);

  return (
    <div>
      <h1>WebSocket en React</h1>
      <button onClick={connect} disabled={connected}>
        Conectar
      </button>
      <button onClick={disconnect} disabled={!connected}>
        Desconectar
      </button>
      <button onClick={sendMessage} disabled={!connected}>
        Enviar mensaje
      </button>

      <h2>Mensajes recibidos:</h2>
      <ul>
        {messages.map((msg, index) => (
          <li key={index}>{msg}</li>
        ))}
      </ul>
    </div>
  );
};

export default WebSocketComponent;