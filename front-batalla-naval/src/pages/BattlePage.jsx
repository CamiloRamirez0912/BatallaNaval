import React, { useState } from "react";
import BoardPlayer from "../components/board_player/BoardPlayer";
import BoardOponent from "../components/board_oponent/BoardOponent";
import UsersProfiles from "../components/UsersProfiles";
import CurrentPlayerContext from "../context/CurrentPlayerContext";


function BattlePage({ namePlayer1, namePlayer2 }) {
  const [stateCurrentPlayer, setStateCurrentPlayer] = useState(namePlayer1);

  return (
    <CurrentPlayerContext.Provider value={{stateCurrentPlayer}}>
        <div className="w-screen h-screen bg-[#EBE8DB] flex  flex-col">
          <UsersProfiles namePlayer1={namePlayer1} namePlayer2={namePlayer2} />
          <div className="w-full flex justify-center items-center">
            <BoardPlayer />
            <BoardOponent />
          </div>
        </div>
    </CurrentPlayerContext.Provider>
  );
}

export default BattlePage;
