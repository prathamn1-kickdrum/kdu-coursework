const { Post } = require('../models/post');
const postRepository = require('../context/postRepository');
const { validatePostData } = require('../util/postValidator');

/**
 * Creates a new post based on the data provided in the request body.
 * @param {Request} req - The request object.
 * @param {Response} res - The response object.
 * @returns {Response} The response object with status and message.
 */
const createPostAction = async (req, res) => {
    const myPostData = req.body;
    const validMessage = validatePostData(myPostData);
    if (validMessage.length != 0) {
        return res.status(400).json({ message: "Invalid Data", errors: validMessage.join('\n') });
    }

    const postId = postRepository.addPost(new Post(myPostData.title, myPostData.author, myPostData.content));

    return res.status(200).json({ message: "Post added successfully", postId: postId });
}

/**
 * Retrieves all posts from the post repository.
 * @param {Request} req - The request object.
 * @param {Response} res - The response object.
 * @returns {Response} The response object with status and message.
 */
const getAllPostsAction = async (req, res) => {
    const postList = postRepository.getAllPosts();
    if (postList.size == 0) {
        return res.status(200).json({ message: "No posts present. Try adding a new post." });
    } else {
        return res.status(200).json({ message: "Posts fetched successfully", posts: [...postList.values()] });
    }
}

/**
 * Retrieves details of a specific post based on the provided postId.
 * @param {Request} req - The request object.
 * @param {Response} res - The response object.
 * @returns {Response} The response object with status and message.
 */
const getPostDetailAction = async (req, res) => {
    const postId = req.params.postId;
    const myPost = postRepository.getPost(postId);
    if (myPost === null || myPost === undefined) {
        return res.status(400).json({ message: `No post present with id: ${postId}` });
    } else {
        return res.status(200).json({ message: "Post fetched successfully", post: myPost });
    }
}

const deletePostAction = async (req, res) => {
    const postId = req.params.postId;
    if (!postRepository.deletePost(postId)) {
        return res.status(400).json({ message: `No post present with id: ${postId}` });
    } else {
        return res.status(200).json({ message: "Post deleted successfully"});
    }
}

module.exports = { createPostAction, getAllPostsAction, getPostDetailAction, deletePostAction };
