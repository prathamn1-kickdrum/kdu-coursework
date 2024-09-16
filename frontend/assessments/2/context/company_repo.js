const Company = require('../model/company')

class CompanyRepository {
    constructor() {
        this.companyMap= new Map();
    }

    addNewCompany(name,base_price,logo) {
        const myCompany = new Company(name,base_price,logo);
        this.companyMap.set(name,myCompany);
    }

    getCompanyByName(name) {
        return this.companyMap.get(name);
    }

    getAllCompaniesList() {
        return [...this.companyMap.keys()];
    }
}

const companyRepository = new CompanyRepository();
companyRepository.addNewCompany('zomato',500,'https://mir-s3-cdn-cf.behance.net/projects/404/af1307136664867.Y3JvcCw1NzUzLDQ1MDAsNDIsMA.jpg');


module.exports = companyRepository;
