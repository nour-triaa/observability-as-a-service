const API_URL = "http://localhost:8080"; // port du gateway

export async function register(user) {
  const res = await fetch(`${API_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    credentials: "include",
    body: JSON.stringify(user)
  });

  if (!res.ok) {
    throw new Error(await res.text());
  }

  return res.json();
}

export async function login(credentials) {
  const res = await fetch(`${API_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    credentials: "include",
    body: JSON.stringify(credentials)
  });

  if (!res.ok) {
    throw new Error(await res.text());
  }

  return res.json();
}
