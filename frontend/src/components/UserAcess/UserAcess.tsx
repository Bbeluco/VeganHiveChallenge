import React, { useState } from 'react';
import OptionsAccess from '../OptionsAccess/OptionsAcess';
import "./UserAccess.css"
import Login from '../Login/Login';
import Register from '../Register/Register';


function UserAccess() {
    const [optionAccess, setOptionAccess] = useState("login");

    function changeAccessOption(option: string): void {
        setOptionAccess(option);
    }


    return (
        <div className="form">
            <OptionsAccess changeAccessOption={changeAccessOption}/>

            {optionAccess == "login" ? <Login /> : <Register />}
        </div>
    )
}

export default UserAccess;
