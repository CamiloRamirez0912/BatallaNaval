import RowCells from "./RowCells";
import RowNumbers from "../RowNumbers";

const BoardPlayer = () => {

  return (
    <div className="w-[50%] h-full flex flex-col items-center justify-center gap-5">
        <div className="w-[450px] h-[450px] grid grid-rows-11">
            <RowNumbers/>
            <RowCells row={0}/>
            <RowCells row={1}/>
            <RowCells row={2}/>
            <RowCells row={3}/>
            <RowCells row={4}/>
            <RowCells row={5}/>
            <RowCells row={6}/>
            <RowCells row={7}/>
            <RowCells row={8}/>
            <RowCells row={9}/>
        </div>
        <button className="bg-[#FF0000] hover:bg-[#C00F0C] p-2 rounded-xl text-white font-bold text-xl cursor-pointer">Rendirse</button>
    </div>
  )
}

export default BoardPlayer;