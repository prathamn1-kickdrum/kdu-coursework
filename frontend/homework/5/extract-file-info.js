const path = require('path');

function extractFileInfo(filePath) {
    return {
        extension: path.extname(filePath),
        baseName: path.basename(filePath),
        directory: path.dirname(filePath)
    };
}

function processFilePaths(filePaths) {
    return filePaths.map(filePath => extractFileInfo(filePath));
}



const filePaths = [
    __filename,
    path.join(__dirname, 'system_info.json') 
];

console.log(processFilePaths(filePaths));
