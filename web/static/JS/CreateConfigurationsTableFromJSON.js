$(function () {
    $.get('http://172.17.5.62:8080/api/optimization/searchoptimizationconfigurationbyemail', {email: window.SESSION_EMAIL}, function (data) {
        var tableContent = '';
        $.each(data.result.summary, function (i) {
            tableContent += '<tr>';
            tableContent += '<th scope="row">' + '<a href="Mete aqui qualquer coisa Maria">' + data.result.summary[i].problemName + '</a>' + '</th>';
            tableContent += '<td>' + data.result.summary[i].description + '</td>';
            tableContent += '<td colspan="2">' + new Date(data.result.summary[i].createdDate).toLocaleDateString() + '</td>';
            tableContent += '</tr>';
        });
        $('#tableDiv').html(tableContent);
    });
})

