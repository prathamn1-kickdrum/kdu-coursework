const router = require('express').Router();
const {createPostAction, getAllPostsAction, getPostDetailAction} = require('../controllers/postController');


router.get('/post/:postId',getPostDetailAction);
router.post('/post',createPostAction);
router.get('/posts',getAllPostsAction);


module.exports=router;