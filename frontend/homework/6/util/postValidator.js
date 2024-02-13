const { postSchema } = require('../models/post');

/**
 * Validates the provided data against the post schema.
 * @param {object} data - The data to be validated.
 * @returns {string[]} An array containing validation error messages, if any.
 */
const validatePostData = (data) => {
    const message = [];
    for (let key of Object.keys(postSchema)) {
        const fieldSchema = postSchema[key];

        if (fieldSchema.required && !data.hasOwnProperty(key)) {
            message.push(`${key} is required`);
        }

        if (data.hasOwnProperty(key) && (typeof data[key] === 'string' || data[key] instanceof String) && data[key].trim() === '') {
            message.push(`${key} cannot be blank or empty`);
        }

        if (data.hasOwnProperty(key) && typeof data[key] !== fieldSchema.type) {
            message.push(`${key} should be of type ${fieldSchema.type}`);
        }
    }

    return message;
}

module.exports = { validatePostData };
