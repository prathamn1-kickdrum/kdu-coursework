const { UserRepository } = require("../context/UserRepository");

const socketConnection = (io) => {
  const userRepository = new UserRepository();
  io.on("connection", (socket) => {
    console.log("WebSocket Connected...\nID - " + socket.id);

    socket.on("enter", ({ username }) => {
      userRepository.userJoin(socket.id, username);
      const activeUsers = userRepository.getUsers();
      io.emit("current", activeUsers);
    });

    socket.on("chat", () => {
      const activeUsers = userRepository.getUsers();
      io.emit("current", activeUsers);
    });

    socket.on("msg", ({ socketId, username }) => {
      socket.emit("data", userRepository.getChats(socketId, username));
    });

    socket.on("message", ({ id, username, content }) => {
      userRepository.addMessage({ id, username, content });
      socket.emit("data", userRepository.getChats(id, username));
      io.sockets.to(id).emit("refresh", userRepository.getChats(id, username));
    });

    socket.on("disconnect", () => {
      const myUser = userRepository.userLeave(socket.id);

      if (myUser) {
        const activeUsers = userRepository.getUsers();
        io.emit("current", activeUsers);
      }
    });
  });
};

module.exports = socketConnection;
