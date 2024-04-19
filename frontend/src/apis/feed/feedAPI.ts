import { Utils } from "../../utils/utils";
import { IFeedPostInfo, IPostInfo } from "../intefaces/IFeedPostInfo";
import { IPostComments } from "../intefaces/IPostComments";
import { IPostRecentComment } from "../intefaces/IPostRecentComment";

const baseURL = "http://localhost:8080/posts";

export const FeedAPI = {
    getAllPosts: async function() {
        const response = await fetch(baseURL, {
            headers: {
                "Accept": "application/json",
                "Authorization": "Bearer " + Utils.getJwtInfo()
            },
            method: "GET"
        })

        return response.json() as Promise<IFeedPostInfo>;
    },

    createPost: async function(content: string) {
        const response = await fetch(baseURL, {
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
                "Authorization": "Bearer " + Utils.getJwtInfo()
            },
            method: "POST",
            body: JSON.stringify({ content })
        })

        return response.json() as Promise<IPostInfo>;
    },

    likePost: async function(idPost: number) {
        const response = await fetch(baseURL + "/likes", {
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
                "Authorization": "Bearer " + Utils.getJwtInfo()
            },
            method: "PUT",
            body: JSON.stringify({ idPost })
        })

        return response;
    },

    getSpecificPost: async function(idPost: number) {
        const response = await fetch(baseURL + "/" + idPost, {
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
                "Authorization": "Bearer " + Utils.getJwtInfo()
            },
            method: "GET"
        })

        return response.json() as Promise<IPostInfo>;
    },

    getAllCommentsFromPost: async function(idPost:number) {
        const response = await fetch(baseURL + "/comment/" + idPost, {
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
                "Authorization": "Bearer " + Utils.getJwtInfo()
            },
            method: "GET"
        })

        return response.json() as Promise<IPostComments>;
    },

    commentOnPost: async function (idPost: number, comment: string) {
        const response = await fetch(baseURL + "/comment", {
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
                "Authorization": "Bearer " + Utils.getJwtInfo()
            },
            method: "POST",
            body: JSON.stringify({ idPost, comment })
        })

        return response.json() as Promise<IPostRecentComment>;
    }
}