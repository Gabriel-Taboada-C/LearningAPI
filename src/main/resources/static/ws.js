let socket;

function connectWebSocket() {
  const token = localStorage.getItem("token");

  socket = new WebSocket(
    `ws://localhost:8080/ws?token=${localStorage.getItem("token")}`
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
