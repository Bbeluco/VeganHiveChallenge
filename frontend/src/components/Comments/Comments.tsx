import React, { useEffect, useRef, useState } from "react";
import { FaRegCommentAlt } from "react-icons/fa";
import { FeedAPI } from "../../apis/feed/feedAPI";
import { IPostComments } from "../../apis/intefaces/IPostComments";
import "./Comments.css"

interface IComments {
    postId: number;
}

function Comments({ postId }: IComments): React.JSX.Element {

    const [comments, setComments] = useState<IPostComments>();

    const commentFieldRef: any = useRef("");

    function reloadAllComments() {
        FeedAPI.getAllCommentsFromPost(postId)
            .then(response => {
                setComments(response)
            }).catch(err => console.log(err))
    }

    useEffect(() => {
        reloadAllComments()
    }, [])

    function commentOnPost() {
        if(!commentFieldRef.current.value) {
            return
        }

        FeedAPI.commentOnPost(postId, commentFieldRef.current.value)
            .then(() => {
                reloadAllComments();
            }).catch(err => console.log(err));
        
            commentFieldRef.current.value = "";
    }

    return (
        <div>
            <br />
            <div className="input-group w-100">

                <input type="text" name="commentOnPost" id="commentOnPost" placeholder="Write your comment" className="form-control form-control-md" ref={commentFieldRef}/>
                <div className="input-group-append">
                    <div className="input-group-text">
                        <button type="button" className="btn btn-light" onClick={() => commentOnPost()}><FaRegCommentAlt /></button>
                    </div>
                </div>
            </div>
            <br />
            <div>
                {comments?.comments.map((comment, index) => {
                    return (
                        <div key={index}>
                            <hr />
                            <div className="authorComment">
                                <p><i>{comment.creator}</i></p>
                                <small>• X hours ago</small>
                            </div>
                            <p className="commentContent">{comment.content}</p>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}

export default Comments;