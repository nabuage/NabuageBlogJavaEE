var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.utility = org.nabuage.blog.utility || {};

org.nabuage.blog.utility.Template = function Template(settings) {
    var config = {
        urlRoot: "template"
    };
    
    if (settings) {
        $.extend(config, settings);
    }
    
    var templates = new Array();
    
    var loadTemplate = function(filename) {
        
        $.ajax({
            url: config.urlRoot + filename + ".html",
            type: "GET",
            async: false,
            success: function(data, status, xhr) {
                templates[filename] = data;
            },
            error: function(xhr, status, error) {
                
            }
        });
        
    };
    
    this.get = function(filename) {
        
        if (templates[filename] === undefined) {
            loadTemplate(filename);
        }
        
        return templates[filename];
        
    };
    
};