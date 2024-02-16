const companyRepository = require('../context/company_repo');

const doTransactionAction = (req,res)=> {
    try {
        const reqBody = req.body;
        console.log(reqBody);
        const myCompany = companyRepository.getCompanyByName(req.params.companyName);
        myCompany.addTransaction(reqBody.type,reqBody.stocks,reqBody.price,reqBody.date);
        return res.status(200).json({success: true,message: "transaction done successfully"});
    } catch {
        return res.status(400).json({
            success: false,
            message: "Could not fetch company details"
        });
    }  
}

const getAllCompaniesAction = (req,res) => {
    try {
        return res.status(200).json({
            success: true,
            companyList: companyRepository.getAllCompaniesList()
        });
    } catch {
        return res.status(400).json({
            success: false,
            message: "Could not fetch company details"
        });
    }  
}


const getCompanyDetailsAction = (req,res) => {

    try {
        const myCompany = companyRepository.getCompanyByName(req.params.companyName);

        return res.status(200).json({
            success: true,
            message: "company data fetched successfully",
            company: {
                name: myCompany.getName(),
                price: myCompany.getPrice(),
                logo: myCompany.getLogo(),
                transactionHistory: myCompany.getTransactionHistory()
            }
        });
    } catch (e){
        return res.status(400).json({
            success: false,
            message: "Could not fetch company details",
            error: e
        })
    }  
    
}

const getTransactionHistoryAction = (req,res) => {
    try {
        const myCompany = companyRepository.getCompanyByName(req.params.companyName);

        return res.status(200).json({
            success: true,
            message: "transaction history fetched successfully",
            company: {
                transactionHistory: myCompany.getTransactionHistory()
            }
        });
    } catch {
        return res.status(400).json({
            success: false,
            message: "Could not fetch company transaction history"
        })
    }  
    
}

module.exports = {doTransactionAction,getAllCompaniesAction,getCompanyDetailsAction,getTransactionHistoryAction};