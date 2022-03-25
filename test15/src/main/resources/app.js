var stompClient=null;

function setConnected(connected) {
$('#connect').prop("disable",connected);
$('#disconnect').prop("disable",!connected);
if (connected){
    $('#conversation').show();
    $('#chat').show();
}else {
    $('#conversation').hide();
    $('#chat').hide();
}
$('#greetings').html("")
}

function showGreeting(message) {
    $('#greetings').append("<div>" + message.name + ":" + message.content + "</div>>");
}
function disconnect() {
if (stompClient !==null){
    stompClient.disconnect();
}
setConnected(false);
}
function sendName() {
        stompClient.send("/app/hello",{},JSON.stringify({'name':$('#name').val(),'content':$('#content').val()}));
}
function connect() {
if (!$("#name").val()){
    return;
}
var socket = new SockJS('/chat');
stompClient = Stomp.over(socket);
stompClient.connect({},function (frame) {
    setConnected(true);
    stompClient.subscribe('/topic/greetings',function (greeting) {
        showGreeting(JSON.parse(greeting.body))
    })
})
}

$(
    function () {
    $("#connect").click(function () {
connect();
    });
    $("#disconnect").click(function () {
disconnect();
    });
    $("#send").click(function () {
sendName();
    });
    }
)