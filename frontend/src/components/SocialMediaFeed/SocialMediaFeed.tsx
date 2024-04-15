import React from "react";
import { AiOutlineLike } from "react-icons/ai";
import { FaRegCommentAlt } from "react-icons/fa";
import "./SocialMediaFeed.css"

function SocialMediaFeed(): React.JSX.Element {
    return (
        <div className="post">

            <div className="media">
                    <div className="media-body">
                        <h5>Bbeluco</h5>
                        <p className="text-justify">Primeiro Post da rede</p>
                    </div>
            </div>
            <hr/>
            <div className="interact-with-post-options">
                <div>
                    <AiOutlineLike /> LIKE
                </div>
                
                <div>
                    <FaRegCommentAlt /> COMMENT
                </div>
            </div>
        </div>
    )
}

export default SocialMediaFeed;