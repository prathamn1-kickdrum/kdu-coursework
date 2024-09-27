const socket = io("http://127.0.0.1:4000/");

function setFocus() {
  document.getElementById("w-input-text").focus();
}

const chatContainer = document.getElementsByClassName("chat-container")[0];
document.addEventListener("keydown", (e) => {
  if (e.key === "Enter" && document.hasFocus()) {
    console.log("hello");
    sendMessage();
  }
});

const sendMessage = () => {
  let msgDiv = document.createElement("div");
  let msg = document.getElementById("w-input-text").innerText;
  if (msg.length === 0 || msg === null || msg === undefined) return;
  socket.emit("clientMessage", msg);
  const textNode = document.createElement("h3");
  textNode.textContent = msg;
  textNode.classList.add(...["chat-message"]);
  document.getElementById("w-input-text").textContent = null;
  msgDiv.appendChild(textNode);
  msgDiv.classList.add(...["msg-container", "my-message"]);
  chatContainer.appendChild(msgDiv);
};

socket.on("serverMessage", (msg) => {
  let msgDiv = document.createElement("div");
  if (msg.length === 0 || msg === null || msg === undefined) return;
  const textNode = document.createElement("h3");
  textNode.textContent = msg;
  textNode.classList.add(...["chat-message"]);
  document.getElementById("w-input-text").textContent = null;
  msgDiv.appendChild(textNode);
  msgDiv.classList.add(...["msg-container", "user-message"]);
  chatContainer.appendChild(msgDiv);
});
