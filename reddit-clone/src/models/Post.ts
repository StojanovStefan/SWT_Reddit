import Community from './Community';
import User from './User';

class Post {
  id?: number;
  user?: User;
  community?: Community;
  title?: string;
  text?: string;
  creationDate?: Date;
  imagePath?: string;
}

export default Post;
