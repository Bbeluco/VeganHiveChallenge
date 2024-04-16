import React from "react";
import SocialMediaFeed from "../SocialMediaFeed/SocialMediaFeed";
import SocialMediaCreatePosts from "../SocialMediaCreatePosts/SocialMediaCretePosts";
import SocialMediaHeader from "../SocialMediaHeader/SocialMediaHeader";
import { IFeedPostInfo, IPostInfo } from "../../apis/intefaces/IFeedPostInfo";
import "./SocialMedia.css"

interface ISocialMedia {
    posts: IFeedPostInfo
    updateFeedPosts: (post: IPostInfo) => void;
}

function SocialMedia( { posts, updateFeedPosts }: ISocialMedia): React.JSX.Element {
    return (
        <div className="socialMedia">
            <SocialMediaHeader />
            <div className="container">
                <div className="row">
                    <div className="col-12 col-lg-3">
                        <div className="left-column">
                        </div>
                    </div>
                    <div className="col-12 col-lg-6" >
                        <SocialMediaCreatePosts updateFeedPosts={updateFeedPosts}/>
                        <br />
                        <SocialMediaFeed posts={posts}/>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default SocialMedia;