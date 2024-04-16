import React, { useRef, useState } from "react";
import { AiOutlineLike } from "react-icons/ai";
import { FaRegCommentAlt } from "react-icons/fa";
import "./SocialMediaFeed.css"
import { IFeedPostInfo } from "../../apis/intefaces/IFeedPostInfo";

interface ISocialMediaFeed {
    posts: IFeedPostInfo
}

function SocialMediaFeed({ posts }: ISocialMediaFeed): React.JSX.Element {

    const likesPostRef = useRef<HTMLButtonElement[]>([]);

    function likePost(index: number) {
        console.log(likesPostRef.current[index])
        if(likesPostRef.current[index].className.includes("likes")) {
            let classes = likesPostRef.current[index].className.split(" likes");
            likesPostRef.current[index].className = classes[0];
        } else {
            likesPostRef.current[index].className += " likes"
        }
    }


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
                            <div className="option">
                                <button className={"btn btn-outline-dark"} onClick={() => likePost(index)} id={`likeButton` + index} ref={el => likesPostRef.current[index] = el as HTMLButtonElement}><AiOutlineLike /> LIKE</button>
                            </div>

                            <div className="option">
                                <button className="btn btn-outline-dark"><FaRegCommentAlt /> COMMENT</button>
                            </div>
                        </div>
                    </div>
                )
            })}
        </div>
    )
}

export default SocialMediaFeed;