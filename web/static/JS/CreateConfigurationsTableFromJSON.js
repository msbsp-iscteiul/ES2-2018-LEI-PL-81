$(function () {
    $.get('http://172.17.10.175:8080/api/optimization/searchoptimizationconfigurationbyemail', {email: window.SESSION_EMAIL}, function (data) {
        var tableContent = '';
        $.each(data.result.summary, function (i) {

            tableContent += '<tr>';
            tableContent += '<th scope="row">' + data.result.summary[i].problemName + '</th>';
            tableContent += '<td>' + data.result.summary[i].description + '</td>';
            tableContent += '<td>' + new Date(data.result.summary[i].createdDate).toLocaleDateString() + '</td>';
            tableContent += '<td><a href="/requestdetails/' + data.result.summary[i].id + '" class="btn btn-primary">Open</a></td>';
            tableContent += '<td><a href="/processing/' + data.result.summary[i].id + '" class="btn btn-primary">Execute</a></td>';
            tableContent += '</tr>';
        });
        $('#tableDiv').html(tableContent);
    });
});

