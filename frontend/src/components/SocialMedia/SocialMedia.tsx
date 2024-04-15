import React from "react";
import SocialMediaFeed from "../SocialMediaFeed/SocialMediaFeed";
import SocialMediaCreatePosts from "../SocialMediaCreatePosts/SocialMediaCretePosts";
import SocialMediaHeader from "../SocialMediaHeader/SocialMediaHeader";

function SocialMedia(): React.JSX.Element {
    return (
        <div>
            <SocialMediaHeader />
            <div className="container">
                <div className="row">
                    <div className="col-12 col-lg-3">
                        <div className="left-column">
                        </div>
                    </div>
                    <div className="col-12 col-lg-6" >
                        <SocialMediaCreatePosts />
                        <br />
                        <SocialMediaFeed />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default SocialMedia;