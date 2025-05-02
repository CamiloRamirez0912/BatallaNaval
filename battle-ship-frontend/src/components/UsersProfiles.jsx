import React from 'react'

const UsersProfiles = ({namePlayer1, namePlayer2}) => {
  return (
    <div className='w-full flex h-28 justify-around p-5'>
        <div className='flex justify-center items-center w-[50%] gap-5'>
            <img src="/general.png" alt="icon-user-1" className='w-20 h-20'/>
            <div className="flex flex-col">
                <span className='text-[#1500FF] font-bold text-2xl'>{namePlayer1}</span>
            </div>
        </div>
        <img src="/vs.png" alt="vs-image" className='w-20 h-20'/>

        <div className='flex justify-center items-center w-[50%] gap-5'>
            <img src="/general2.png" alt="icon-user-2" className='w-20 h-20'/>
            <div className="flex flex-col">
                <span className='text-[#FF0000] font-bold text-2xl'>{namePlayer2}</span>
            </div>
        </div>
    </div>
  )
}

export default UsersProfiles;