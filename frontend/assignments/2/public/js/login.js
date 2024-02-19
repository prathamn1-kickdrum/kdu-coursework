const loginBtn = document.querySelector(".main-button");
const loginForm = document.querySelector(".main-form");
const errorMessage = document.querySelector(".error");

loginForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  console.log("Username:", username);
  console.log("Password:", password);

  const res = await fetch("http://localhost:8000/api/v1/login", {
    method: "POST",
    body: JSON.stringify({ username, password }),
    headers: {
      "Content-Type": "application/json",
    },
  });

  const data = await res.json();

  if (data.authorized) {
    let baseUrl = "http://localhost:8000/index.html";

    let queryParams = { username: username };

    let queryString = Object.keys(queryParams)
      .map((key) => key + "=" + encodeURIComponent(queryParams[key]))
      .join("&");

    let urlWithParams = baseUrl + "?" + queryString;

    window.location.href = urlWithParams;
  } else {
    errorMessage.style.display = "block";
  }
});
