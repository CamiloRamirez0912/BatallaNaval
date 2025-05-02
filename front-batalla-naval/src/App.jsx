import { BrowserRouter, Route, Routes } from "react-router";
import Login from "./pages/Login";
import BattlePage from "./pages/BattlePage";
import PositionShipsPage from "./pages/PositionShipsPage";
import { WebSockerProvider } from "./context/WebSocketContext";

function App() {
  return (
    <BrowserRouter>
      <WebSockerProvider>
        <Routes>
          <Route path="/" element={<Login/>}/>
          <Route  path="/position-ships/:gameId/:playerId/:username"element={<PositionShipsPage/>}/>
          <Route path="/battle/:gameId/:playerId/:username/:opponent" element={<BattlePage/>}/>
          <Route path="*" element={<h1>Not Found</h1>}/>
        </Routes> 
      </WebSockerProvider>
  </BrowserRouter>
  );
}

export default App;