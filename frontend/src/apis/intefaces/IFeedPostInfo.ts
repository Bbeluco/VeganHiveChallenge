export interface IFeedPostInfo {
    posts: PostInfo[];
}

interface PostInfo {
    id: number,
    content: string,
    creator: string,
    likes: number;
}