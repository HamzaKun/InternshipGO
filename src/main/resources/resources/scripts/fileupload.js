/**
 * Created by hamza on 04/01/17.
 */
$(document).ready(function() {
    $("#upload-file-input").on("change", uploadFile);
});

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile() {
    $.ajax({
        url: "/uploadProfilePic",
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
            // Handle upload success
            $("#upload-file-message").text("File succesfully uploaded");
        },
        error: function () {
            // Handle upload error
            $("#upload-file-message").text(
                "File not uploaded (perhaps it's too much big)");
        }
    });
}
$(document).ready(function() {
    $("#upload-file-input1").on("change", uploadFile1);
});

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile1() {
    $.ajax({
        url: "/uploadResume",
        type: "POST",
        data: new FormData($("#upload-file-form1")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
            // Handle upload success
            $("#upload-file-message1").text("File succesfully uploaded");
        },
        error: function () {
            // Handle upload error
            $("#upload-file-message1").text(
                "File not uploaded (perhaps it's too much big)");
        }
    });
}