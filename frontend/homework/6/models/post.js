class Post {
    constructor(title,content,author) {
        this.title=title;
        this.author=author;
        this.content=content;
        this.date = new Date().toISOString;
    }
}

const postSchema = {
    title: {
        type: 'string',
        required: true
    },
    content: {
        type: 'string',
        required: true
    },
    author: {
        type: 'string',
        required: true
    }
};


module.exports = {Post,postSchema};
