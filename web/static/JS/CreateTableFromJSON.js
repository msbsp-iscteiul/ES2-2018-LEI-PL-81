$(function () {
    $.get('http://172.17.10.175:8080/api/history/', {email: window.SESSION_EMAIL}, function (data) {
        var tableContent = '';
        $.each(data.result.nodes, function (i) {
            tableContent += '<tr>';
            tableContent += '<th scope="row">' + data.result.nodes[i].problemName + '</th>';
            tableContent += '<td>' + new Date(data.result.nodes[i].startDate).toLocaleDateString() + '</td>';
            tableContent += '<td>' + new Date(data.result.nodes[i].endDate).toLocaleDateString() + '</td>';
            tableContent += '<th><a href="/history/details/' + data.result.nodes[i].id + '" class="btn btn-primary">Details</a></th>';
            tableContent += '</tr>';
        });
        $('#tableDiv').html(tableContent);
    });
})


