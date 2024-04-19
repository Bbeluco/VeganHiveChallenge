export interface IPostComments {
    comments: ICommentInfo[]
}

interface ICommentInfo {
    creator: string,
    content: string
}