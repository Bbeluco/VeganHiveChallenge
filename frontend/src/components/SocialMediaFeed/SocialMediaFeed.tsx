import React from "react";
import { AiOutlineLike } from "react-icons/ai";
import { FaRegCommentAlt } from "react-icons/fa";
import "./SocialMediaFeed.css"
import { IFeedPostInfo } from "../../apis/intefaces/IFeedPostInfo";

interface ISocialMediaFeed {
    posts: IFeedPostInfo
}

function SocialMediaFeed({ posts }: ISocialMediaFeed): React.JSX.Element {


    return (
        <div className="feed">
            {posts.posts.map((post, index) => {
                return (
                    <div key={index} className="post">
                        <div className="media">
                            <div className="media-body">
                                <h5>{post.creator}</h5>
                                <p className="text-justify">{post.content}</p>
                            </div>
                        </div>
                        <small>{post.likes} likes</small>
                        <hr />
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
            })}
        </div>
    )
}

export default SocialMediaFeed;