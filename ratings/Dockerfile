FROM node:10

ENV PORT 8085
WORKDIR /usr/src/app

COPY package.json ./

RUN npm install

COPY . .

EXPOSE EXPOSE ${SERVER_PORT}
CMD [ "node", "server.js" ]
