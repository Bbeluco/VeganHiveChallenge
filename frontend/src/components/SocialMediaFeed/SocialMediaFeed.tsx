import React, { useRef, useState } from "react";
import { AiOutlineLike } from "react-icons/ai";
import { FaRegCommentAlt } from "react-icons/fa";
import "./SocialMediaFeed.css"
import { IFeedPostInfo } from "../../apis/intefaces/IFeedPostInfo";
import { FeedAPI } from "../../apis/feed/feedAPI";
import Comments from "../Comments/Comments";

interface ISocialMediaFeed {
    posts: IFeedPostInfo
}

function SocialMediaFeed({ posts }: ISocialMediaFeed): React.JSX.Element {

    const likesPostRef = useRef<HTMLButtonElement[]>([]);
    const countLikesPostRef = useRef<HTMLParagraphElement[]>([]);
    const commentsPostRef = useRef<HTMLButtonElement[]>([]);

    const [indexCommentsPostToShow, setIndexCommentsPostToShow] = useState<number>();

    function likePost(index: number, postId: number) {
        likesPostRef.current[index].disabled = true;
        FeedAPI.likePost(postId)
            .then(_response => {
                if(likesPostRef.current[index].className.includes("likes")) {
                    let classes = likesPostRef.current[index].className.split(" likes");
                    likesPostRef.current[index].className = classes[0];
                    let likesQuantity: number = Number.parseInt(countLikesPostRef.current[index].textContent?.split(" ")[0] as string);
                    countLikesPostRef.current[index].textContent = (likesQuantity - 1) + " likes";
                } else {
                    likesPostRef.current[index].className += " likes"
                    FeedAPI.getSpecificPost(postId)
                        .then(response => {
                            console.log(countLikesPostRef.current[index]);
                            countLikesPostRef.current[index].textContent = response.likes + " likes";
                        }).catch(err => console.log(err));
                }
            })
            .catch(err => console.log(err));

            likesPostRef.current[index].disabled = false;
    }

    function openCommentSection(index: number) {
        if(index == indexCommentsPostToShow) {
            setIndexCommentsPostToShow(-1);
        } else {
            setIndexCommentsPostToShow(index);
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
                        <small ref={el => countLikesPostRef.current[index] = el as HTMLParagraphElement}>{post.likes} likes</small>
                        <hr />
                        <div className="interact-with-post-options">
                            <div className="option">
                                <button className={post.likedByMe ? "btn btn-outline-dark likes" : "btn btn-outline-dark"} onClick={() => likePost(index, post.id)} id={`likeButton` + index} ref={el => likesPostRef.current[index] = el as HTMLButtonElement}><AiOutlineLike /> LIKE</button>
                            </div>

                            <div className="option">
                                <button className="btn btn-outline-dark" onClick={() => openCommentSection(index)} ref={el => commentsPostRef.current[index] = el as HTMLButtonElement}><FaRegCommentAlt /> COMMENT</button>
                            </div>
                        </div>
                        {index == indexCommentsPostToShow ? <Comments postId={post.id} /> : ""}
                    </div>
                )
            })}
        </div>
    )
}

export default SocialMediaFeed;