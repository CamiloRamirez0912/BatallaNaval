import React, { useEffect } from "react";
import BoardPlayer from "../components/board_player/BoardPlayer";
import BoardOponent from "../components/board_oponent/BoardOponent";
import UsersProfiles from "../components/UsersProfiles";
import { useParams } from "react-router";
import { useWebSocket } from "../context/useWebSocket";


function BattlePage() {
  const {gameId} = useParams();
  const {username} = useParams();
  const {opponent} = useParams();
  const {stompClient, connect, disconnect, sendMessage, resultServer, setResultServer} = useWebSocket();

    useEffect(() => {
      const startGame = async () => {
        try {
          await connect(stompClient, username, gameId, setResultServer);
          const player = {name: username, gameId}
          console.log(player)
          sendMessage(stompClient, player, "play");
        } catch (error) {
          console.error("❌ Error al conectar:", error);
        }
      }
      startGame();
      console.log(stompClient.current.connected);
      console.log("Ejecutando");
      return () => {
        disconnect(stompClient);
      }
    }, [connect, disconnect, gameId, sendMessage, setResultServer, stompClient, username]);


  return (   
        <div className="w-screen h-screen bg-[#EBE8DB] flex  flex-col">
          <UsersProfiles namePlayer1={username} namePlayer2={opponent} />
          <div className="w-full flex justify-center items-center">
            <BoardPlayer />
            <BoardOponent />
          </div>
        </div>
  );
}

export default BattlePage;