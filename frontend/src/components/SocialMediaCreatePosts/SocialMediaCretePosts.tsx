import React, { useRef } from "react";
import { IPostInfo } from "../../apis/intefaces/IFeedPostInfo";
import { FeedAPI } from "../../apis/feed/feedAPI";

interface ISocialMediaCreatePosts {
    updateFeedPosts: (post: IPostInfo) => void;
}

function SocialMediaCreatePosts({ updateFeedPosts }: ISocialMediaCreatePosts): React.JSX.Element {
    const postContent: any = useRef("");    

    async function publishPost() {
        let content: string = postContent.current.value;
        if(!content) {
            return;
        }

        FeedAPI.createPost(content).then((response) => {
            postContent.current.value = "";
            updateFeedPosts(response);
        }).catch(err => console.log(err));
    }

    return (
        <div className="card-header bg-transparent">
            <div className="input-group w-100">
                <input type="text" name="newPost" id="newPost" placeholder="Write your post :)" className="form-control form-control-md" ref={postContent}/>
                    <div className="input-group-append">
                        <div className="input-group-text">
                            <button type="button" className="btn btn-light" onClick={publishPost}>POST</button>
                        </div>
                    </div>
            </div>
        </div>
    )
}

export default SocialMediaCreatePosts;