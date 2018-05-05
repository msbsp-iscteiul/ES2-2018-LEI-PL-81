$(function () {
    $.get('http://172.17.5.62:8080/api/history/', {email: window.SESSION_EMAIL}, function (data) {
        var tableContent = '';
        $.each(data.result.nodes, function (i) {
            tableContent += '<tr>';
            tableContent += '<th scope="row"><a href="/history/details/'+data.result.nodes[i].id+'">' + data.result.nodes[i].problemName + '</a></th>';
            tableContent += '<td>' + new Date(data.result.nodes[i].startDate).toLocaleDateString() + '</td>';
            tableContent += '<td colspan="2">' + new Date(data.result.nodes[i].endDate).toLocaleDateString() + '</td>';
            tableContent += '</tr>';
        });
        $('#tableDiv').html(tableContent);
    });
})

