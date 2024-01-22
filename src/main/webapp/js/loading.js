$(document).ready(function(){

    NProgress.configure({ showSpinner: false, trickle: false });
    $('a[href]').click(showLoadingBar);
    $('form').submit(showLoadingBar);
    let interval;
    let counter = 0; // Percentage of the bar 0 to 1
    let inc = 0.04; // 4% each second
    
    function showLoadingBar() {
        clearInterval(interval);
        NProgress.start();
        interval = setInterval(function() {
            if (counter >= 0.99) {
                clearInterval(interval); // stop the progress bar
            } else if(counter >= 0.95) {
                inc = 0.001;
            } else if(counter >= 0.9) {
                inc = 0.002;
            } else if(counter >= 0.7) {
                inc = 0.01;
            } else if(counter >= 0.5) {
                inc = 0.02;
            } else if(counter >= 0.3) {
                inc = 0.03;
            }
            
            
            NProgress.inc(inc);
            counter += inc;
            console.log(counter)
        }, 1000);
    }
    
    // when the page exits
    $(window).on('beforeunload', function() {
        clearInterval(interval);
        NProgress.done();
    });
});









