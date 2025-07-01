// Update the circular progress bar based on the performance data
const mathPerformance = 75; // Replace with actual data
const circularProgressBar = document.querySelector('#mathematic-performance-reader .circular-progress-bar circle:nth-child(2)');
const circularProgressText = document.querySelector('#mathematic-performance-reader .progress-text');
const dashArray = 251.327;
const dashOffset = dashArray - (dashArray * (mathPerformance / 100));

if (circularProgressBar && circularProgressText) {
  circularProgressBar.setAttribute('stroke-dashoffset', dashOffset);
  circularProgressText.textContent = `${mathPerformance}% out of 100%`;
}

// Update the progress bar width and text based on the performance data
const performanceData = 20; // Replace with actual data
const progressBar = document.querySelector('.progress-bar');
const progressText = document.querySelector('.progress-text');

if (progressBar && progressText) {
  progressBar.style.width = `${performanceData}%`;
  progressText.textContent = `${performanceData}%`;
}
