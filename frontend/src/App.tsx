import './App.css';
import UserAccess from './components/UserAcess/UserAcess';
import SocialMedia from './components/SocialMedia/SocialMedia';
import { useEffect, useState } from 'react';
import { FeedAPI } from './apis/feed/feedAPI';

function App() {
  const [accessAllowed, setAccessAllowed] = useState(false);
  const [feedPosts, setFeedPosts] = useState([]);

  useEffect(() => {
    const feed = FeedAPI.getAllPosts();
    feed.then(response => {
      console.log(response);
      setAccessAllowed(true);
    }).catch(err => {})
  }, []);

  return (
    <div>
      {accessAllowed ? <SocialMedia /> : <UserAccess /> }
    </div>
  );
}

export default App;
