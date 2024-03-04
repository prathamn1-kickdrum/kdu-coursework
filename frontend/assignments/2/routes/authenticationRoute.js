const router = require('express').Router();
const { loginAction, getCurrentUserAction } = require('../controllers/authenticationController');


router.post('/login',loginAction);
router.get('/user/:username',getCurrentUserAction);

module.exports = router;
