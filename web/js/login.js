var nabuageGlobal = new org.nabuage.blog.utility.Global();
var nabuageTemplate = new org.nabuage.blog.utility.Template({urlRoot: "/BlogApp/js/template/"});
var app = new org.nabuage.blog.Main();

$(function(){
    app.setupNagivation();
    $("button").on("click", function(event){
        event.preventDefault();
        
        $.ajax({
            url: "j_security_check",
            type: "POST",
            data: $("form").serialize(),
            success: function(data, status, xhr) {
                document.location.reload(true);
            },
            error: function(xhr, status, error) {
                nabuageGlobal.popupMessage({title: "Login Failure", message: "Invalid username and/or password. Please try again."});
            }
        });
    });
});