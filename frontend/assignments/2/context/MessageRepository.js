class MessageRepository {
    constructor() {
        this.messages = new Map();
    }

     getMessages = () => {
        return this.messages;
    }
    
     getUserMessages = (id) => {
        return this.messages.get(id);
    }
    
     addMessageToUser = (id, messageData) => {
        this.messages.get(id).push(messageData);
    }
    
     addEmptyUser = (id) => {
        this.messages.set(id, []);
    }
    
}

module.exports = {
    MessageRepository
}