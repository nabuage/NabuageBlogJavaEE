(function($){
    $.fn.nabuageDataTable = function(settings){
        var $self = $(this);
        
        var config = {
        };
        
        if (settings) {
            $.extend(config, settings);
        }
        
        var dataTable = $self.dataTable();

        return $self;
        
    };
})(jQuery);