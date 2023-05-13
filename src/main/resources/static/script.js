

function showTable(data) {
  // Transpose matrix
  output = data[0].map((_, colIndex) => data.map(row => row[colIndex]));
  let div = document.getElementById('round-robin-div');

  let table = document.createElement('table');
  table.className = "table table-bordered";
  //iterate over every array(row) within tableArr
  for (let row of output) {
    //Insert a new row element into the table element
    table.insertRow();
    //Iterate over every index(cell) in each array(row)
    for (let cell of row) {
      //While iterating over the index(cell)
      //insert a cell into the table element
      let newCell = table.rows[table.rows.length - 1].insertCell();
      //add text to the created cell element
      newCell.textContent = cell;
    }
  }
  //append the compiled table to the DOM
  div.appendChild(table);

}

function registerTour(btn) {
  const tourId = btn.id.split("-")[1];

  $.ajax({
    url: `/registerTour?tourId=${tourId}&name=${localStorage.getItem('username')}`,
    success: (result) => {
      console.log(result);
      btn.className = 'btn btn-secondary';
      btn.textContent = 'Registered';
      btn.disabled = true;
    }
  });

}

function populateUpcomingTable(data) {
  let table = document.getElementById("upcoming-table");

  for (let tour of data) {
    let row = table.insertRow();

    var cell = row.insertCell();
    cell.textContent = tour.name;

    cell = row.insertCell();
    const btn = document.createElement('button');
    btn.textContent = 'Register';
    btn.className = 'btn btn-primary';
    btn.id = `btn-${tour.id}`;
    btn.setAttribute('onClick', `registerTour(this)`);
    cell.append(btn);
  }
}

$(document).ready(() => {
  let table = $("#upcoming-table");

  if (table) {
    $.ajax({
      url: '/getAllTour',
      type: 'get',
      success: (result) => {
        populateUpcomingTable(result);
      }
    });
  }

});


function sendCreateTour() {
  const data = {
    name: $("#tournamentName").val(),
    type: $("#sel11").val(),
    participationType: $("#sel1").val(),
    sport: $("#game").val(),
    startDate: $("#date").val(),
    endDate: $("#date2").val(),
    teams: $("#participants").val(),
    numOfTeams: $("#number-of-teams").val(),
    supervisor: localStorage.getItem('username'),
  };

  $.ajax({
    url: "/createTour",
    type: "post",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: (result) => {
      console.log(result);
      $("#form-container").hide();
      showTable(result);
    }
  });
}

$(document).ready(() => {
  $("#create-btn").click(sendCreateTour);
});



// for login page
//

function sendLogin() {
  const username = $("#username").val();
  const password = $("#password").val();

  $.ajax({
    url: "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn",
    type: 'get',
    data: {
      username: username,
      password: password,
    },
    success: (result) => {
      console.log(result);
      localStorage.setItem("usertype", result.type);
      localStorage.setItem("username", username);
      if (result.type == "admin") {
        window.location.href = "/admin";
      }
      else {
        window.location.href = "/student";
      }
    },
  });
}

$(document).ready(() => {
  $("#login-btn").click(sendLogin);
});
