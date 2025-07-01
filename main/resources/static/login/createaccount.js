async function handleSubmit(event) {
  event.preventDefault();

  // Get input values
  const firstName = document.getElementById("firstName").value;
  const lastName = document.getElementById("lastName").value;
  const school = document.getElementById("school").value;
  const level = document.getElementById("level").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;

  function validatePasswords(password, confirmPassword) {
    if (password === confirmPassword) {
      document.getElementById("confirmPassword").style.border = "1px solid lightgreen";
      document.getElementById("passwordError").innerHTML = "";
      return true;
    } else {
      document.getElementById("confirmPassword").style.border = "1px solid red";
      document.getElementById("passwordError").innerHTML = "Passwords do not match";
      return false;
    }
  }

  // Validate passwords
  if (!validatePasswords(password, confirmPassword)) {
    return;
  }

  // Create register data object
  const registerData = {
    firstName,
    lastName,
    school,
    level,
    email,
    password,
	confirmPassword,
  };

  const baseUrl = "http://localhost:8080/addUser"

  try {
    // Send POST request
    const response = await fetch(baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(registerData),
    });

    // Check if response is OK
    if (response.ok) {
      const data = await response.json();
      alert(`Student registered successfully!: ${data.message}`);
    } else {
      const errorData = await response.json();
      alert(`Error registering student: ${errorData.message}`);
    }
  } catch (error) {
    console.error(error);
    alert("An error occurred while calling the API");
  }
}