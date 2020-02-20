let express = require('express');
let app = express();
const port = (process.env.PORT === undefined)? 8085 : process.env.PORT;
const Eureka = require('eureka-js-client').Eureka;
const client = new Eureka({
    // application instance information
    instance: {
        app: 'ratings',
        hostName: 'localhost',
        ipAddr: '0.0.0.0',
        port: {
            '$': port,
            '@enabled': 'true',
        },
        vipAddress: 'ratings',
        statusPageUrl: 'http://localhost:' + port + '/actuator/info',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: 'localhost',
        port: 9090,
        servicePath: '/eureka/apps/',
    },
});

const ratings = [
    {
        idReviewer: 1,
        stars: 3,
    },
    {
        idReviewer: 2,
        stars: 4
    }
];

function getRating(idReviewer){
    for (const rating of ratings){
        if (rating.idReviewer === Number(idReviewer)){
            return rating;
        }
    }
    return {};
}

app.get('/ratings/:idReviewer', function (req, res) {
    const idReviewer = req.params.idReviewer;
    res.json(getRating(idReviewer));
});

app.get('/actuator/info', function (req, res) {
    res.json({});
});

let server = app.listen(port, function () {
    let host = server.address().address
    let port = server.address().port;
    client.start();
    console.log("Example app listening at http://%s:%s", host, port)
});
