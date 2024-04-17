import { Utils } from "../../utils/utils";
import { IFeedPostInfo, IPostInfo } from "../intefaces/IFeedPostInfo";

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
    }
}