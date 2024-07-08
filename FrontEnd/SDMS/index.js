// ! navigation

function showSection(sectionId) {
    const sections = document.querySelectorAll('section');
    sections.forEach((section) => {
        section.style.display = 'none';
    });
    document.getElementById(sectionId).style.display = 'block';
}


// ! image slide show

const slideshow = document.querySelector('.slideshow');
const images = slideshow.querySelectorAll('img');
let currentImageIndex = 0;

function nextImage() {
  images[currentImageIndex].classList.remove('active');
  currentImageIndex = (currentImageIndex + 1) % images.length;
  images[currentImageIndex].classList.add('active');
}

setInterval(nextImage, 3000);

// ! View more
document.getElementById('view-more').addEventListener('click', function() {
    document.querySelectorAll('.hidden').forEach(function(element) {
        element.classList.remove('hidden');
    });
    this.style.display = 'none';
});

