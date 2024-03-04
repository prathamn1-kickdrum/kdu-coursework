const { v4: uuidv4 } = require('uuid');

class PostRepository {
    constructor() {
        this.postList=new Map();
    }

    /**
     * Adds a new post to the repository.
     * @param {object} myPost - The post to add.
     * @returns {string} The UUID of the added post.
     */
    addPost(myPost) {
        const uuid = uuidv4()
        this.postList.set(uuid,{...myPost});
        return uuid;
    }

    /**
     * Retrieves a post from the repository.
     * @param {string} uuid - The UUID of the post to retrieve.
     * @returns {object|undefined} The retrieved post, or undefined if not found.
     */
    getPost(uuid) {
        return this.postList.get(uuid);
    }

    /**
     * Deletes a post from the repository.
     * @param {string} uuid - The UUID of the post to delete.
     * @returns {boolean} True if the post was successfully deleted, false otherwise.
     */
    deletePost(uuid) {
        return this.postList.delete(uuid);
    }

    /**
     * Retrieves all posts from the repository.
     * @returns {Map<string, object>} A map containing all posts in the repository.
     */
    getAllPosts() {
        return this.postList;
    }
}

const postRepository = new PostRepository();

module.exports = postRepository;
