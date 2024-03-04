const webSocket = io();

const myProfile = document.querySelector(".menu-container-profile-info");
const tweetBoxProfile = document.querySelector(".menu-container-profile-pic");
const messageBtn = document.getElementById("message");
const postBtn = document.getElementById("post-button");
const postInput = document.getElementById("post-input");
const postsContainer = document.querySelector(".posts");
const tweetBox = document.querySelector(".main-tweet-input");
const mediaInput = document.getElementById("mediaInput");

let page = 1;

const { username } = Qs.parse(location.search, {
  ignoreQueryPrefix: true,
});

postBtn.addEventListener("click", async () => {
  if (postInput.value.length === 0) {
    return;
  }

  const res = await fetch(`http://localhost:8000/api/v1/user/${username}`);
  const data = await res.json();

  console.log(data);
  console.log(postInput.value);
  console.log(
    JSON.stringify({
      name: data.name,
      username: data.username,
      content: postInput.value,
      user_image: data.profile_url,
    })
  );
  await fetch("http://localhost:8000/api/v1/posts", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      name: data.name,
      username: data.username,
      content: postInput.value,
      user_image: data.profile_url,
    }),
  });

  window.location.reload();
});

const createPostDom = async (post) => {
  const postContainer = document.createElement("div");
  postContainer.classList.add("post");

  const profilePic = document.createElement("img");
  profilePic.classList.add("menu-container-profile-pic");
  profilePic.src = post.user_image;

  const myPost = document.createElement("div");
  myPost.classList.add("post-info");

  const myUser = document.createElement("div");
  myUser.classList.add("post-user-info");

  const userName = document.createElement("h1");
  userName.classList.add("post-user");

  const userUsername = document.createElement("p");
  userUsername.classList.add("post-username");
  userUsername.textContent = `@${post.username}`;

  const postTime = document.createElement("p");
  postTime.classList.add("post-time");
  postTime.textContent = new Date().getMinutes() + "mins ago";

  const moreInfoImg = document.createElement("img");
  moreInfoImg.src = "./assets/three_dots.svg";
  moreInfoImg.alt = "More Info";

  const postContent = document.createElement("p");
  postContent.classList.add("post-content");
  postContent.textContent = post.content;

  const postImage = document.createElement("img");
  postImage.classList.add("post-image");

  if (post.imageBlob?.length !== 0) {
    postImage.src = post.imageBlob;
  }

  const postActions = document.createElement("div");
  postActions.classList.add("post-actions");

  const postIcons = [
    "./assets/comment.svg",
    "./assets/retweet.svg",
    "./assets/heart.svg",
    "./assets/stats.svg",
    "./assets/bookmark.svg",
    "./assets/share.svg",
  ];

  postIcons.forEach((iconPath) => {
    const img = document.createElement("img");
    img.src = iconPath;
    img.alt = "Action";
    postActions.appendChild(img);
  });

  myUser.appendChild(userName);
  myUser.appendChild(userUsername);
  myUser.appendChild(postTime);
  myUser.appendChild(moreInfoImg);

  myPost.appendChild(myUser);
  myPost.appendChild(postContent);

  if (post.imageBlob?.length !== 0) {
    myPost.appendChild(postImage);
  }

  myPost.appendChild(postActions);

  postContainer.appendChild(profilePic);
  postContainer.appendChild(myPost);

  postsContainer.appendChild(postContainer);
};

function populateProfileInfo(profileData) {
  console.log(profileData);

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

  tweetBoxProfile.src = profileData.profile_url;
}

async function onLoad() {
  const res = await fetch(`http://localhost:8000/api/v1/user/${username}`);
  const data = await res.json();

  populateProfileInfo(data);

  fetchPostData();
}

messageBtn.addEventListener("click", () => {
  let baseUrl = "http://localhost:8000/chat.html";

  let queryParams = { username: username };

  let queryString = Object.keys(queryParams)
    .map((key) => key + "=" + encodeURIComponent(queryParams[key]))
    .join("&");

  let urlWithParams = baseUrl + "?" + queryString;

  window.location.href = urlWithParams;
});

function fetchPostData() {
  if (page != -1)
    fetch(`http://localhost:8000/api/v1/posts?page=${page}&limit=5`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        data.results.forEach((p) => createPostDom(p));

        if (data.results < 5) {
          page = -1;
          return;
        }

        page++;
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
}

function handleScroll() {
  if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
    fetchPostData();
  }
}

function showMedia(file) {
  const reader = new FileReader();
  let img = document.createElement("img");
  img.classList.add("uploaded-image");

  reader.onload = function (e) {
    img.src = e.target.result;
    console.log(e.target.result);
    tweetBox.appendChild(img);
  };

  reader.readAsDataURL(file);
}

mediaInput.addEventListener("change", handleFiles, false);

function handleFiles() {
  const fileList = this.files;
  console.log(fileList[0]);
  showMedia(fileList[0]);
}

window.addEventListener("scroll", handleScroll);

onLoad();
