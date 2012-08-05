var app = require('http').createServer(handler), io = require('socket.io')
		.listen(app), fs = require('fs');

var url = require('url');
var path = require('path');

app.listen(82);

var mimeTypes = {
	    "html": "text/html",
	    "jpeg": "image/jpeg",
	    "jpg": "image/jpeg",
	    "png": "image/png",
	    "js": "text/javascript",
	    "css": "text/css"};

function handler(req, res) {
	
	var filename = url.parse(req.url).pathname;
    console.log("HTTP request " + filename);

    if (filename == '/') {
    	filename = '/index.html';
    }
    
	fs.readFile(__dirname + '/public' + filename, function(err, data) {
		if (err) {
			res.writeHead(500);
			return res.end('Error loading index.html');
		}

		var mimeType = mimeTypes[path.extname(filename).split(".")[1]];
        res.writeHead(200, mimeType);
		res.end(data);
	});
}

var amqp = require('amqp');
var connectionId = 1;

io.sockets.on('connection', function(socket) {

	var connection = amqp.createConnection({
		host : 'localhost'
	});

	socket.on('disconnect', function(){
		console.log("AMQP Connection closing");
		connection.end();
	});
	
	connection.on('ready', function() {
		console.log("AMQP Connection ready");
		
		// un identifiant de connection. chaque client websocket possede sa propre queue (par défaut non durable)
		var id = connectionId++;
		connection.queue('consumerX' + id, function(q) {
			console.log("AMQP Queue Connection ready");
			q.bind('commandesExFO3', 'key' );
			q.on('queueBindOk',function(){
				console.log('Binding');					
        		q.subscribe(function (message) {
        			console.log("AMQP message " + message);
					socket.send(JSON.stringify(message));
        		});			
			});
			q.on('basicConsumeOk',function() {
				console.log('AMQP Subscribed');
			});
		});
	});
	
});
