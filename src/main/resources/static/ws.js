let socket;

function connectWebSocket() {
  const token = localStorage.getItem("token");

  if (!token) {
        console.error("No token");
        return;
    }

  const socket = new SockJS('/ws');
  const stompClient = Stomp.over(socket);

  stompClient.connect(
      { Authorization: "Bearer " + token },
      () => console.log("WS conectado"),
      err => console.error("WS error", err)
  );

  socket.onopen = () => {
    console.log("🟢 WebSocket conectado");
  };

  socket.onmessage = (event) => {
    const div = document.getElementById("messages");
    div.innerHTML += `<p>${event.data}</p>`;
  };

  socket.onclose = () => {
    console.log("🔴 WebSocket desconectado");
  };
}
