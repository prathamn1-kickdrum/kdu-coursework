const { v4: uuidv4 } = require("uuid");
const {
  addPost,
  getAllPosts,
  getPostById,
} = require("../utils/dummyDataHandler");

const getAllPostsAction = (req, res) => {
  if (req.query.page && req.query.limit) {
    const page = parseInt(req.query.page) || 1;
    const limit = parseInt(req.query.limit) || 5;

    const startIndex = (page - 1) * limit;
    const endIndex = page * limit;

    const posts = getAllPosts();

    const results = posts.slice(startIndex, endIndex);

    res.json({
      page,
      limit,
      results,
    });
  } else if (req.query.id) {
    const post = getPostById(req.query.id);
    if (post.length === 0) {
      res.status(404).send("No such post");
    } else {
      res.status(200).json(post[0]);
    }
  } else {
    return res.json(getAllPosts());
  }
};

const addPostAction = (req, res) => {
  const postId = uuidv4();
  const post = {
    id: postId,
    name: req.body.name,
    username: req.body.username,
    content: req.body.content,
    user_image: req.body.user_image,
  };

  addPost(post);
  console.log(postId);
  res.status(200).send(postId);
};

module.exports = { getAllPostsAction, addPostAction };
