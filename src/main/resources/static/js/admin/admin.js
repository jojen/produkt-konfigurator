tinymce.init({
    selector: "textarea",
    plugins: [
        "advlist autolink lists link image charmap preview anchor",
        "searchreplace visualblocks code fullscreen",
        "insertdatetime media table contextmenu paste"
    ],
    toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
});


Dropzone.options.defaultDropzone = {
    init: function () {
        //Restore initial message when queue has been completed
        this.on("complete", function (file) {
            location.reload();
        });
    }
};

// Image Gallery
$(document).on('click', '[data-toggle="lightbox"]', function (event) {
    event.preventDefault();
    $(this).ekkoLightbox();
});

// Image Gallery Form Selector
$(document).on("click", ".select-gallery-btn", function () {   ;
    var modalviewtarget = $(this).data('modalviewtarget');
    var modaltarget = $(this).data('modaltarget');

    $("#select-gallery").data('modalviewtarget', modalviewtarget);
    $("#select-gallery").data('modaltarget', modaltarget);
});

$(document).on("click", ".select-gallery-item", function () {
    var $modal = $("#select-gallery");
    var modalviewtarget = $modal.data("modalviewtarget");
    var modaltarget = $modal.data("modaltarget");
    var src = $(this).attr('src');
    var id = $(this).data('id');
    $("#"+modalviewtarget).attr('src',src).show();
    $("#"+modaltarget).attr('value',id);
    $modal.modal('toggle');
});


$(".delete-image").click(function () {
    var image = $(this).data("target");
    $("#"+image).val("");
    var imageview = $(this).data("view-target");
    $("#"+imageview).attr("src","");

});