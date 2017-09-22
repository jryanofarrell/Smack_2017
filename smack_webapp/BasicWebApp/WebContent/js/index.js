var favoriteButton = document.querySelector('#favorite'),
    printButton = document.querySelector('#print'),
    downloadButton = document.querySelector('#download');

TweenMax.set('svg', {
  visibility: 'visible'
});

var tlFavorite = new TimelineMax({paused: true});

tlFavorite
.to('#bike__text', 0.15, {
  opacity: 0
})
.to('#bike__rect', 0.30, {
  visibility: 'hidden',
  ease: Power3.easeOut
})  
.to('#svg_7', 0.00, {
	 visibility: 'visible',
	  ease: Power3.easeOut
})
.to('#svg_6', 0.00, {
 visibility: 'visible',
  ease: Power3.easeOut
})  
.to('#svg_5', 0.00, {
 visibility: 'visible',
 ease: Power3.easeOut
})  
.to('#BlueCircle', 0.05, {
 visibility: 'visible'
})    
.to('#OuterCircle', 0.05, {
 visibility: 'visible'
});



favoriteButton.addEventListener('mouseenter', function() {
  tlFavorite.play();
});


favoriteButton.addEventListener('mouseleave', function() {
  tlFavorite.reverse();
});

