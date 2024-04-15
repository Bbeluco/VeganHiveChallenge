import { Utils } from "../../utils/utils";

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

        return response.json();
    }
}