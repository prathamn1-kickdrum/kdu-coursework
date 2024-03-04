const { getUserByUsername } = require("../utils/dummyDataHandler");

const loginAction = (req, res) => {
  const username = req.body.username;
  const password = req.body.password;

  const myUser = getUserByUsername(username)[0];

  if (user.length == 0 || password !== user.password) {
    res.status(200).json({
      authorized: false,
      message: "Authentication unsuccessful",
    });
  } else if (password === user.password) {
    res.status(200).json({
      authorized: true,
      message: "Authentication successful",
    });
  } else {
    res.status(500).json({
      message: "Some error occurred",
    });
  }
};

const getCurrentUserAction = (req, res) => {
  const username = req.params.username;

  const myUser = getUserByUsername(username)[0];

  res.json({
    name: user.name,
    username: user.user_name,
    profile_url: user.profile_url,
  });
};

module.exports = { loginAction, getCurrentUserAction };
