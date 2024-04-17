import './App.css';
import UserAccess from './components/UserAcess/UserAcess';
import SocialMedia from './components/SocialMedia/SocialMedia';
import { useEffect, useState } from 'react';
import { FeedAPI } from './apis/feed/feedAPI';
import { IFeedPostInfo, IPostInfo } from './apis/intefaces/IFeedPostInfo';

function App() {
  const [accessAllowed, setAccessAllowed] = useState(false);
  const [feedPosts, setFeedPosts] = useState<IFeedPostInfo>({ posts: [] });

  useEffect(() => {
    const feed = FeedAPI.getAllPosts();
    feed.then(response => {
      setAccessAllowed(true);
      setFeedPosts(response);
    }).catch(() => {})
  }, []);

  function updateFeedPosts(post: IPostInfo) {
    let newPosts = {...feedPosts};
    newPosts.posts.unshift(post);
    setFeedPosts(newPosts)
  }

  return (
    <div>
      {accessAllowed 
      ? <SocialMedia posts={feedPosts} updateFeedPosts={updateFeedPosts}/> 
      : <UserAccess /> }
    </div>
  );
}

export default App;
