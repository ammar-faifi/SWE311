
$.get("https://competehub.azurewebsites.net/randRR", (data, status) => {
  console.log(data, status);


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
  };

  $.ajax({
    url: "http://localhost:8080/createTour",
    type: "post",
    contentType: "application/json",
    data: JSON.stringify(data),
    success: (result, status) => {
      console.log(status, result);
    }
  });
}

$(document).ready(() => {
  $("#create-btn").click(sendCreateTour);
});
