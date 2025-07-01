async function handleSubmit(event) {
  event.preventDefault();

  const userId = document.getElementById("userId").value.trim();
  const password = document.getElementById("password").value.trim();

  if (!userId || !password) {
    alert("Please fill in both fields.");
    return;
  }

  const loginData = { userId, password };
  const baseUrl = "http://localhost:8080/loginUser";

  try {
    const response = await fetch(baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginData),
    });

    const data = await response.json();

    if (response.ok) {
      alert("Login successful!");
	  
	  
	  // Login successful, redirect to another page
	              window.location.href = "file.html";
	  
	  
    } else {
      throw new Error(data.message || 'Wrong credentials, please try again.');
    }
  } catch (error) {
    console.error("Error occurred while calling the API:", error);
    alert("Error: " + error.message);
  }
}