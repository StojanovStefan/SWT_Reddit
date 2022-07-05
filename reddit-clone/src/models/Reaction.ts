import Post from './Post';
import User from './User';

enum ReactionType {
  LIKE = 'LIKE',
  DISLIKE = 'DISLIKE'
}

class Reaction {
  id?: number;
  user?: User;
  post?: Post;
  type?: ReactionType;
}

export { Reaction, ReactionType };
