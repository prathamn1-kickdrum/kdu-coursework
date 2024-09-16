const router = require('express').Router();
const {getTransactionHistoryAction,doTransactionAction,getAllCompaniesAction,getCompanyDetailsAction} = require('../controllers/company_controller');


router
    .get('/',getAllCompaniesAction)
    .get('/:companyName',getCompanyDetailsAction)
    .get('/history/:companyName',getTransactionHistoryAction)
    .post('/transaction/:companyName',doTransactionAction)
    

module.exports = router;