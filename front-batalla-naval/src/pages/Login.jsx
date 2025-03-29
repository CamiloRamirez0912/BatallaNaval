import { useState } from 'react';
import ButtonLogin from "../components/ButtonLogin";

const Login = () => {
  const [username, setUsername] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);
    setSuccessMessage(null);

    try {
      const response = await fetch('http://localhost:8080/api/game/register-player', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username }),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Error al registrar el jugador');
      }

      setSuccessMessage('Usuario creado exitosamente');
      console.log('Jugador registrado:', data);
      // Aquí podrías redirigir al usuario o actualizar el estado de la aplicación
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="w-screen h-screen flex">
      <div className="h-full flex-shrink-0">
        <img src="/logo.png" alt="" className="h-full object-contain" />
      </div>

      <div className="flex-grow h-full flex flex-col items-center justify-center gap-y-14">
        <h1 className="text-[#25323A] font-bold text-6xl">¡Bienvenido!</h1>
        <form onSubmit={handleSubmit} className="flex flex-col w-[60%]">
          <label htmlFor="name-user">Ingresa tu Usuario</label>
          <input
            type="text"
            id="name-user"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="border-b-[#767039]  
              border-b-2 outline-none focus:outline-none 
              appearance-none text-[#767039] text-4xl"
            required
          />
        </form>

        <div className="w-full flex justify-center gap-x-20">
          <ButtonLogin 
            color={"brown"} 
            text={isLoading ? "Cargando..." : "Buscar Partida"} 
            onClick={handleSubmit}
            disabled={isLoading || !username.trim()}
          />
          <ButtonLogin color={"blue"} text={"¿Como jugar?"} />
        </div>
        
        {error && (
          <p className="text-red-500 text-xl py-2 px-4 bg-red-100 rounded">
            {error}
          </p>
        )}
        
        {successMessage && (
          <p className="text-green-600 text-xl py-2 px-4 bg-green-100 rounded">
            {successMessage}
          </p>
        )}
      </div>
    </div>
  );
};

export default Login;