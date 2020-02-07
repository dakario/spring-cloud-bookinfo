// Copyright 2017 Istio Authors
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.



var http = require('http')
var dispatcher = require('httpdispatcher')

var port = parseInt(process.argv[2])

var userAddedRatings = [] // used to demonstrate POST functionality

var unavailable = false
var healthy = true

const Eureka = require('eureka-js-client').Eureka;
 
const client = new Eureka({
  // application instance information
  instance: {
    app: 'ratings',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    port: {
      '$': 8085,
      '@enabled': 'true',
    },
    vipAddress: 'ratings',
    statusPageUrl: 'http://localhost:8085/actuator/info',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    // eureka server host / port
    host: 'localhost',
    port: 9090,
    servicePath: '/eureka/apps/',
  },
});

client.start();

dispatcher.onGet(/^\/actuator\/info/, function (req, res) {
  console.log("cbon")
  res.writeHead(200, {'Content-type': 'application/json'})
  res.end(JSON.stringify({}))
  return {}
})  


dispatcher.onGet(/^\/ratings\/[0-9]*/, function (req, res) {
  var productIdStr = req.url.split('/').pop()
  var productId = parseInt(productIdStr)

  if (Number.isNaN(productId)) {
    res.writeHead(400, {'Content-type': 'application/json'})
    res.end(JSON.stringify({error: 'please provide numeric product ID'}))
  }  else {
        res.writeHead(500, {'Content-type': 'application/json'})
        res.end(""+Math.floor(Math.random() * 5))
  }
})

function handleRequest (request, response) {
  try {
    console.log(request.method + ' ' + request.url)
    dispatcher.dispatch(request, response)
  } catch (err) {
    console.log(err)
  }
}

var server = http.createServer(handleRequest)

server.listen(8085, function () {
  console.log('Server listening on: http://0.0.0.0:8085')
})
