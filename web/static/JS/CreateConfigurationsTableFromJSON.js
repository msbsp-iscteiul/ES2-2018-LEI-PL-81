$(function () {
    $.get('http://172.17.8.26:8080/api/configurations/', {email: window.SESSION_EMAIL}, function (data) {
        var tableContent = '';
        $.each(data.result.nodes, function (i) {
            tableContent += '<tr>';
            tableContent += '<th scope="row">' + '<a href="details">' + data.result.nodes[i].problemName + '</a>' + '</th>';
            tableContent += '<td>' + new Date(data.result.nodes[i].startDate).toLocaleDateString() + '</td>';
            tableContent += '<td colspan="2">' + new Date(data.result.nodes[i].endDate).toLocaleDateString() + '</td>';
            tableContent += '</tr>';
        });
        $('#tableDiv').html(tableContent);
    });
})

