var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var connectingElement = document.querySelector('.connecting');
var stompClient = null;
var username = null;

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {}, JSON.stringify({sender: username, type: 'JOIN'})
    )
}

function disconnect() {
    stompClient.disconnect()
    chatPage.classList.add('hidden');
    stompClient.unsubscribe('/topic/public')
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(x, y) {
    var playerMove = {
        x: x,
        y: y,
        side: 'X',
        winner: null
    };
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(playerMove));
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    putXO((message.x + '' + message.y), message.side)
    if (message.winner === true) {
        message.winner = false;
        window.location.href = "/"
        alert(message.side + " WIN!!!");
        disconnect()

    } else if (message.winner === false) {
        message.winner = false;
        window.location.href = "/"
        alert(" DRAW!!!");
        disconnect()
    }

}

function putXO(id, side) {
    if (!(document.getElementById(id).value === "X" || document.getElementById(id).value === "O")) {
        document.getElementById(id).value = side;
    }
};

function give(val) {
    if (!(document.getElementById(val).value === "X" || document.getElementById(val).value === "O")) {
        sendMessage(document.getElementById(val).getAttribute('X'),
            document.getElementById(val).getAttribute('Y'))
    }
    event.preventDefault()
}

usernameForm.addEventListener('submit', connect, true)
