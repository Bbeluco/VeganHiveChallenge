import './App.css';
import UserAccess from './components/UserAcess/UserAcess';
import SocialMedia from './components/SocialMedia/SocialMedia';
import { useEffect, useState } from 'react';
import { FeedAPI } from './apis/feed/feedAPI';
import { IFeedPostInfo } from './apis/intefaces/IFeedPostInfo';

function App() {
  const [accessAllowed, setAccessAllowed] = useState(false);
  const [feedPosts, setFeedPosts] = useState<IFeedPostInfo>();

  useEffect(() => {
    const feed = FeedAPI.getAllPosts();
    feed.then(response => {
      console.log(response.posts);
      setAccessAllowed(true);
      setFeedPosts(response);
    }).catch(err => {})
  }, []);

  return (
    <div>
      {accessAllowed ? <SocialMedia posts={feedPosts} /> : <UserAccess /> }
    </div>
  );
}

export default App;
