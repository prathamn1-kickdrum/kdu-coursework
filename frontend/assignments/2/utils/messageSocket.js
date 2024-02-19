const { UserRepository } = require("../context/UserRepository");

const socketConnection = (io) => {
  const userRepository = new UserRepository();
  io.on("connection", (socket) => {
    console.log("WebSocket Connected...\nID - " + socket.id);

    socket.on("joinRoom", ({ username }) => {
      const myUser = userRepository.userJoin(socket.id, username);
      const activeUsers = userRepository.getUsers();
      io.emit("activeUsers", activeUsers);
    });

    socket.on("messagePage", () => {
      const activeUsers = userRepository.getUsers();
      io.emit("activeUsers", activeUsers);
    });

    socket.on("chatOpen", ({ socketId, username }) => {
      socket.emit("chatDetails", userRepository.getChats(socketId, username));
    });

    socket.on("sendMessage", ({ id, username, content }) => {
      const senderId = userRepository.addMessage({ id, username, content });
      socket.emit("chatDetails", userRepository.getChats(id, username));
      io.sockets
        .to(id)
        .emit("updateChatDetails", userRepository.getChats(id, username));
    });

    socket.on("disconnect", () => {
      const myUser = userRepository.userLeave(socket.id);

      if (user) {
        const activeUsers = userRepository.getUsers();
        io.emit("activeUsers", activeUsers);
      }
    });
  });
};

module.exports = socketConnection;
