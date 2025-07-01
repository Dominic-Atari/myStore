//Toggle logic for subjects, topics, and subtopics
function toggleElement(event) {
  event.preventDefault();
  const element = event.target.nextElementSibling;
  if (element) {
    element.classList.toggle('show');
  }
}

// Document ready
document.addEventListener("DOMContentLoaded", function () {
  // Toggle links for subjects, topics, and subtopics
  const toggleLinks = document.querySelectorAll(".toggle-link");
  toggleLinks.forEach((link) => link.addEventListener("click", toggleElement));

  // Checkbox toggle logic
  const subtopics = document.querySelectorAll(".submenu li label");
  subtopics.forEach((subtopic) => subtopic.addEventListener("click", function(event) {
    const checkbox = event.target.previousElementSibling;
    checkbox.checked = !checkbox.checked;
  }));
});

//message Modal
document.getElementById('message-icon').addEventListener('click', function(event) {
    event.preventDefault();
    var messageForm = document.getElementById('message-form');
    if (messageForm.style.display === 'none') {
      messageForm.style.display = 'block';
    } else {
      messageForm.style.display = 'none';
    }
  });

// Progress bar updates
function updateCircularProgressBar(performanceData, progressBarElement, progressTextElement) {
  const dashArray = 251.327;
  const dashOffset = dashArray - (dashArray * (performanceData / 100));
  progressBarElement.setAttribute('stroke-dashoffset', dashOffset);
  progressTextElement.textContent = `${performanceData}% out of 100%`;
}

function updateProgressBar(performanceData, progressBarElement, progressTextElement) {
  progressBarElement.style.width = `${performanceData}%`;
  progressTextElement.textContent = `${performanceData}%`;
}

const mathPerformance = 90; // Replace with actual data
const circularProgressBar = document.querySelector('#mathematic-performance-reader .circular-progress-bar circle:nth-child(2)');
const circularProgressText = document.querySelector('#mathematic-performance-reader .progress-text');

if (circularProgressBar && circularProgressText) {
  updateCircularProgressBar(mathPerformance, circularProgressBar, circularProgressText);
}

const performanceData = 60; // Replace with actual data
const progressBar = document.querySelector('.progress-bar');
const progressText = document.querySelector('.progress-text');

if (progressBar && progressText) {
  updateProgressBar(performanceData, progressBar, progressText);
}