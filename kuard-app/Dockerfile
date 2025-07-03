# Start from a Node.js 20 (LTS) image 1
FROM node:20

# Specify the directory inside the image in which all commands will run 2
WORKDIR /usr/src/app

# Copy package files and install dependencies 3
COPY package*.json ./
RUN npm install

# Copy all of the app files into the image 4
COPY . .

# The default command to run when starting the container 5
CMD [ "npm", "start" ]