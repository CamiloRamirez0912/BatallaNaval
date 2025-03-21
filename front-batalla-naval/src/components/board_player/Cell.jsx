import React, { useContext, useState } from "react";
import SelectCellContext from "../../context/SelectCellContext";
import BoardComponentContext from "../../context/BoardOponentContext";

function Cell({ row, col }) {
  const [stateShot, setStateShut] = useState(false);
  const contextCell = useContext(SelectCellContext);
  const stateCell = useContext(BoardComponentContext)?.boardOponent[row][col];
  return (
    <button
      className={`w-full h-full flex 
      items-center justify-center
      border border-solid  border-black  
      cursor-pointer ${
        contextCell?.stateSelectCell?.row == row && contextCell?.stateSelectCell?.col == col
          ? "bg-green-300"
          : "bg-[#ACD1DB] hover:bg-blue-400"
      }`}
      onClick={() => {
        if (!stateShot) {
          contextCell.setStateSelectCell({
            row: row,
            col:col
          });
;
        } else {
          alert("NO SE PUEDE");
        }
      }}
    >
      <span
        className={`${stateCell == 'X' ? 'text-[#FF0000]' : 'text-[#1500FF]'} text-2xl font-bold`}
      >
        {stateCell}
      </span>
    </button>
  );
}

export default Cell;
