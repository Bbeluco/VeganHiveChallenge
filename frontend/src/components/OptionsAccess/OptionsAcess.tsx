import React from 'react';
import './OptionsAccess.css';

interface IOptionsAccess {
    changeAccessOption(option: string): void;
}

function OptionsAccess({ changeAccessOption }: IOptionsAccess): React.JSX.Element {
    return (
        <div className='options'>
            <button className='button' onClick={() => changeAccessOption("login")}>Login</button>
            <button className='button' onClick={() => changeAccessOption("register")}>Sign Up</button>
        </div>
    )
}

export default OptionsAccess;
