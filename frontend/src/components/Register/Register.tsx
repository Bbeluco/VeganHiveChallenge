import React, { useRef, useState } from "react";
import { UsersAPI } from "../../apis/users/UsersAPI";
import { Utils } from "../../utils/utils";

function Register(): React.JSX.Element {
    const usernameInputRef: any = useRef("");
    const passwordInputRef: any = useRef("");
    const confirmPasswordInputref: any = useRef("");

    const [loginIncorrect, setLoginIncorrect] = useState(false);

    function registerUser() {
        if(!usernameInputRef || !passwordInputRef || !confirmPasswordInputref) {
            alert("Fields not filled, please verify ");
            return;
        }

        if(passwordInputRef.current.value != confirmPasswordInputref.current.value) {
            alert("Passwords are different, please verify");
            return;
        }

        try {
            const request = UsersAPI.register(usernameInputRef.current.value, passwordInputRef.current.value);
            request.then(response => {
                Utils.setupJwtInfo(response.jwtToken);
                window.location.reload();
            })
        } catch(err) {
            console.log(err)
        }
    }


    return (
        <div>
            <p>Username</p>
            <input type='text' id="username" ref={usernameInputRef}></input>
            <p>Password</p>
            <input type='password' id="password" ref={passwordInputRef}/>
            <p>Confirm password</p>
            <input type="password" id="confirm-password" ref={confirmPasswordInputref}></input>
            {loginIncorrect ? <p>Login incorrect, please verify user or password</p> : ""}
            <button onClick={() => registerUser()}>Login</button>
        </div>
    )
}

export default Register;