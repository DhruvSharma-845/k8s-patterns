const express = require('express');
const fs = require('fs');
const util = require('util');

const app = express ();
app.use(express.json());

const PORT = process.env.PORT || 3000;

const log_file = fs.createWriteStream('/opt/logs.txt', {flags : 'w'});


app.get('/', (req, res) => {
  
  const message = 'Hello ' + (process.env.USER_NAME || 'World');

  log_file.write(util.format(message) + '\n');
  res.send(message);
  
});

app.listen(PORT, function () {
    console.log('http://localhost:' + PORT);
});

