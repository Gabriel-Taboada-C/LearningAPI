const API_URL = "http://localhost:8080";

function login() {
  fetch(`${API_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    credentials: "include",
    body: JSON.stringify({
      name: document.getElementById("name").value,
      password: document.getElementById("password").value
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Credenciales inválidas");
    return res.json();
  })
  .then(data => {
    localStorage.setItem("token", data.accessToken);

    // 🔥 REDIRECCIÓN
    window.location.href = "chat.html";
  })
  .catch(err => {
    document.getElementById("error").innerText = err.message;
  });
}
