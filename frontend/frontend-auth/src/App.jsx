import React, { useState } from "react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";

function App() {
  const [page, setPage] = useState("home"); // home / register / login / dashboard
  const [user, setUser] = useState(null); // stocker info utilisateur après création/connexion

  const handleLoginOrRegister = (userData) => {
    setUser(userData);     // stocke l'utilisateur
    setPage("dashboard");  // va au dashboard
  };

  const handleLogout = () => {
    setUser(null);
    setPage("home");
  };

  const renderPage = () => {
    switch(page) {
      case "register": return <Register goBack={() => setPage("home")} onSuccess={handleLoginOrRegister} />;
      case "login": return <Login goBack={() => setPage("home")} onSuccess={handleLoginOrRegister} />;
      case "dashboard": return <Dashboard user={user} logout={handleLogout} />;
      default: return <Home onChoose={(p) => setPage(p)} />;
    }
  };

  return renderPage();
}

export default App;
