import ButtonLogin from "../components/ButtonLogin";

const Login = () => (
  <div className="w-screen h-screen flex">
    <div className="h-full flex-shrink-0">
      <img src="/logo.png" alt="" className="h-full object-contain" />
    </div>

    <div className="flex-grow h-full flex flex-col items-center justify-center gap-y-14">
      <h1 className="text-[#25323A] font-bold text-6xl">¡Bienvenido!</h1>
      <form action="" className="flex flex-col w-[60%]">
        <label htmlFor="name-user">Ingresa tu Usuario</label>
        <input
          type="text"
          className="border-b-[#767039]  
            border-b-2 outline-none focus:outline-none 
            appearance-none text-[#767039] text-4xl"
        />
      </form>

      <div className="w-full flex justify-center gap-x-20">
        <ButtonLogin color={"brown"} text={"Buscar Partida"} />
        <ButtonLogin color={"blue"} text={"¿Como jugar?"} />
      </div>
    </div>
  </div>
);

export default Login;
