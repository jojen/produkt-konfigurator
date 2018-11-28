$(document).ready(function () {

    validate();

    $(".image-picker").imagepicker({
        show_label: true
    });

    $("#length").slider().on('slideStop', function(ev){
           var selectedValue = Array.from($(this).data("slider-values"))[ev.value-1];
           $("#length-selectedValue").val(selectedValue)
           validate();
      });

    $("#width").slider().on('slideStop', function(ev){
           var selectedValue = Array.from($(this).data("slider-values"))[ev.value-1];
           $("#width-selectedValue").val(selectedValue)
           validate();
      });

    $("#height").slider().on('slideStop', function(ev){
           var selectedValue = Array.from($(this).data("slider-values"))[ev.value-1];
           $("#height-selectedValue").val(selectedValue)
           validate();
      });

    function validate(){
        $("#productform").ajaxSubmit({
            url: $('#productform').attr('action'),
            type: 'post',
            success:  function(price) {
                   $("#price").html(price);
                }});
    }


});