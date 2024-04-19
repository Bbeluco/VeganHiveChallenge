import React, { useRef, useState } from "react";
import { UsersAPI } from "../../apis/users/UsersAPI";
import { Utils } from "../../utils/utils";
import "./Login.css"

function Login(): React.JSX.Element {
    const usernameFieldRef: any = useRef("");
    const passwordFieldRef: any = useRef("");

    const [loginIncorrect, setLoginIncorrect] = useState(false);

    function sendLoginInfo() {

        if(!usernameFieldRef || !passwordFieldRef) {
            setLoginIncorrect(true);
            return;
        }

        try {
            const request = UsersAPI.login(usernameFieldRef.current.value, passwordFieldRef.current.value);
            request.then(response => {
                Utils.setupJwtInfo(response.jwtToken);
                if(!response.jwtToken) {
                    setLoginIncorrect(true);
                    return;
                }
                window.location.reload();
            })
        } catch(err) {
            console.log(err);
        }   
    }

    function resetLoginInvalidInfo() {
        setLoginIncorrect(false);
    }


    return (
        <div>
            <p>Username</p>
            <input type='text' id="username" ref={usernameFieldRef} onChange={() => resetLoginInvalidInfo()}></input>
            <p>Password</p>
            <input type='password' id="password" ref={passwordFieldRef} onChange={() => resetLoginInvalidInfo()}/>
            {loginIncorrect ? <p className="invalidLogin">Login incorrect, please verify user or password</p> : ""}
            <button onClick={() => sendLoginInfo()}>Login</button>
        </div>
    )
}

export default Login;