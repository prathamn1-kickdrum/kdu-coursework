"use strict";
const recipe_controller = require('../controllers/recipe_controller');
const router = require('express').Router();
router
    .get('/recipe', recipe_controller);
