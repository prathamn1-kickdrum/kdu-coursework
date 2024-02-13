const LIKE_BUTTON_SVG = `
<svg
            class="like-button"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            aria-hidden="true"
          >
            <g>
              <path
                fill="#808080"
                d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
              ></path>
            </g>
          </svg>
`;
const LIKE_SVG = `
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" aria-hidden="true"><g><path fill="#ff657e" d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path></g></svg>
`;

const myImage = document.getElementsByClassName('prof-image');
const inputTweetElement = document.querySelector("#tweet-input");
const container = document.querySelector("#post-container");
const tweetContainer = document.getElementsByClassName("tweet-box")

const navBar = document.getElementsByClassName('mobile-nav')
const navOps = document.getElementsByClassName('mobile-nav-ops')
const likeCount = document.getElementsByClassName('likes-count')[0]
likeCount.textContent = 0;


const navContainer = document.getElementsByClassName("navigation-section")
window.addEventListener('resize',()=> {
    if(this.window.innerWidth>570) {
        navContainer[0].style.display='flex';
    }else {
        navContainer[0].style.display='none';

    }
})


const postBtn = document.querySelector("#tweet-btn");

postBtn.addEventListener('click', () => {
    const tweetValue = inputTweetElement.value;
    createPostElement(tweetValue);
})

const iconProfile = document.getElementsByClassName("profile-icon")


iconProfile[0].addEventListener('click',()=> {
    navContainer[0].style.display='flex';
})

myImage[0].addEventListener('click',()=> {
    navContainer[0].style.display='none';
})

const tweetFob = document.querySelector("#tweet-fob")

tweetFob.addEventListener('click', () => {
    tweetContainer[0].classList.add('display-on-flex')
    navBar[0].classList.add('display-none')
    navOps[0].classList.add('display-on-flex')
})

const getTweetTags = (inputString) => {
    const hashtagRegex = /#(\S+)/g;

    const hashtags = [];
    let normalText = inputString.replace(hashtagRegex, (match, p1) => {
        hashtags.push(p1);
        return '';
    });

    return [normalText.trim(), ...hashtags];
}

const createPostElement = (tweetValue) => {
    let liked = false;

    if (tweetValue.length === 0) {
        return;
    }

    const inputArray = getTweetTags(tweetValue);

    const postContainer = document.createElement('div');
    postContainer.classList.add('posts');

    const profileElement = document.createElement('Element');
    profileElement.classList.add('posts-profile');
    profileElement.src = './assets/profile-pic.png';
    profileElement.alt = 'profile-pic';

    const postInfo = document.createElement('div');
    postInfo.classList.add('posts-info');

    const postTitle = document.createElement('div');
    postTitle.classList.add('posts-title');

    const titleName = document.createElement('h1');
    titleName.textContent = 'Nitesh Gupta';

    const titleHandle = document.createElement('p');
    titleHandle.textContent = '@nit_hck ·';

    const titleTime = document.createElement('span');
    titleTime.textContent = '1s';

    const moreInfoElement = document.createElement('Element');
    moreInfoElement.src = './assets/three_dots.svg';
    moreInfoElement.alt = 'More Info';

    const postContent = document.createElement('div');
    postContent.classList.add('posts-content');

    const contentText = document.createElement('p');
    contentText.textContent = inputArray[0];

    const postActions = document.createElement('div');
    postActions.classList.add('post-ops');

    const commentElement = document.createElement('img');
    commentElement.src = './assets/comment.svg';
    commentElement.alt = 'Comment';

    const retweetElement = document.createElement('img');
    retweetElement.src = './assets/retweet.svg';
    retweetElement.alt = 'Retweet';

    const likeDiv = document.createElement('div');
    const likeCountSpan = document.createElement('span');
    likeCountSpan.classList.add('likes-count');
    likeCountSpan.textContent = 0;
    likeDiv.classList.add('like-button-container');
    likeDiv.innerHTML = LIKE_BUTTON_SVG;

    const statsElement = document.createElement('img');
    statsElement.src = './assets/stats.svg';
    statsElement.alt = 'Stats';

    const subActions = document.createElement('div');
    subActions.classList.add('posts-subactions');

    const bookmarkElement = document.createElement('img');
    bookmarkElement.src = './assets/bookmark.svg';
    bookmarkElement.alt = 'Bookmark';

    const shareElement = document.createElement('img');
    shareElement.src = './assets/share.svg';
    shareElement.alt = 'Share';

    postTitle.appendChild(titleName);
    postTitle.appendChild(titleHandle);
    postTitle.appendChild(titleTime);
    postTitle.appendChild(moreInfoElement);

    postContent.appendChild(contentText);

    for (let i = 1; i < inputArray.length; i++) {
        const contentTags = document.createElement('span');
        contentTags.textContent = '#' + inputArray[i];
        postContent.appendChild(contentTags);
    }

    postActions.appendChild(commentElement);
    postActions.appendChild(retweetElement);

    likeDiv.appendChild(likeCountSpan);
    postActions.appendChild(likeDiv);

    likeDiv.addEventListener('click', () => {
        liked = !liked;
        if (!liked) {
            likeCountSpan.textContent = likeCountSpan.textContent * 1 - 1;
            likeDiv.innerHTML = LIKE_BUTTON_SVG;
            likeDiv.appendChild(likeCountSpan)
        } else {
            likeCountSpan.textContent = likeCountSpan.textContent * 1 + 1;
            likeDiv.innerHTML = LIKE_SVG;
            likeDiv.appendChild(likeCountSpan)
        }
    });

    postActions.appendChild(statsElement);

    subActions.appendChild(bookmarkElement);
    subActions.appendChild(shareElement);

    postActions.appendChild(subActions);

    postInfo.appendChild(postTitle);
    postInfo.appendChild(postContent);
    postInfo.appendChild(postActions);

    postContainer.appendChild(profileElement);
    postContainer.appendChild(postInfo);

    container.appendChild(postContainer);
    postBtn.style.backgroundColor = 'rgba(29, 156, 240, 0.429)';
    inputTweetElement.value = "";
    postBtn.disabled = true;
}

inputTweetElement.addEventListener('keyup', function () {
    let inputValue = inputTweetElement.value.trim();
    postBtn.disabled = inputValue.length > 0 ? false : true;

    postBtn.style.backgroundColor = !inputValue ? 'rgba(29, 156, 240, 0.429)' : 'rgb(29, 155, 240)';
});

const likeButton = document.getElementsByClassName('like-button');
let liked = false;

for (const element of likeButton) {
    element.addEventListener('click', () => {
        liked = !liked;
        if (!liked) {
            likeCount.textContent = likeCount.textContent * 1 - 1;
            element.innerHTML = LIKE_BUTTON_SVG;
        } else {
            element.innerHTML = LIKE_SVG;

            likeCount.textContent = likeCount.textContent * 1 + 1;
        }
    });
}
