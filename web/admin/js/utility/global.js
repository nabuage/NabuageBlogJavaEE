var org = org || {};
org.nabuage = org.nabuage || {};
org.nabuage.blog = org.nabuage.blog || {};
org.nabuage.blog.utility = org.nabuage.blog.utility || {};

org.nabuage.blog.utility.Global = function Global() {    
    
    this.cleanseHTMLOutput = function(html) {
        
      return html.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#x27;").replace(/\//g, "&#x2F;");
        
    };
    
};