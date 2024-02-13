const router = require('express').Router();
const {createPostAction, getAllPostsAction, getPostDetailAction, deletePostAction} = require('../controllers/postController');

router.
    get('/post/:postId',getPostDetailAction);
    delete('/post/:postId',deletePostAction); //NOSONAR
    post('/post',createPostAction);
    get('/posts',getAllPostsAction);

module.exports=router;