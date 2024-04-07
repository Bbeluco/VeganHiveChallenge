import React, { useRef, useState } from "react";
import { UsersAPI } from "../../apis/users/UsersAPI";
import { IUserLoginInfo } from "../../apis/intefaces/IUserLoginInfo";

function Login(): React.JSX.Element {
    const usernameFieldRef: any = useRef("");
    const passwordFieldRef: any = useRef("");

    const [loginIncorrect, setLoginIncorrect] = useState(false);

    function sendLoginInfo() {

        if(!usernameFieldRef || !passwordFieldRef) {
            alert("Fields not filled, please verify ")
        }

        try {
            const request = UsersAPI.login(usernameFieldRef.current.value, passwordFieldRef.current.value);
            request.then(response => {
                console.log(response.jwtToken)
            })
        } catch(err) {
            console.log(err)
        }
        
    }


    return (
        <div>
            <p>Username</p>
            <input type='text' id="username" ref={usernameFieldRef}></input>
            <p>Password</p>
            <input type='password' id="password" ref={passwordFieldRef}/>
            {loginIncorrect ? <p>Login incorrect, please verify user or password</p> : ""}
            <button onClick={() => sendLoginInfo()}>Login</button>
        </div>
    )
}

export default Login;