const RANDOM_IMAGE_URL = "https://source.unsplash.com/random/300×300";

const dummyPosts = [
  {
    id: "1",
    content:
      "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful. - Albert Schweitzer",
    username: "user1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "2",
    content:
      "The only way to do great work is to love what you do. If you haven't found it yet, keep looking. Don't settle. - Steve Jobs",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "3",
    content:
      "Believe you can and you're halfway there. All our dreams can come true, if we have the courage to pursue them. - Walt Disney",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "4",
    content:
      "The future belongs to those who believe in the beauty of their dreams. Dreams are not what you see in sleep, it is the thing which doesn't let you sleep. - Eleanor Roosevelt",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "5",
    content:
      "In the middle of every difficulty lies opportunity. You must not lose faith in humanity. Humanity is like an ocean; if a few drops of the ocean are dirty, the ocean does not become dirty. - Mahatma Gandhi",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "6",
    content:
      "The greatest glory in living lies not in never falling, but in rising every time we fall. You may have to fight a battle more than once to win it. - Margaret Thatcher",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "7",
    content:
      "Life is what happens when you're busy making other plans. Count your age by friends, not years. Count your life by smiles, not tears. - John Lennon",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "8",
    content:
      "Spread love everywhere you go. Let no one ever come to you without leaving happier. The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart. - Mother Teresa",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "9",
    content:
      "When you reach the end of your rope, tie a knot in it and hang on. Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "10",
    content:
      "The only limit to our realization of tomorrow will be our doubts of today. Keep your face always toward the sunshine - and shadows will fall behind you. - Walt Whitman",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "11",
    content:
      "The way to get started is to quit talking and begin doing. The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "12",
    content:
      "Don't watch the clock; do what it does. Keep going. Time is a created thing. To make the best use of it is the art of life. - Unknown",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "13",
    content:
      "Do not wait to strike till the iron is hot; but make it hot by striking. - William Butler Yeats",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "14",
    content:
      "It is never too late to be what you might have been. - George Eliot",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "15",
    content:
      "Everything you’ve ever wanted is on the other side of fear. The journey of a thousand miles begins with one step. - Lao Tzu",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "16",
    content:
      "The best time to plant a tree was 20 years ago. The second best time is now. - Chinese Proverb",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "17",
    content:
      "Your time is limited, don't waste it living someone else's life. Life is 10% what happens to us and 90% how we react to it. - Steve Jobs",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "18",
    content:
      "You miss 100% of the shots you don’t take. The only impossible journey is the one you never begin. - Wayne Gretzky",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "19",
    content:
      "I have not failed. I've just found 10,000 ways that won't work. You become what you believe. - Thomas Edison",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
  {
    id: "20",
    content:
      "If you want to lift yourself up, lift up someone else. The best revenge is massive success. - Booker T. Washington",
    username: "prathamn1",
    user_image: RANDOM_IMAGE_URL,
    imageBlob: "",
  },
];

const dummyUsers = [
  {
    id: "1",
    name: "User One",
    user_name: "user1",
    user_email_id: "user1@gmail.com",
    password: "user1",
    profile_url: RANDOM_IMAGE_URL,
  },
  {
    id: "2",
    name: "User Two",
    user_name: "user2",
    user_email_id: "user2@gmail.com",
    password: "user2",
    profile_url: RANDOM_IMAGE_URL,
  },
  {
    id: "3",
    name: "User Three",
    user_name: "user3",
    user_email_id: "user3@gmail.com",
    password: "user3",
    profile_url: RANDOM_IMAGE_URL,
  },
  {
    id: "4",
    name: "User Four",
    user_name: "user4",
    user_email_id: "user4@gmail.com",
    password: "user4",
    profile_url: RANDOM_IMAGE_URL,
  },
  {
    id: "5",
    name: "User Five",
    user_name: "user5",
    user_email_id: "user5@gmail.com",
    password: "user5",
    profile_url: RANDOM_IMAGE_URL,
  },
];

const addPost = (post) => {
  dummyPosts.unshift(post);
};

const getAllPosts = () => {
  return dummyPosts;
};

const getPostById = (id) => {
  return dummyPosts.filter((p) => p.id === id);
};

const getUserByUsername = (username) => {
  return dummyUsers.filter((u) => u.user_name === username);
};

module.exports = {
  dummyUsers,
  dummyPosts,
  addPost,
  getAllPosts,
  getPostById,
  getUserByUsername,
};
