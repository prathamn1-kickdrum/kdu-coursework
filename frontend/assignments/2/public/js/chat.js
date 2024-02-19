const webSocket = io();

const myProfile = document.querySelector(".menu-container-profile-info");
const activeUserContainer = document.querySelector(".main-users");
const mainMessageDiv = document.querySelector(".main-messages");

let activeUsers = [];
let activeChatId;

const { username } = Qs.parse(location.search, {
  ignoreQueryPrefix: true,
});

function populateMessageContainer(messageData) {
  const userHeader = document.createElement("h2");
  userHeader.textContent = messageData.name;

  const messageContainer = document.createElement("div");
  messageContainer.classList.add("message-container");

  messageData.messages.forEach((message) => {
    if (message.user_id !== activeChatId) return;
    const messageDiv = document.createElement("div");
    messageDiv.classList.add(
      "message",
      message.type === "self" ? "self" : "other"
    );

    const content = document.createElement("p");
    content.classList.add("message-content");
    content.textContent = message.content;

    const time = document.createElement("p");
    time.classList.add("message-time");
    time.textContent = message.time;

    messageDiv.appendChild(content);
    messageDiv.appendChild(time);

    messageContainer.appendChild(messageDiv);
  });

  const messageInputContainer = document.createElement("div");
  messageInputContainer.classList.add("message-input");

  const messageInput = document.createElement("input");
  messageInput.placeholder = "Enter a message..";
  messageInput.type = "text";
  messageInput.id = "message-input";

  const sendBtn = document.createElement("button");
  sendBtn.classList.add("message-button");

  const sendIcon = document.createElement("img");
  sendIcon.src = "./assets/send.svg";
  sendIcon.alt = "Send";

  sendBtn.appendChild(sendIcon);

  sendBtn.addEventListener("click", () => {
    if (messageInput.value.length === 0) return;

    socket.emit("message", {
      id: activeChatId,
      username,
      content: messageInput.value,
    });
  });

  messageInputContainer.appendChild(messageInput);
  messageInputContainer.appendChild(sendBtn);

  messageContainer.appendChild(messageInputContainer);

  mainMessageDiv.appendChild(userHeader);
  mainMessageDiv.appendChild(messageContainer);
}

function setActiveChat(socketId) {
  activeChatId = socketId;
  console.log("Current Active Chat ID - " + activeChatId);
  socket.emit("msg", { socketId, username });
}

function populateUser(userData) {
  const userContainer = document.createElement("div");
  userContainer.classList.add("user");

  const profilePic = document.createElement("img");
  profilePic.classList.add("menu-container-profile-pic");
  profilePic.src = userData.profile_url;

  const userName = document.createElement("h1");
  userName.classList.add("user-name");
  userName.textContent = userData.name;

  const userUsername = document.createElement("p");
  userUsername.classList.add("user-username");
  userUsername.textContent = `@${userData.username}`;

  userContainer.appendChild(profilePic);
  userContainer.appendChild(userName);
  userContainer.appendChild(userUsername);

  userContainer.addEventListener("click", () => {
    mainMessageDiv.style.display = "flex";
    console.log(window.innerWidth);
    if (window.innerWidth <= 400) {
      activeUserContainer.style.display = "none";
    }
    mainMessageDiv.innerHTML = "";
    console.log("Socket id of " + userData.username + " - " + userData.id);
    setActiveChat(userData.id);
  });

  activeUserContainer.appendChild(userContainer);
}

function populateProfileInfo(profileData) {
  const profilePicElement = document.createElement("img");
  profilePicElement.classList.add("menu-container-profile-pic");
  profilePicElement.src = profileData.profile_url;

  const profileTextDiv = document.createElement("div");
  profileTextDiv.classList.add("menu-container-profile-text");

  const nameElement = document.createElement("h1");
  nameElement.textContent = profileData.name;

  const usernameElement = document.createElement("p");
  usernameElement.textContent = `@${profileData.username}`;

  const moreOptionsImg = document.createElement("img");
  moreOptionsImg.src = "./assets/three_dots.svg";
  moreOptionsImg.alt = "More Options";

  profileTextDiv.appendChild(nameElement);
  profileTextDiv.appendChild(usernameElement);

  myProfile.appendChild(profilePicElement);
  myProfile.appendChild(profileTextDiv);
  myProfile.appendChild(moreOptionsImg);
}

async function onLoad() {
  socket.emit("enter", { username });

  const res = await fetch(`http://localhost:8000/api/v1/user/${username}`);

  const data = await res.json();

  populateProfileInfo(data);
  socket.emit("chat");
  console.log("Socket id of " + username + " - " + socket.id);
}

socket.on("current", (payload) => {
  const elementsToRemove = activeUserContainer.querySelectorAll("." + "user");

  if (elementsToRemove.length !== 0) {
    elementsToRemove.forEach((element) => {
      element.parentNode.removeChild(element);
    });
  }

  payload.forEach((u) => {
    populateUser(u);
  });

  activeUsers = payload;
});

socket.on("data", (payload) => {
  console.log(payload);
  mainMessageDiv.innerHTML = "";
  populateMessageContainer(payload);
});

socket.on("refresh", (payload) => {
  if (payload.otherId == activeChatId) {
    mainMessageDiv.innerHTML = "";
    populateMessageContainer({
      id: payload.otherId,
      name: payload.otherName,
      messages: payload.otherMessages,
    });
  }
});

onLoad();
