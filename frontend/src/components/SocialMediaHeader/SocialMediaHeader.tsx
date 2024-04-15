import React from "react";
import "./SocialMediaHeader.css"

function SocialMediaHeader(): React.JSX.Element {
    return (
        <nav className="navbar navbar-expand-md mb-4 custom-nav-bar">

        <button className="navbar-toggler" data-toggle="collapse" data-target="#responsive"><span className="navbar-toggler-icon"></span></button>
            <div className="collapse navbar-collapse" id="responsive">
                <ul className="navbar-nav mr-auto text-capitalize">
                    <li className="nav-item"><a href="#" className="nav-link active">home</a></li>
                    <li className="nav-item"><a href="#" className="nav-link">profile</a></li>
                    <li className="nav-item"><a href="#" className="nav-link" data-toggle="modal">messages</a></li>
                    <li className="nav-item"><a href="#" className="nav-link d-md-none">logout</a></li>
                </ul>
            </div>
        </nav>
    )
}

export default SocialMediaHeader;