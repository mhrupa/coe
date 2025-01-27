/* global bootstrap: false */
(function () {
  'use strict'
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()

function showContent(menuName) {
  $.ajax({
    url: '/' + menuName,  // Endpoint with /api prefix as handled by your interceptor
    method: 'GET',
    contentType: 'application/json', // Send as JSON
    success: function (data) {
      $("#mainContent").html(data);
    },
    error: function (error) {
      console.error('Error fetching random question:', error);
    }
  });
}
