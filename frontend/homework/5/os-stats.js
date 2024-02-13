const os = require('os');
const fs = require('fs');
const http = require('http');
const path = require('path')

function getSystemInfo() {
    return {
        HostName: os.hostname(),
        OperatingSystem: os.platform(),
        Architecture: os.arch(),
        OSRelease: os.release(),
        Uptime: os.uptime(),
        NumberOfCPUCores: os.cpus().length,
        TotalMemory: os.totalmem(),
        FreeMemory: os.freemem(),
        CurrentWorkingDirectory: process.cwd()
    };
}

function writeJsonToFile(data, filename) {
    fs.writeFileSync(filename, JSON.stringify(data, null, 4));
}

function createJsonAndWriteToFile(filename) {
    const systemInfo = getSystemInfo();
    const filePath = path.join(__dirname, filename);
    writeJsonToFile(systemInfo, filePath);
}


const server = http.createServer((req, res) => {
    if (req.method === 'GET' && req.url === '/') {
        const systemInfo = fs.readFileSync('system_info.json', 'utf8');
        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.end(systemInfo);
    } else {
        res.writeHead(404);
        res.end('Not Found');
    }
});

const PORT = 8000;
server.listen(PORT, () => {
    console.log(`HTTP server started at port ${PORT}`);
    createJsonAndWriteToFile('system_info.json');
});
