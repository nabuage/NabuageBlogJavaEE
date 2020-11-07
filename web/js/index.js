var nabuageGlobal = new org.nabuage.blog.utility.Global();
var nabuageTemplate = new org.nabuage.blog.utility.Template({urlRoot: "/BlogApp/js/template/"});
var app = new org.nabuage.blog.Main();

$(function(){
    app.setupNagivation();
    app.start();
});