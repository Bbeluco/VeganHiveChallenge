export interface IFeedPostInfo {
    posts: IPostInfo[];
}

export interface IPostInfo {
    id: number,
    content: string,
    creator: string,
    likes: number;
    likedByMe: boolean;
}