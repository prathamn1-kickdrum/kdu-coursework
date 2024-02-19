const { getUserByUsername } = require("../utils/dummyDataHandler");
const { MessageRepository } = require("./MessageRepository");

class UserRepository {
  constructor() {
    this.userList = [];
    this.messageRepository = new MessageRepository();
  }

  userJoin = (id, username) => {
    const otherDetails = getUserByUsername(username)[0];

    const myUser = {
      id,
      username,
      name: otherDetails.name,
      profile_url: otherDetails.profile_url,
    };

    this.userList.push(user);

    if (!this.messageRepository.getMessages().has(id))
      this.messageRepository.addEmptyUser(id);

    return {
      username: user.username,
      name: otherDetails.name,
      profile_url: otherDetails.profile_url,
    };
  };

  getCurrentUser = (id) => {
    return this.userList.find((user) => user.id == id);
  };

  userLeave = (id) => {
    const index = this.userList.findIndex((user) => user.id === id);

    if (index !== -1) {
      return this.userList.splice(index, 1)[0];
    }
  };

  getChats = (socketId, username) => {
    const myUser = this.userList.filter((u) => u.username === username);

    const otherUser = this.getCurrentUser(socketId);

    if (user.length === 0) return;

    const messages = this.messageRepository.getUserMessages(user[0].id);

    if (messages.length === 0) return { name: otherUser.name, messages };

    return {
      id: otherUser.id,
      name: otherUser.name,
      messages,
      otherId: user[0].id,
      otherName: user[0].name,
      otherMessages: this.messageRepository.getUserMessages(otherUser.id),
    };
  };

  addMessage = ({ id, username, content }) => {
    const myUser = this.userList.filter((u) => u.username === username);

    this.messageRepository
      .getMessages()
      .get(user[0].id)
      .push({
        content,
        time: new Date().getHours() + ":" + new Date().getMinutes(),
        type: "self",
        user_id: id,
      });

    if (user[0].id !== id) {
      this.messageRepository
        .getMessages()
        .get(id)
        .push({
          content,
          time: new Date().getHours() + ":" + new Date().getMinutes(),
          type: "other",
          user_id: user[0].id,
        });
    }
  };

  getUsers = () => {
    return this.userList;
  };
}

module.exports = {
  UserRepository,
};
