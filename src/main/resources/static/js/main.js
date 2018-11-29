$(document).ready(function () {

    validate();

    $(".image-picker").imagepicker({
        show_label: true
    });

    $("#length").slider().on('slideStop', function (ev) {
        var selectedValue = Array.from($(this).data("slider-values"))[ev.value - 1];
        var selectedSize =  Array.from($(this).data("slider-ticks-labels"))[ev.value - 1];

        $("#length-selectedValue").val(selectedValue);
        $("#length-selectedValue-text").html(selectedValue);

        $(".length-images").hide();
        $(".length-description").hide();
        $("#length-image-"+selectedSize).show();
        $("#length-text-"+selectedSize).show();

        validate();
    });

    $("#width").slider().on('slideStop', function (ev) {
        var selectedValue = Array.from($(this).data("slider-values"))[ev.value - 1];
        var selectedSize =  Array.from($(this).data("slider-ticks-labels"))[ev.value - 1];

        $("#width-selectedValue").val(selectedValue);
        $("#width-selectedValue-text").html(selectedValue);

        $(".width-images").hide();
        $(".width-description").hide();
        $("#width-image-"+selectedSize).show();
        $("#width-text-"+selectedSize).show();

        validate();
    });

    $("#height").slider().on('slideStop', function (ev) {
        var selectedValue = Array.from($(this).data("slider-values"))[ev.value - 1];
        var selectedSize =  Array.from($(this).data("slider-ticks-labels"))[ev.value - 1];

        $("#height-selectedValue").val(selectedValue);
        $("#height-selectedValue-text").html(selectedValue);

        $(".height-images").hide();
        $(".height-description").hide();
        $("#height-image-"+selectedSize).show();
        $("#height-text-"+selectedSize).show();

        validate();
    });

    function validate() {
        $("#productform").ajaxSubmit({
            url: $('#productform').attr('action'),
            type: 'post',
            success: function (price) {
                $("#price").html(price);
            }
        });
    }


});