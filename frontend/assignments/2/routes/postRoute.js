const router = require('express').Router();
const { getAllPostsAction, addPostAction } = require('../controllers/postController');

router
    .get('/posts', getAllPostsAction)
    .post('/posts', addPostAction)

module.exports = router;