import { BrowserRouter, Route, Router, Routes } from "react-router";
import Login from "./pages/Login";
import BattlePage from "./pages/BattlePage";
import WebSocketComponent from "./components/WebSocketComponent";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login/>}/>
        <Route path="/battle" element={<BattlePage namePlayer1={"SANTIAGO"} namePlayer2={"NATALIAa"}/>}/>
        <Route path="/" element={<WebSocketComponent/>}/>
      </Routes> 
    </BrowserRouter>
  );
}

export default App;