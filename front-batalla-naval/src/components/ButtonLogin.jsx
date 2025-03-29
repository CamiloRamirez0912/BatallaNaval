import React from 'react'

function ButtonLogin({ text, color, onClick, disabled = false }) {
  const colorClass = {
    brown: "bg-[#767039]",
    blue: "bg-[#25323A]",
  };

  return (
    <button
      onClick={onClick}
      disabled={disabled}
      className={`${colorClass[color]} text-white text-bold rounded-[4px] p-3 cursor-pointer text-3xl ${
        disabled ? 'opacity-50 cursor-not-allowed' : 'hover:opacity-90'
      }`}
    >
      {text}
    </button>
  );
}

export default ButtonLogin;