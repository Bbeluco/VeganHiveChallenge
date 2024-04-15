import React from "react";

function SocialMediaCreatePosts(): React.JSX.Element {
    return (
        <div className="card-header bg-transparent">
            <div className="input-group w-100">
                <input type="text" name="newPost" id="newPost" placeholder="Write your post :)" className="form-control form-control-md" />
                    <div className="input-group-append">
                        <div className="input-group-text">
                            <button type="button" className="btn btn-light">POST</button>
                        </div>
                    </div>
            </div>
        </div>
    )
}

export default SocialMediaCreatePosts;