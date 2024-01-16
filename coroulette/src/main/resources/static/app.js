const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log();
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
    stompClient.subscribe('/topic/room', (room) => {
        console.log(JSON.parse(room.body))
        var users = JSON.parse(room.body).users;

        showGreeting(JSON.parse(room.body).name + ": " + users.map(user => user.name).join(','))
    });
    sendSession();
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendSession(){
    stompClient.publish({
        destination: "/app/enter",
    })
}

function sendName() {
    stompClient.publish({
        destination: "/app/addCategory",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function createRoom(){
    stompClient.publish({
        destination: "/app/register",
        body: JSON.stringify({'name': $('#roomName').val()})
    })
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
    $( "#roomNameSend").click(()=>createRoom());
});