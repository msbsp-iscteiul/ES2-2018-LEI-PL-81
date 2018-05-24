var $choices = $('[name=choices]');
$('#id_algorithm_choice_method').change(function () {
    var method = $(this).val();
    if (method === 'Manual') {
        enableChoices();
    } else if (method === 'Automatic') {
        randomizeChoices();
        disableChoices();
    } else if (method === 'Mixed') {
        randomizeChoices();
        enableChoices();
    }
});

function enableChoices() {
    $choices.each(function () {
        $(this).unbind('click');
    })
}

function disableChoices() {
    $choices.each(function () {
        $(this).click(function () {
            return false;
        });
    });
}

function randomizeChoices() {
    do {
        $choices.each(function () {
            $(this).prop('checked', Math.random() > .5);
        });
    } while ($choices.filter(function () {
        return $(this).prop('checked');
    }).length === 0)
}
