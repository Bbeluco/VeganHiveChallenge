import React, { useRef, useState } from "react";
import { UsersAPI } from "../../apis/users/UsersAPI";
import { Utils } from "../../utils/utils";
import "./Register"

function Register(): React.JSX.Element {
    const usernameInputRef: any = useRef("");
    const passwordInputRef: any = useRef("");
    const confirmPasswordInputref: any = useRef("");

    const [loginIncorrect, setLoginIncorrect] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    function registerUser() {
        if(!usernameInputRef.current.value || !passwordInputRef.current.value || !confirmPasswordInputref.current.value) {
            setErrorMessage("Fields not filled, please verify");
            setLoginIncorrect(true);
            return;
        }

        if(passwordInputRef.current.value != confirmPasswordInputref.current.value) {
            setErrorMessage("Passwords are different, please verify");
            setLoginIncorrect(true);
            return;
        }

        try {
            const request = UsersAPI.register(usernameInputRef.current.value, passwordInputRef.current.value);
            request.then(response => {
                if(!response.jwtToken) {
                    setErrorMessage("User already exists");
                    setLoginIncorrect(true);
                    return;
                }
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
            {loginIncorrect ? <p className="invalidLogin">{errorMessage}</p> : ""}
            <button onClick={() => registerUser()}>SIGN UP</button>
        </div>
    )
}

export default Register;